package com.dailycode.premiumug.odi.services;


import com.dailycode.premiumug.odi.model.CustomerSTG;
import com.dailycode.premiumug.odi.model.CustomersConctStg;
import com.dailycode.premiumug.odi.model.OFApremiums;
import com.dailycode.premiumug.odi.model.RedisModel;
import com.dailycode.premiumug.odi.repository.*;
import com.dailycode.premiumug.reports.model.PremiumCustContainer;
import com.dailycode.premiumug.reports.model.PremiumTransContainer;
import com.sun.istack.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OdiServices {
    Logger logger = Logger.getLogger(OdiServices.class.getName());

    private final OFApremiumRepo ofApremiumRepo;
    private final StagingCustRepo stagingCustRepo;
    private final CustomersConcatStgRepo concatStgRepo;
    private final RedisService redisService;
    private final DailyRatesRepo dailyRatesRepo;

    public OdiServices(OFApremiumRepo ofApremiumRepo, StagingCustRepo stagingCustRepo, CustomersConcatStgRepo concatStgRepo, RedisService redisService, DailyRatesRepo dailyRatesRepo) {
        this.ofApremiumRepo = ofApremiumRepo;
        this.stagingCustRepo = stagingCustRepo;
        this.concatStgRepo = concatStgRepo;
        this.redisService = redisService;
        this.dailyRatesRepo = dailyRatesRepo;
    }


    public Integer getCountOfAvailableJournals(String operatingUnit, Integer entryLine, String journalId,
                                               String policynumber, String invoiceType, Double unitPrice) {
        logger.info("=========Get count for journal no : " +journalId +" From OFA Database");

        int r;

        try {
            Optional<OFApremiums> result = ofApremiumRepo
                    .findByOperatingUnitAndLineNumAndTransactionNumberAndLegacyPolicyNumberAndClazzAndUnitPrice
                            (operatingUnit,entryLine,journalId,policynumber,invoiceType,unitPrice);
            //Integer result2 = ofApremiumRepo.findIfExist(operatingUnit,entryLine,journalId,policynumber,invoiceType);

            if (result.isPresent()){
                r =1;
            }
            else {
                r =0;
            }
        }
        catch (Exception e) {
            r = 0;
            e.printStackTrace();
        }
        return r;
    }

    @NotNull
    public Pair<Integer, String> checkIfCustIsAvalaible(String countryId, String entityId, String polNumber, String pin,
                                                        String portfolio) {
        String operatingUnit = "JHL_"+countryId+"_MED_OU";
        int r = 0;
        String productPortfolio = "";

        try {
            Optional<String> portfolio1 = stagingCustRepo.findAvailablePin(operatingUnit,entityId,polNumber,pin);
            Integer customerMap = stagingCustRepo.findIfAvailable(operatingUnit,entityId,polNumber,pin);
            if (portfolio1.isPresent() && customerMap > 0 ) {
                r = 1;
                productPortfolio = portfolio1.get();
            } else if (portfolio1.isEmpty() && customerMap > 0 ) {
                r = 1;
                if (portfolio != null && !portfolio.isEmpty()) {
                    stagingCustRepo.updatePortfolio(operatingUnit,entityId,polNumber,pin,portfolio);
                    productPortfolio = portfolio;
                }
                else {
                    stagingCustRepo.updatePortfolio(operatingUnit,entityId,polNumber,pin,"53");
                    productPortfolio = "52";
                }


            } else {
                r = 0;
                productPortfolio ="52";
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            r =2;
        }
        return Pair.of(r,productPortfolio);
    }

    public CustomerSTG saveCustomerToOFA(@NotNull PremiumCustContainer custContainer) {
        CustomerSTG customer = new CustomerSTG();
        customer.setProcessFlag("1");
        customer.setCreatedBy(9999);
        customer.setCreationDate(LocalDateTime.now());
        customer.setLastUpdatedBy(9999);
        customer.setLastUpdateLogin(1);
        customer.setLastUpdateDate(LocalDateTime.now());
        if (custContainer.getEntityType() == 1) {
            customer.setCustomerType("ORGANIZATION");
        }
        else {
            customer.setCustomerType("PERSON");
        }
        customer.setCustomerName(custContainer.getEntityName());
        customer.setAccountDescription(custContainer.getPolicyNumber());
        customer.setAccountType("External");
        customer.setAcctProfileClass("Default");
        customer.setAcctPaymentTerm("Immediate");
        customer.setOrganizationName(custContainer.getOperatingUnit());
        customer.setCustomerSiteName(custContainer.getEntityId());
        customer.setAddress1(custContainer.getAddress1());
        customer.setAddress2(custContainer.getAddress2());
        if (custContainer.getCity().isEmpty() || custContainer.getCity().equals(null)) {
            customer.setCity("Nairobi");
        }
        else {
            customer.setCity(custContainer.getCity());
        }
        if (custContainer.getCounty().isEmpty() || custContainer.getCounty().equals(null)) {
            customer.setCounty("Nairobi");
        }
        else {
            customer.setCounty(custContainer.getCounty());
        }
        customer.setState(custContainer.getCountry());
        customer.setCountry(custContainer.getCountryId());
        if (custContainer.getPostCode().isEmpty() || custContainer.getPostCode().equals(null)) {
            customer.setPostalCode("00100");
        }
        else {
            customer.setPostalCode(custContainer.getPostCode());
        }
        customer.setBillToFlag("Y");
        customer.setShipToFlag("N");
        customer.setBillToLocation(custContainer.getEntityId());
        customer.setSiteProfileClass("Default");
        customer.setBillPaymentTerm("Immediate");
        customer.setSiteCreditHold("N");
        customer.setSiteCreditCheck("N");
        customer.setBillRevAccount("411001");
        customer.setBillToReceiviableAccount("151011");
        //legacy_ref1 pin missing
        customer.setLegacyRef1(custContainer.getPinNo());
        //legacy_ref11 to implement
        String typeDesc = null;
        if (custContainer.getEntityType() ==1){
            typeDesc = "DIRECT-CORPORATE";
        }
        else {
            typeDesc ="DIRECT-INDIVIDUAL";
        }
        customer.setLegacyRef11(typeDesc);
        customer.setLegacyRef12(custContainer.getEntityId());
        customer.setLegacyRef13(custContainer.getPolicyNumber());
        customer.setLegacyRef17(custContainer.getProductId());
        if (custContainer.getBranchCode().isEmpty() || custContainer.getBranchCode().equals(null)) {
            customer.setLegacyRef18("101");
        }
        else {
            customer.setLegacyRef18(custContainer.getBranchCode());
        }
        customer.setLegacyRef19("00");
        if (custContainer.getPortfolio().isEmpty() || custContainer.getPortfolio().equals(null)) {
            customer.setLegacyRef20("51");
        }
        else {
            customer.setLegacyRef20(custContainer.getPortfolio());
        }

        customer.setLegacyRef21("0");

        return saveCustomerData(customer);

    }

    @Transactional
    public CustomerSTG saveCustomerData(CustomerSTG customer) {
        return stagingCustRepo.save(customer);
    }

    public CustomersConctStg saveCustomerToCustOFAStg(PremiumCustContainer custContainer) {
        try {
            CustomersConctStg custStg = new CustomersConctStg();
            custStg.setProcessFlag("1");
            custStg.setCreatedBy(9999);
            custStg.setCreationDate(LocalDateTime.now());
            custStg.setLastUpdatedBy(9999);
            custStg.setLastUpdateDate(LocalDateTime.now());
            custStg.setErrorMessage(null);
            custStg.setCustomerName(custContainer.getEntityName());
            custStg.setOrganizationName(custContainer.getOperatingUnit());
            if (custContainer.getFirstname().isEmpty()){
                custStg.setFirstName(custContainer.getCompanyName());
            }
            else {
                custStg.setFirstName(custContainer.getFirstname());
            }
            if (custContainer.getSurname().isEmpty()){
                custStg.setLastName(custContainer.getCompanyName());
            }
            else {
                custStg.setLastName(custContainer.getSurname());
            }
            custStg.setCountry(custContainer.getCountry());
            custStg.setAddress1(custContainer.getAddress1());
            if (custContainer.getCity().isEmpty() || custContainer.getCity().equals(null)) {
                custStg.setCity("Nairobi");
            }
            else {
                custStg.setCity(custContainer.getCity());
            }
            if (custContainer.getCounty().isEmpty() || custContainer.getCounty().equals(null)) {
                custStg.setCounty("Nairobi");
            }
            else {
                custStg.setCounty(custContainer.getCounty());
            }
            custStg.setState(custContainer.getCountry());
            if (custContainer.getPostCode().equals(null) || custContainer.getPostCode().isEmpty()) {
                custStg.setPostalCode("00100");
            }
            else {
                custStg.setPostalCode(custContainer.getPostCode());
            }
            custStg.setLegacySourceName(custContainer.getPinNo());
            custStg.setLegacyPolicyNumber(custContainer.getPolicyNumber());
            custStg.setLegacySourceValue(custContainer.getEntityId());


            return saveCustToCustStg(custStg);

        }
        catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    @Transactional
    public CustomersConctStg saveCustToCustStg(CustomersConctStg custStg) {
        return concatStgRepo.save(custStg);
    }

    public Pair<OFApremiums, String> saveDataToOfa(PremiumTransContainer transContainer) {

        OFApremiums apremiums = new OFApremiums();
        apremiums.setTransactionNumber(transContainer.getJournalId());
        apremiums.setAmount(String.valueOf(transContainer.getGrossAmount()));
        apremiums.setTransactionSource("JHL "+ transContainer.getCountryId()+ " MED IMP");
        apremiums.setClazz(transContainer.getClassType());
        apremiums.setTransactionDate(LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd")));
        apremiums.setGlDate(LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd")));
        apremiums.setTransactionType(transContainer.getTransactionType());
        apremiums.setReference(transContainer.getJournalDesc());
        if (transContainer.getCurrencyName().equals("USD")) {
            if (transContainer.getEntryLineDesc().equals("BASE PREMIUM")) {
                apremiums.setDescription(transContainer.getEntryLineDesc() + " USD");
            }
            else {
                apremiums.setDescription(transContainer.getEntryLineDesc());
            }
            Optional<RatesInterface> rates = dailyRatesRepo.fetchConversionRates(LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                    "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd")));
            if (rates.isPresent()) {
                apremiums.setCurrencyCode(rates.get().getFromCurrency());
                apremiums.setFrnCurrErateType("USER");
                apremiums.setFrnCurrErateDate(rates.get().getConversionDate());
                Double conversrate = rates.get().getConversionRate();
                apremiums.setExchangeRate(conversrate);
            }
            else {
                logger.warning("No conversion Rates found!!!!!!!!!!!!!!!!!!!!!!");
                apremiums.setCurrencyCode(transContainer.getCurrencyName());
                apremiums.setFrnCurrErateType("USER");
                apremiums.setFrnCurrErateDate((LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                        "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"))));
                apremiums.setExchangeRate(Double.valueOf(1));
            }
        }
        else {
            apremiums.setDescription(transContainer.getEntryLineDesc());
            apremiums.setCurrencyCode(transContainer.getCurrencyName());
            apremiums.setFrnCurrErateType("USER");
            apremiums.setFrnCurrErateDate((LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                    "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"))));
            apremiums.setExchangeRate(Double.valueOf(1));
        }
        apremiums.setPaymentTerms("IMMEDIATE");
        apremiums.setDueDate(LocalDate.parse(DateConverter(transContainer.getPostingDate(),
                "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd")));
        apremiums.setOperatingUnit(transContainer.getOperatingUnit());
        apremiums.setLedger(transContainer.getLedgers());
        apremiums.setCustomerName(transContainer.getEntityName());
        apremiums.setCustAccName(transContainer.getPolicyNumber());
        if (transContainer.getCounty().isEmpty() || transContainer.getCounty().equals(null)) {
            apremiums.setCustAccSiteName("Nairobi");
        }
        else {
            apremiums.setCustAccSiteName(transContainer.getCounty());
        }
        apremiums.setBillToSiteName("001");
        apremiums.setPaymentMethod("CHECK");
        apremiums.setLineNum(transContainer.getEntryLine());
        apremiums.setQuantity(1);
        apremiums.setUnitPrice(transContainer.getAmount());
        apremiums.setOpenAmount(transContainer.getAmount());
        apremiums.setLegacySourceName(transContainer.getPinNo());
        apremiums.setLegacySourcevalue(transContainer.getPolicyHolderId());
        apremiums.setLegacyPolicyNumber(transContainer.getPolicyNumber());
        apremiums.setProcessFlag("1");
        apremiums.setCreatedBy(9999);
        apremiums.setCreationDate(LocalDate.now());
        apremiums.setLastUpdatedBy(9999);
        apremiums.setLastUpdateDate(LocalDate.now());
        apremiums.setLastUpdateLogin(9999);
        apremiums.setLineAmount(transContainer.getAmount());
        apremiums.setBillPartySiteId(001);
        apremiums.setBrokerName(transContainer.getIntermediaryName());
        apremiums.setBrokerCode(transContainer.getIntermediaryId());

        return savePrimium(apremiums);
    }

    @Transactional
    public Pair<OFApremiums, String> savePrimium(OFApremiums apremiums) {

        Pair<OFApremiums, String> resultReturned = null;
        String res =null;
        Random r = new Random();
        int low= 10;
        int high = 500;
        int result= r.nextInt(high-low)+low;
        String logdate = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss").format(new Date());

        RedisModel redisModel = new RedisModel();
        redisModel.setId(result);
        redisModel.setTransactionType("Premiums");
        redisModel.setTransactionObject(apremiums.toString());
        redisModel.setLogTime(logdate);

        try {
            resultReturned = Pair.of(ofApremiumRepo.save(apremiums),"") ;
            boolean residRes = redisService.savePremiumsInRedis(redisModel);
            if (residRes) {
                logger.info("----------------Premium Data Saved in Redis Server-------");
            }
            else {
                logger.warning("!!!!!!!!!!!!!!!!!!!!!!!Failed to save Premium on Redis!!!!!!!!!!!!!!!");
            }
        }
        catch (Exception e){
            e.getMessage();
            resultReturned =Pair.of(null,e.getMessage());
        }
        return resultReturned;
    }

    public boolean updateClientPortfolio(String countryId, String entityId, String polNumber, String pinNo, String portfolio) {
        boolean res = false;
        String operatingUnit = "JHL_"+countryId+"_MED_OU";
        try {
            stagingCustRepo.updatePortfolio(operatingUnit,entityId,polNumber,pinNo,portfolio);
            res = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    private String DateConverter (String currentDate, String currentDateFormat, String newDateFormat) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(currentDateFormat);
            Date date = dateFormat.parse(currentDate);
            DateFormat dateFormat1 = new SimpleDateFormat(newDateFormat);
            String newDate = dateFormat1.format(date);
            return newDate;
        }
        catch (ParseException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return currentDate;
        }
    }

}
