package com.dailycode.premiumug.odi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "XXJICUG_AR_CUSTOMERS_CONCT_STG", schema = "XXJIC", catalog = "")
public class CustomersConctStg {
    @Id
    @SequenceGenerator(name = "seq",sequenceName = "XXJIC_CUST_CONT_CONV_SEQ",allocationSize = 1,schema = "XXJIC")
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "SEQ_ID", nullable = false, precision = 0)
    private Long seqId;
    @Basic
    @Column(name = "PROCESS_FLAG", nullable = false, length = 2)
    private String processFlag;
    @Basic
    @Column(name = "REQUEST_ID", nullable = true, precision = 0)
    private Integer requestId;
    @Basic
    @Column(name = "CREATED_BY", nullable = false, precision = 0)
    private Integer createdBy;
    @Basic
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
    @Basic
    @Column(name = "LAST_UPDATED_BY", nullable = true, precision = 0)
    private Integer lastUpdatedBy;
    @Basic
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private LocalDateTime lastUpdateDate;
    @Basic
    @Column(name = "ERROR_MESSAGE", nullable = true, length = 4000)
    private String errorMessage;
    @Basic
    @Column(name = "LAST_UPDATE_LOGIN", nullable = true, precision = 0)
    private Integer lastUpdateLogin;
    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = true, length = 360)
    private String customerName;
    @Basic
    @Column(name = "ACCOUNT_DESCRIPTION", nullable = true, length = 240)
    private String accountDescription;
    @Basic
    @Column(name = "ORGANIZATION_NAME", nullable = true, length = 240)
    private String organizationName;
    @Basic
    @Column(name = "CUSTOMER_SITE_NAME", nullable = true, length = 360)
    private String customerSiteName;
    @Basic
    @Column(name = "PERSON_PREFIX", nullable = true, length = 30)
    private String personPrefix;
    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 240)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 240)
    private String lastName;
    @Basic
    @Column(name = "SEX_CODE", nullable = true, length = 30)
    private String sexCode;
    @Basic
    @Column(name = "JOB_TITLE", nullable = true, length = 100)
    private String jobTitle;
    @Basic
    @Column(name = "EMAIL_ADDRESS", nullable = true, length = 100)
    private String emailAddress;
    @Basic
    @Column(name = "COUNTRY", nullable = true, length = 60)
    private String country;
    @Basic
    @Column(name = "ADDRESS_1", nullable = true, length = 60)
    private String address1;
    @Basic
    @Column(name = "CITY", nullable = true, length = 60)
    private String city;
    @Basic
    @Column(name = "COUNTY", nullable = true, length = 60)
    private String county;
    @Basic
    @Column(name = "STATE", nullable = true, length = 60)
    private String state;
    @Basic
    @Column(name = "POSTAL_CODE", nullable = true, length = 60)
    private String postalCode;
    @Basic
    @Column(name = "PHONE_AREA_CODE", nullable = true, length = 60)
    private String phoneAreaCode;
    @Basic
    @Column(name = "COUNTRY_CODE", nullable = true, length = 40)
    private String countryCode;
    @Basic
    @Column(name = "PHONE_EXTENSION", nullable = true, length = 30)
    private String phoneExtension;
    @Basic
    @Column(name = "PHONE_NUMBER", nullable = true, length = 25)
    private String phoneNumber;
    @Basic
    @Column(name = "FAX_AREA_CODE", nullable = true, length = 60)
    private String faxAreaCode;
    @Basic
    @Column(name = "FAX_COUNTRY_CODE", nullable = true, length = 40)
    private String faxCountryCode;
    @Basic
    @Column(name = "FAX_NUMBER", nullable = true, length = 25)
    private String faxNumber;
    @Basic
    @Column(name = "WEB_URL", nullable = true, length = 240)
    private String webUrl;
    @Basic
    @Column(name = "LEGACY_SOURCE_NAME", nullable = false, length = 150)
    private String legacySourceName;
    @Basic
    @Column(name = "LEGACY_SOURCE_VALUE", nullable = false, length = 150)
    private String legacySourceValue;
    @Basic
    @Column(name = "LEGACY_POLICY_NUMBER", nullable = true, length = 150)
    private String legacyPolicyNumber;
    @Basic
    @Column(name = "PERSON_PARTY_ID", nullable = true, precision = 0)
    private Integer personPartyId;
    @Basic
    @Column(name = "PARTY_ID", nullable = true, precision = 0)
    private Integer partyId;
    @Basic
    @Column(name = "ORG_ID", nullable = true, precision = 0)
    private Integer orgId;
    @Basic
    @Column(name = "PARTY_SITE_ID", nullable = true, precision = 0)
    private Integer partySiteId;
    @Basic
    @Column(name = "CUST_ACCOUNT_ID", nullable = true, precision = 0)
    private Integer custAccountId;
    @Basic
    @Column(name = "CUST_ACCT_SITE_ID", nullable = true, precision = 0)
    private Integer custAcctSiteId;
}
