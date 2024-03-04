package com.dailycode.premiumug.reports.model;

import lombok.Data;

@Data
public class PremiumCustContainer {
    private String EntityId;
    private Integer EntityType;
    private String EntityTypeDesc;
    private String PayerGroup;
    private String Firstname;
    private String Surname;
    private String CompanyName;
    private String EntityName;
    private String ActiveEntity;
    private String ExternallyMastered;
    private String CountryCode;
    private String Country;
    private String CountryId;
    private String County;
    private String City;
    private String Address1;
    private String Address2;
    private String PostCode;
    private String PinNo;
    private String OperatingUnit;
    private String PolicyNumber;
    private String BranchCode;
    private String portfolio;
    private String productId;
}
