package com.dailycode.premiumug.odi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "XXJICUG_AR_CUSTOMERS_STG", schema = "XXJIC", catalog = "")
public class CustomerSTG {
    @Id
    @SequenceGenerator(name = "seq2",schema = "XXJIC",catalog = "", sequenceName = "XXJIC_CUST_COM_CONV_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
    @Column(name = "SEQ_ID", nullable = false, precision = 0)
    private Long seqId;
    @Basic
    @Column(name = "PROCESS_FLAG", nullable = false, length = 2)
    private String processFlag;
    @Basic
    @Column(name = "CREATED_BY", nullable = false, precision = 0)
    private Integer createdBy;
    @Basic
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
    @Basic
    @Column(name = "LAST_UPDATED_BY", nullable = false, precision = 0)
    private Integer lastUpdatedBy;
    @Basic
    @Column(name = "LAST_UPDATE_LOGIN", nullable = false, precision = 0)
    private Integer lastUpdateLogin;
    @Basic
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private LocalDateTime lastUpdateDate;
    @Basic
    @Column(name = "LOGIN_ID", nullable = true, precision = 0)
    private BigInteger loginId;
    @Basic
    @Column(name = "REQUEST_ID", nullable = true, precision = 0)
    private BigInteger requestId;
    @Basic
    @Column(name = "ERROR_MESSAGE", nullable = true, length = 4000)
    private String errorMessage;
    @Basic
    @Column(name = "CUSTOMER_TYPE", nullable = true, length = 30)
    private String customerType;
    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = true, length = 360)
    private String customerName;
    @Basic
    @Column(name = "PARTY_PHONE_AREA", nullable = true, length = 10)
    private String partyPhoneArea;
    @Basic
    @Column(name = "PARTY_PHONE_COUNTRY", nullable = true, length = 10)
    private String partyPhoneCountry;
    @Basic
    @Column(name = "PARTY_PHONE_EXTENSION", nullable = true, length = 20)
    private String partyPhoneExtension;
    @Basic
    @Column(name = "PARTY_PHONE_NUMBER", nullable = true, length = 40)
    private String partyPhoneNumber;
    @Basic
    @Column(name = "PARTY_FAX_AREA", nullable = true, length = 10)
    private String partyFaxArea;
    @Basic
    @Column(name = "PARTY_FAX_COUNTRY", nullable = true, length = 10)
    private String partyFaxCountry;
    @Basic
    @Column(name = "PARTY_FAX_NUMBER", nullable = true, length = 40)
    private String partyFaxNumber;
    @Basic
    @Column(name = "PARTY_EMAIL_ADDRESS", nullable = true, length = 2000)
    private String partyEmailAddress;
    @Basic
    @Column(name = "PARTY_WEB_URL", nullable = true, length = 2000)
    private String partyWebUrl;
    @Basic
    @Column(name = "CLASSIFICATION", nullable = true, length = 30)
    private String classification;
    @Basic
    @Column(name = "ACCOUNT_DESCRIPTION", nullable = true, length = 240)
    private String accountDescription;
    @Basic
    @Column(name = "ACCOUNT_TYPE", nullable = true, length = 25)
    private String accountType;
    @Basic
    @Column(name = "ACCT_PROFILE_CLASS", nullable = true, length = 25)
    private String acctProfileClass;
    @Basic
    @Column(name = "ACCT_COLLECTOR", nullable = true, length = 30)
    private String acctCollector;
    @Basic
    @Column(name = "ACCOUNT_LEVEL_COLLECTOR", nullable = true, length = 30)
    private String accountLevelCollector;
    @Basic
    @Column(name = "ACCT_COLLECTOR_PHONE", nullable = true, length = 25)
    private String acctCollectorPhone;
    @Basic
    @Column(name = "ACCT_PAYMENT_TERM", nullable = true, length = 15)
    private String acctPaymentTerm;
    @Basic
    @Column(name = "ACCT_CREDIT_HOLD", nullable = true, length = 1)
    private String acctCreditHold;
    @Basic
    @Column(name = "ACCT_CREDIT_CHECK", nullable = true, length = 1)
    private String acctCreditCheck;
    @Basic
    @Column(name = "ACCT_CURRENCY", nullable = true, length = 15)
    private String acctCurrency;
    @Basic
    @Column(name = "ACCT_MIN_RECEIPT_LIMIT", nullable = true, precision = 0)
    private BigInteger acctMinReceiptLimit;
    @Basic
    @Column(name = "ACCT_CREDIT_LIMIT", nullable = true, precision = 0)
    private BigInteger acctCreditLimit;
    @Basic
    @Column(name = "RELATED_ACCOUNT", nullable = true, length = 360)
    private String relatedAccount;
    @Basic
    @Column(name = "ACCOUNT_RELATION_STATUS", nullable = true, length = 80)
    private String accountRelationStatus;
    @Basic
    @Column(name = "RECIPROCAL_CHECK_BOX", nullable = true, length = 1)
    private String reciprocalCheckBox;
    @Basic
    @Column(name = "ORGANIZATION_NAME", nullable = true, length = 240)
    private String organizationName;
    @Basic
    @Column(name = "CUSTOMER_SITE_NAME", nullable = true, length = 360)
    private String customerSiteName;
    @Basic
    @Column(name = "ADDRESS_1", nullable = true, length = 240)
    private String address1;
    @Basic
    @Column(name = "ADDRESS_2", nullable = true, length = 240)
    private String address2;
    @Basic
    @Column(name = "ADDRESS_3", nullable = true, length = 240)
    private String address3;
    @Basic
    @Column(name = "ADDRESS_4", nullable = true, length = 240)
    private String address4;
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
    @Column(name = "COUNTRY", nullable = true, length = 60)
    private String country;
    @Basic
    @Column(name = "POSTAL_CODE", nullable = true, length = 60)
    private String postalCode;
    @Basic
    @Column(name = "BILL_TO_FLAG", nullable = true, length = 1)
    private String billToFlag;
    @Basic
    @Column(name = "SHIP_TO_FLAG", nullable = true, length = 1)
    private String shipToFlag;
    @Basic
    @Column(name = "BILL_TO_LOCATION", nullable = true, length = 40)
    private String billToLocation;
    @Basic
    @Column(name = "SHIP_TO_LOCATION", nullable = true, length = 40)
    private String shipToLocation;
    @Basic
    @Column(name = "BILL_SALES_TERRITORY", nullable = true, length = 40)
    private String billSalesTerritory;
    @Basic
    @Column(name = "BILL_SALES_PERSON", nullable = true, length = 240)
    private String billSalesPerson;
    @Basic
    @Column(name = "SALES_PERSON_PHONE", nullable = true, length = 40)
    private String salesPersonPhone;
    @Basic
    @Column(name = "PRIMARY_FLAG", nullable = true, length = 1)
    private String primaryFlag;
    @Basic
    @Column(name = "SITE_PROFILE_CLASS", nullable = true, length = 30)
    private String siteProfileClass;
    @Basic
    @Column(name = "SITE_COLLECTOR", nullable = true, length = 30)
    private String siteCollector;
    @Basic
    @Column(name = "SITE_COLLECTOR_PHONE", nullable = true, length = 20)
    private String siteCollectorPhone;
    @Basic
    @Column(name = "BILL_PAYMENT_TERM", nullable = true, length = 15)
    private String billPaymentTerm;
    @Basic
    @Column(name = "SITE_CREDIT_HOLD", nullable = true, length = 1)
    private String siteCreditHold;
    @Basic
    @Column(name = "SITE_CREDIT_CHECK", nullable = true, length = 1)
    private String siteCreditCheck;
    @Basic
    @Column(name = "SITE_CURRENCY_CODE", nullable = true, length = 15)
    private String siteCurrencyCode;
    @Basic
    @Column(name = "SITE_MIN_RECEIPT_LIMIT", nullable = true, precision = 0)
    private BigInteger siteMinReceiptLimit;
    @Basic
    @Column(name = "SITE_CREDIT_LIMIT", nullable = true, precision = 0)
    private BigInteger siteCreditLimit;
    @Basic
    @Column(name = "SITE_PHONE_COUNTRY", nullable = true, length = 10)
    private String sitePhoneCountry;
    @Basic
    @Column(name = "SITE_PHONE_AREA", nullable = true, length = 10)
    private String sitePhoneArea;
    @Basic
    @Column(name = "SITE_PHONE_NUMBER", nullable = true, length = 30)
    private String sitePhoneNumber;
    @Basic
    @Column(name = "SITE_FAX_COUNTRY_CODE", nullable = true, length = 10)
    private String siteFaxCountryCode;
    @Basic
    @Column(name = "SITE_FAX_AREA_CODE", nullable = true, length = 10)
    private String siteFaxAreaCode;
    @Basic
    @Column(name = "SITE_FAX_NUMBER", nullable = true, length = 30)
    private String siteFaxNumber;
    @Basic
    @Column(name = "SITE_EMAIL_ADDRESS", nullable = true, length = 240)
    private String siteEmailAddress;
    @Basic
    @Column(name = "PRIMARY_PAY_METHOD", nullable = true, length = 1)
    private String primaryPayMethod;
    @Basic
    @Column(name = "PAYMENT_METHOD_NAME", nullable = true, length = 30)
    private String paymentMethodName;
    @Basic
    @Column(name = "PAY_METHOD_SATRT_DATE", nullable = true)
    private Date payMethodSatrtDate;
    @Basic
    @Column(name = "ORIG_SYSTEM_CUST_REF", nullable = true, length = 240)
    private String origSystemCustRef;
    @Basic
    @Column(name = "BILL_REV_ACCOUNT", nullable = true, length = 240)
    private String billRevAccount;
    @Basic
    @Column(name = "BILL_TO_RECEIVIABLE_ACCOUNT", nullable = true, length = 240)
    private String billToReceiviableAccount;
    @Basic
    @Column(name = "LEGACY_REF1", nullable = false, length = 150)
    private String legacyRef1;
    @Basic
    @Column(name = "LEGACY_REF2", nullable = true, length = 150)
    private String legacyRef2;
    @Basic
    @Column(name = "LEGACY_REF7", nullable = true, length = 150)
    private String legacyRef7;
    @Basic
    @Column(name = "LEGACY_REF9", nullable = true, length = 150)
    private String legacyRef9;
    @Basic
    @Column(name = "LEGACY_REF11", nullable = true, length = 150)
    private String legacyRef11;
    @Basic
    @Column(name = "LEGACY_REF12", nullable = false, length = 150)
    private String legacyRef12;
    @Basic
    @Column(name = "LEGACY_REF13", nullable = true, length = 150)
    private String legacyRef13;
    @Basic
    @Column(name = "LEGACY_REF14", nullable = true, length = 150)
    private String legacyRef14;
    @Basic
    @Column(name = "LEGACY_REF15", nullable = true, length = 150)
    private String legacyRef15;
    @Basic
    @Column(name = "LEGACY_REF16", nullable = true, length = 150)
    private String legacyRef16;
    @Basic
    @Column(name = "LEGACY_REF17", nullable = true, length = 150)
    private String legacyRef17;
    @Basic
    @Column(name = "LEGACY_REF18", nullable = true, length = 150)
    private String legacyRef18;
    @Basic
    @Column(name = "LEGACY_REF19", nullable = true, length = 150)
    private String legacyRef19;
    @Basic
    @Column(name = "LEGACY_REF20", nullable = true, length = 150)
    private String legacyRef20;
    @Basic
    @Column(name = "LEGACY_REF21", nullable = true, length = 150)
    private String legacyRef21;
    @Basic
    @Column(name = "ORG_ID", nullable = true, precision = 0)
    private BigInteger orgId;
    @Basic
    @Column(name = "ACCT_PAY_TERM_ID", nullable = true, precision = 0)
    private BigInteger acctPayTermId;
    @Basic
    @Column(name = "BILL_SALE_TERRITORY_ID", nullable = true, precision = 0)
    private BigInteger billSaleTerritoryId;
    @Basic
    @Column(name = "BILL_SALES_PERSON_ID", nullable = true, precision = 0)
    private BigInteger billSalesPersonId;
    @Basic
    @Column(name = "BILL_ORDER_TYPE_ID", nullable = true, precision = 0)
    private BigInteger billOrderTypeId;
    @Basic
    @Column(name = "BILL_PAYMENT_TERM_ID", nullable = true, precision = 0)
    private BigInteger billPaymentTermId;
    @Basic
    @Column(name = "ACCT_WAREHOUSE_ID", nullable = true, precision = 0)
    private BigInteger acctWarehouseId;
    @Basic
    @Column(name = "ACCT_PROFILE_CLASS_ID", nullable = true, precision = 0)
    private BigInteger acctProfileClassId;
    @Basic
    @Column(name = "GL_REC_ID", nullable = true, precision = 0)
    private BigInteger glRecId;
    @Basic
    @Column(name = "GL_REV_ID", nullable = true, precision = 0)
    private BigInteger glRevId;
    @Basic
    @Column(name = "GL_FREIGHT_ID", nullable = true, precision = 0)
    private BigInteger glFreightId;
    @Basic
    @Column(name = "BILL_PRICE_LIST_ID", nullable = true, precision = 0)
    private BigInteger billPriceListId;
    @Basic
    @Column(name = "BILL_WHARE_HOUSE_ID", nullable = true, precision = 0)
    private BigInteger billWhareHouseId;
    @Basic
    @Column(name = "SITE_PROFILE_CLASS_ID", nullable = true, precision = 0)
    private BigInteger siteProfileClassId;
    @Basic
    @Column(name = "ACCT_COLLECTOR_ID", nullable = true, precision = 0)
    private BigInteger acctCollectorId;
    @Basic
    @Column(name = "SITE_COLLECTOR_ID", nullable = true, precision = 0)
    private BigInteger siteCollectorId;
    @Basic
    @Column(name = "ACCT_PRICE_LIST_ID", nullable = true, precision = 0)
    private BigInteger acctPriceListId;
    @Basic
    @Column(name = "PARTY_ID", nullable = true, precision = 0)
    private BigInteger partyId;
    @Basic
    @Column(name = "CUST_ACCOUNT_ID", nullable = true, precision = 0)
    private BigInteger custAccountId;
    @Basic
    @Column(name = "PARTY_SITE_ID", nullable = true, precision = 0)
    private BigInteger partySiteId;
    @Basic
    @Column(name = "CUST_ACCT_PROFILE_ID", nullable = true, precision = 0)
    private BigInteger custAcctProfileId;
    @Basic
    @Column(name = "LOCATION_ID", nullable = true, precision = 0)
    private BigInteger locationId;
    @Basic
    @Column(name = "CUST_ACCT_SITE_ID", nullable = true, precision = 0)
    private BigInteger custAcctSiteId;
    @Basic
    @Column(name = "BILL_SITE_USE_ID", nullable = true, precision = 0)
    private BigInteger billSiteUseId;
    @Basic
    @Column(name = "SHIP_TO_LOCATION_ID", nullable = true, precision = 0)
    private BigInteger shipToLocationId;
    @Basic
    @Column(name = "CUST_SITE_PROFILE_ID", nullable = true, precision = 0)
    private BigInteger custSiteProfileId;
    @Basic
    @Column(name = "NEW_ACCOUNT_NUMBER", nullable = true, precision = 0)
    private BigInteger newAccountNumber;
    @Basic
    @Column(name = "BROKER_NAME", nullable = true, length = 150)
    private String brokerName;
}
