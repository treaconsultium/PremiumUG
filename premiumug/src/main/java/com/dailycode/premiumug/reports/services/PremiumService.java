package com.dailycode.premiumug.reports.services;


import com.dailycode.premiumug.odi.model.CustomerSTG;
import com.dailycode.premiumug.odi.model.CustomersConctStg;
import com.dailycode.premiumug.odi.model.OFApremiums;
import com.dailycode.premiumug.odi.services.OdiServices;
import com.dailycode.premiumug.reports.configs.ListProcessor;
import com.dailycode.premiumug.reports.model.PortfolioInterfaceJ;
import com.dailycode.premiumug.reports.model.PremiumCustContainer;
import com.dailycode.premiumug.reports.model.PremiumTransContainer;
import com.dailycode.premiumug.reports.model.PremiumTransInterface;
import com.dailycode.premiumug.reports.repository.PremiumsRepo;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PremiumService {
    Logger logger = Logger.getLogger(PremiumService.class.getName());

    private final PremiumsRepo premiumsRepo;
    private final ReportExcelGeneratorService excelGeneratorService;
    private final OdiServices odiServices;
    public static String operatingUnit = "";
    public static String invoiceType = "";

    public PremiumService(PremiumsRepo premiumsRepo, ReportExcelGeneratorService excelGeneratorService, OdiServices odiServices) {
        this.premiumsRepo = premiumsRepo;
        this.excelGeneratorService = excelGeneratorService;
        this.odiServices = odiServices;
    }

    @Scheduled(fixedDelay = 5L)
    public void fetchPremiumUG (){
        logger.info("===========Started fetching Premium data==========");

        List<String> premiumPayments = premiumsRepo.getReadyPremiums();
        if (premiumPayments.isEmpty() || premiumPayments == null)
            return;

        logger.info("===========Premium Size fetched : " + premiumPayments.size() + "=======================");

        int i = 1;
        List<List<String>> parts = ListProcessor.chopped(premiumPayments,1000);
        for (List<String> part: parts) {
            List<PremiumTransInterface> premiumTransactions = premiumsRepo.fetchAllTransactions(part);
            logger.info("===============================Running Segment "+ i++ +" of the Memory=============");
            System.out.println(premiumTransactions.size());
            for (String journalId: part) {
                logger.info("Working on Journal ID " + journalId);
                premiumTransactions.stream()
                        .filter(a -> Objects.equals(a.getJournalId(),journalId))
                        .collect(Collectors.toList())
                        .forEach((x) -> {
                            Integer id = x.getRecId();
                            operatingUnit = "JHL_"+x.getCountryId()+"_MED_OU";
                            Integer entryLine = x.getEntryLine();
                            String journalId3 = x.getJournalId();
                            String policyNumber = x.getPolicyNumber();
                            String entryLineDesc = x.getEntryLineDesc();
                            Double amt =x.getGrossAmount();
                            Double lineAmt = x.getAmount();
                            String countryId = x.getCountryId();

                            if (x.getAccountId() == 120 && !x.getJournalDesc().equals("Premium Refund Write Off KE  ")) {
                                excelGeneratorService.updateTransactionStatus(id,5,"Premium Refund Write Off KE,This journal Should not flow to OFA from Account 120");
                                logger.log(Level.INFO,"This journal Should not flow to OFA from Account 120");
                                return;
                            }
                            else {
                                invoiceType = checkInvoiceType(x.getGrossAmount());

                                String transType = checkTransactionType(entryLineDesc,amt,countryId);
                                String ledgers = checkLedgers(countryId);

                                Integer countAR = odiServices.getCountOfAvailableJournals(operatingUnit,entryLine,journalId3,
                                        policyNumber,invoiceType,lineAmt);

                                if (countAR == 0) {
                                    logger.info(" Journal Id : " +journalId3 + "does not exist in OFA staging,continue");

                                    Pair<Integer,String> countAvailCust = odiServices.checkIfCustIsAvalaible(countryId,x.getEntityId(),
                                            x.getPolicyNumber(),x.getPinNo(),x.getPortfolio());
                                    if (countAvailCust.getFirst() == 0 ) {
                                        Boolean result = saveCustomerDetails(x,countAvailCust.getSecond());
                                        if (result == true){
                                            try {
                                                PremiumTransContainer transContainer = new PremiumTransContainer();
                                                transContainer.setCountryId(x.getCountryId());
                                                transContainer.setPolicyHolderId(x.getPolicyHolderId());
                                                transContainer.setEntryLine(entryLine);
                                                transContainer.setEntryLineDesc(x.getEntryLineDesc());
                                                transContainer.setAccountCode(x.getAccountCode());
                                                transContainer.setPostingDate(x.getPostingDate());
                                                transContainer.setJournalId(journalId3);
                                                transContainer.setClassType(invoiceType);
                                                transContainer.setTransactionType(transType);
                                                transContainer.setOperatingUnit(operatingUnit);
                                                transContainer.setLedgers(ledgers);
                                                transContainer.setJournalDesc(x.getJournalDesc());
                                                transContainer.setCurrencyName(x.getCurrencyName());
                                                transContainer.setPolicyNumber(x.getPolicyNumber());
                                                transContainer.setAccountName(x.getAccountName());
                                                transContainer.setBroked(x.getBroked());
                                                transContainer.setAccountId(x.getAccountId());
                                                transContainer.setSourceCompany(x.getSourceCompany());
                                                transContainer.setAmount(x.getAmount());
                                                transContainer.setGrossAmount(amt);
                                                transContainer.setIntermediaryId(x.getIntermediaryId());
                                                transContainer.setIntermediaryName(x.getIntermediaryName());
                                                transContainer.setBranchCode(x.getBranchCode());
                                                transContainer.setBranchName(x.getBranchName());
                                                transContainer.setEntityId(x.getEntityId());
                                                transContainer.setEntityType(x.getEntityType());
                                                transContainer.setPinNo(x.getPinNo());
                                                transContainer.setEntityName(x.getEntityName());
                                                transContainer.setCounty(x.getCounty());
                                                Optional<PortfolioInterfaceJ> getPortfolio = getPortfolio(x.getEntityId());
                                                if (getPortfolio.isPresent()) {
                                                    transContainer.setPortfolio(getPortfolio.get().getPortfolio());
                                                }
                                                else {
                                                    transContainer.setPortfolio(countAvailCust.getSecond());
                                                }

                                                Pair<OFApremiums,String> resul = odiServices.saveDataToOfa(transContainer);
                                                if (!resul.getFirst().equals(null)){
                                                    excelGeneratorService.updateTransactionStatus(id,1,"Transaction saved Successfully to OFA staging");
                                                    excelGeneratorService.saveGeneratedTransactions(journalId3,1,
                                                            "Transaction saved Successfully to OFA staging","",200,entryLineDesc);
                                                    logger.log(Level.INFO,"Transaction saved Successfully to OFA staging for JournalId " +journalId3);
                                                }
                                                else {
                                                    excelGeneratorService.updateTransactionStatus(id,3,"Transaction Failed to save to OFA staging"+resul.getSecond());
                                                    excelGeneratorService.saveGeneratedTransactions(journalId3,0,
                                                            "Transaction Failed to save to OFA staging",resul.getSecond(),200,entryLineDesc);
                                                }

                                            }
                                            catch (Exception e){
                                                logger.log(Level.SEVERE, e.getMessage());
                                                logger.log(Level.SEVERE, "Unable to Save this transaction to OFA even though the customer detail saved");
                                                excelGeneratorService.updateTransactionStatus(id,2,"Unable to Save this transaction to OFA even though the customer detail saved");
                                                excelGeneratorService.saveGeneratedTransactions(journalId3,2,
                                                        "Unable to Save this transaction to OFA even though the customer detail saved",e.getMessage(),200,entryLineDesc);
                                            }

                                        }
                                        else {
                                            logger.log(Level.SEVERE, "Unable to save Customer Details to OFA, hence break for Journal Id " +journalId3);
                                            excelGeneratorService
                                                    .updateTransactionStatus(id,4,"Unable to save Customer Details to OFA, hence break for Journal Id");
                                            excelGeneratorService.saveGeneratedTransactions(journalId3,4,
                                                    "Unable to save Customer Details to OFA, hence break for Journal Id ","Customer details not posted",200,entryLineDesc);
                                        }
                                    } else if (countAvailCust.getFirst() == 1) {
                                        logger.info("-----------------Customer details already existing, proceeding-----------");

                                        try {
                                            PremiumTransContainer  transContainer = new PremiumTransContainer();
                                            transContainer.setCountryId(x.getCountryId());
                                            transContainer.setPolicyHolderId(x.getPolicyHolderId());
                                            transContainer.setEntryLine(entryLine);
                                            transContainer.setEntryLineDesc(x.getEntryLineDesc());
                                            transContainer.setAccountCode(x.getAccountCode());
                                            transContainer.setPostingDate(x.getPostingDate());
                                            transContainer.setJournalId(journalId3);
                                            transContainer.setClassType(invoiceType);
                                            transContainer.setTransactionType(transType);
                                            transContainer.setOperatingUnit(operatingUnit);
                                            transContainer.setLedgers(ledgers);
                                            transContainer.setJournalDesc(x.getJournalDesc());
                                            transContainer.setCurrencyName(x.getCurrencyName());
                                            transContainer.setPolicyNumber(x.getPolicyNumber());
                                            transContainer.setAccountName(x.getAccountName());
                                            transContainer.setBroked(x.getBroked());
                                            transContainer.setAccountId(x.getAccountId());
                                            transContainer.setSourceCompany(x.getSourceCompany());
                                            transContainer.setAmount(x.getAmount());
                                            transContainer.setGrossAmount(x.getGrossAmount());
                                            transContainer.setIntermediaryId(x.getIntermediaryId());
                                            transContainer.setIntermediaryName(x.getIntermediaryName());
                                            transContainer.setBranchCode(x.getBranchCode());
                                            transContainer.setBranchName(x.getBranchName());
                                            transContainer.setEntityId(x.getEntityId());
                                            transContainer.setEntityType(x.getEntityType());
                                            transContainer.setPinNo(x.getPinNo());
                                            transContainer.setEntityName(x.getEntityName());
                                            transContainer.setCounty(x.getCounty());

                                            if (x.getPortfolio() != null && !x.getPortfolio().isEmpty()){
                                                //update the portfolio column
                                                boolean updatePortfolio = odiServices.updateClientPortfolio(countryId,x.getEntityId(),
                                                        x.getPolicyNumber(),x.getPinNo(),x.getPortfolio());
                                                if (updatePortfolio) {
                                                    logger.log(Level.INFO,"Client " + x.getEntityId() + " has been updated from " + countAvailCust.getSecond()
                                                            + " to " +x.getPortfolio());
                                                }
                                                else {
                                                    logger.log(Level.WARNING, "Client portfolio update has failed... !!!!!!!!!!!!");
                                                }
                                            } else if (x.getPortfolio() != null && x.getPortfolio().equals(countAvailCust.getSecond())) {
                                                transContainer.setPortfolio(countAvailCust.getSecond());

                                            } else {
                                                Optional<PortfolioInterfaceJ> getPortfolio = getPortfolio(x.getEntityId());
                                                if (getPortfolio.isPresent()) {
                                                    transContainer.setPortfolio(getPortfolio.get().getPortfolio());
                                                }
                                                else {
                                                    transContainer.setPortfolio(countAvailCust.getSecond());
                                                }
                                            }


                                            Pair<OFApremiums,String> resul = odiServices.saveDataToOfa(transContainer);
                                            if (!resul.getFirst().equals(null)){
                                                excelGeneratorService.updateTransactionStatus(id,1,"Transaction saved Successfully to OFA staging");
                                                excelGeneratorService.saveGeneratedTransactions(journalId3,1,
                                                        "Transaction saved Successfully to OFA staging","",200,entryLineDesc);
                                                logger.log(Level.INFO,"Transaction saved Successfully to OFA staging for JournalId " +journalId3);
                                            }
                                            else {
                                                excelGeneratorService.updateTransactionStatus(id,3,"Transaction Failed to save to OFA staging"+resul.getSecond());
                                                excelGeneratorService.saveGeneratedTransactions(journalId3,0,
                                                        "Transaction Failed to save to OFA staging",resul.getSecond(),200,entryLineDesc);
                                            }

                                        }
                                        catch (Exception e){
                                            logger.log(Level.SEVERE, e.getMessage());
                                            logger.log(Level.SEVERE, "Unable to Save this transaction to OFA even though the customer detail saved");
                                            excelGeneratorService.updateTransactionStatus(id,2,"Unable to Save this transaction to OFA even though the customer detail saved");
                                            excelGeneratorService.saveGeneratedTransactions(journalId3,2,
                                                    "Unable to Save this transaction to OFA even though the customer detail saved",e.getMessage(),200,entryLineDesc);
                                        }
                                    } else {
                                        logger.info("==================Pin already taken==========");
                                        return;
                                    }

                                }
                                else {
                                    logger.warning("------------This Journal already exist--------");
                                    excelGeneratorService.updateTransactionStatus(id,100,"This Premium Journal already exist");
                                    excelGeneratorService.saveGeneratedTransactions(journalId3, 3,"This Journal already exist","",200,entryLineDesc);
                                    return;
                                }
                            }
                        });
            }
        }
    }

    private Boolean saveCustomerDetails(PremiumTransInterface premiums, String portfolio) {
        Boolean res = false;
        try {
            PremiumCustContainer custContainer = new PremiumCustContainer();
            custContainer.setEntityId(premiums.getEntityId());
            custContainer.setEntityType(premiums.getEntityType());
            custContainer.setEntityTypeDesc(premiums.getEntityTypeDesc());
            custContainer.setPayerGroup(premiums.getPayerGroup());
            custContainer.setFirstname(premiums.getFirstname());
            custContainer.setFirstname(premiums.getFirstname());
            custContainer.setSurname(premiums.getSurname());
            custContainer.setCompanyName(premiums.getCompanyName());
            custContainer.setEntityName(premiums.getEntityName());
            custContainer.setActiveEntity("Yes");
            custContainer.setExternallyMastered(premiums.getExternallyMastered());
            custContainer.setCountryCode(premiums.getCountryCode());
            custContainer.setCountry(premiums.getCountry());
            custContainer.setCountryId(premiums.getCountryId());
            custContainer.setCounty(premiums.getCounty());
            custContainer.setCity(premiums.getCity());
            custContainer.setAddress1(premiums.getAddress1());
            custContainer.setAddress2(premiums.getAddress2());
            custContainer.setPostCode(premiums.getPostCode());
            custContainer.setPinNo(premiums.getPinNo());
            custContainer.setOperatingUnit("JHL_UG_MED_OU");
            custContainer.setPolicyNumber(premiums.getPolicyNumber());
            custContainer.setBranchCode(premiums.getBranchCode());
            if (premiums.getPortfolio() != null) {
                custContainer.setPortfolio(premiums.getPortfolio());
                custContainer.setProductId(String.valueOf(premiums.getProductId()));
            }
            else {
                Optional<PortfolioInterfaceJ> portfolioInterface = getPortfolio(premiums.getEntityId());
                if (portfolioInterface.isPresent()) {
                    custContainer.setPortfolio(portfolioInterface.get().getPortfolio());
                    custContainer.setProductId(String.valueOf(portfolioInterface.get().getProductId()));
                }
            }


            CustomerSTG customerStg = odiServices.saveCustomerToOFA(custContainer);
            CustomersConctStg custContStg = odiServices.saveCustomerToCustOFAStg(custContainer);
            logger.info("=================Customer Details Saved================");
            res = true;

        }
        catch (Exception e){
            e.printStackTrace();
            return res;
        }
        return res;
    }

    private String checkTransactionType(String entryLineDesc, Double amt, String countryId) {
        String res = null;

        if (entryLineDesc.equals("MED EXCESS")) {
            if (amt >= 0){
                res = "Med " + countryId + " Excess Inv";
            } else if (amt < 0) {
                res = "Med " + countryId + " Excess CM";
            }
            else {
                res ="";
            }
        } else if (entryLineDesc.equalsIgnoreCase("MED CARD FEE")) {
            if (amt >= 0) {
                res = "JHL " + countryId + " Med Cards Inv";
            } else if (amt < 0) {
                res = "JHL " + countryId + " Med Cards CM";
            }
            else {
                res = "";
            }
        }
        else {
            if (amt >= 0) {
                res = "JHL " + countryId + " Medical Inv";
            } else if (amt < 0) {
                res = "JHL " + countryId + " Medical CM";
            }
            else {
                res = "";
            }
        }

        return res;
    }

    private String checkInvoiceType(Double grossAmount) {
        String invotype = "";

        if (grossAmount >= 0) {
            invotype = "Invoice";
        } else if (grossAmount < 0 ) {
            invotype = "Credit Memo";
        }
        else {
            invotype ="";
        }
        return invotype;
    }

    private String checkLedgers(String countryId) {
        String res = null;
        if (countryId.equals("KE")) {
            res = "JHL_KE_IFRS_PL";
        } else if (countryId.equals("UG")) {
            res = "JHL_UG_MED_PL";
        } else if (countryId.equals("TZ")) {
            res = "JHL_TZ_IFRS_PL";
        }
        else {
            res ="";
        }
        return res;
    }

    private Optional<PortfolioInterfaceJ> getPortfolio(String entityId) {
        Optional<PortfolioInterfaceJ> res = null;
        try {
            res = premiumsRepo.fetchPortfolio(entityId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
