package com.dailycode.premiumug.reports.model;

import lombok.Data;

@Data
public class PremiumTransContainer {
    private String countryId;
    private String policyHolderId;
    private Integer entryLine;
    private String entryLineDesc;
    private String classType;
    private String accountCode;
    private String ledgers;
    private String postingDate;
    private String transactionType;
    private String journalId;
    private String journalDesc;
    private String currencyName;
    private String policyNumber;
    private String operatingUnit;
    private String accountName;
    private String broked;
    private Integer accountId;
    private String sourceCompany;
    private Double amount;
    private Double grossAmount;
    private String intermediaryId;
    private String intermediaryName;
    private String branchCode;
    private String branchName;
    private String entityId;
    private Integer entityType;
    private String pinNo;
    private String entityName;
    private String county;
    private String portfolio;
}
