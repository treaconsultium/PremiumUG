package com.dailycode.premiumug.reports.services;

import com.dailycode.premiumug.reports.model.OfaTransactions;
import com.dailycode.premiumug.reports.model.PremiumsModel;
import com.dailycode.premiumug.reports.repository.OfaTransactionsRepo;
import com.dailycode.premiumug.reports.repository.PremiumsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReportExcelGeneratorService {

    private final OfaTransactionsRepo ofaTransactionsRepo;
    private final PremiumsRepo premiumsRepo;

    public ReportExcelGeneratorService(OfaTransactionsRepo ofaTransactionsRepo, PremiumsRepo premiumsRepo) {
        this.ofaTransactionsRepo = ofaTransactionsRepo;
        this.premiumsRepo = premiumsRepo;
    }


    public void saveGeneratedTransactions(String journalId, Integer status, String statusMessage, String error, Integer groupId, String entryDesc) {
        OfaTransactions transactions = new OfaTransactions();
        transactions.setTransactionId(journalId);
        transactions.setSendStatus(status);
        transactions.setGroupId(groupId);
        transactions.setSendMessage(statusMessage);
        transactions.setSendTime(LocalDateTime.now());
        transactions.setLogTime(LocalDateTime.now());
        transactions.setErrorMessage(error);
        transactions.setTypeDescription(entryDesc);
        saveOfaTransactions(transactions);
    }

    @Transactional
    public OfaTransactions saveOfaTransactions(OfaTransactions transactions) {
        return ofaTransactionsRepo.save(transactions);
    }

    public void updateTransactionStatus(Integer id, Integer status, String statusMessage) {
            PremiumsModel premiumsModel = getRecId(id);
            premiumsModel.setPickedStatus(status);
            premiumsModel.setPickedStatusMsg(statusMessage);
            premiumsModel.setPickedTime(LocalDateTime.now());
            saveTransactionStatus(premiumsModel);
    }

    private PremiumsModel saveTransactionStatus(PremiumsModel premiumsModel) {
        return premiumsRepo.save(premiumsModel);
    }

    private PremiumsModel getRecId(Integer id) {
        return premiumsRepo.findById(id).orElseThrow();
    }
}
