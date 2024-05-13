package com.dailycode.premiumug.odi.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "XXJICUG_AR_TRANS_STG_MEDIS", schema = "XXJIC", catalog = "")
public class OFApremiums {

    @Id
    @SequenceGenerator(name = "seq1",sequenceName = "xxjicke_ar_transaction_seq",allocationSize = 1,schema = "XXJIC")
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "seq1")
    @Column(name = "SEQ_ID", nullable = false, precision = 0)
    private Long seqId;
    @Basic
    @Column(name = "TRANSACTION_SOURCE", nullable = false, length = 100)
    private String transactionSource;
    @Basic
    @Column(name = "TRANSACTION_NUMBER", nullable = false, length = 50)
    private String transactionNumber;
    @Basic
    @Column(name = "AMOUNT", nullable = false, length = 20)
    private String amount;
    @Basic
    @Column(name = "CLASS", nullable = false, length = 30)
    private String clazz;
    @Basic
    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDate transactionDate;
    @Basic
    @Column(name = "GL_DATE", nullable = false)
    private LocalDate glDate;
    @Basic
    @Column(name = "TRANSACTION_TYPE", nullable = false, length = 30)
    private String transactionType;
    @Basic
    @Column(name = "REFERENCE", nullable = true, length = 200)
    private String reference;
    @Basic
    @Column(name = "CURRENCY_CODE", nullable = false, length = 30)
    private String currencyCode;
    @Basic
    @Column(name = "PAYMENT_TERMS", nullable = false, length = 30)
    private String paymentTerms;
    @Basic
    @Column(name = "DUE_DATE", nullable = true)
    private LocalDate dueDate;
    @Basic
    @Column(name = "OPERATING_UNIT", nullable = false, length = 240)
    private String operatingUnit;
    @Basic
    @Column(name = "LEDGER", nullable = false, length = 240)
    private String ledger;
    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = false, length = 360)
    private String customerName;
    @Basic
    @Column(name = "CUST_ACC_NAME", nullable = false, length = 360)
    private String custAccName;
    @Basic
    @Column(name = "CUST_ACC_SITE_NAME", nullable = false, length = 360)
    private String custAccSiteName;
    @Basic
    @Column(name = "BILL_TO_SITE_NAME", nullable = false, length = 60)
    private String billToSiteName;
    @Basic
    @Column(name = "COMMENTS", nullable = true, length = 360)
    private String comments;
    @Basic
    @Column(name = "SHIP_TO_CUST_NAME", nullable = true, length = 360)
    private String shipToCustName;
    @Basic
    @Column(name = "SHIP_TO_SITE_NAME", nullable = true, length = 360)
    private String shipToSiteName;
    @Basic
    @Column(name = "PAYMENT_METHOD", nullable = true, length = 30)
    private String paymentMethod;
    @Basic
    @Column(name = "FRN_CURR_ERATE_TYPE", nullable = true, length = 30)
    private String frnCurrErateType;
    @Basic
    @Column(name = "FRN_CURR_ERATE_DATE", nullable = true)
    private LocalDate frnCurrErateDate;
    @Basic
    @Column(name = "EXCHANGE_RATE", nullable = true, precision = 0)
    private Double exchangeRate;
    @Basic
    @Column(name = "SOLD_TO_CUST_NAME", nullable = true, length = 360)
    private String soldToCustName;
    @Basic
    @Column(name = "PAYING_CUST_NAME", nullable = true, length = 360)
    private String payingCustName;
    @Basic
    @Column(name = "PAYING_CUST_LOCATION", nullable = true, length = 360)
    private String payingCustLocation;
    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 240)
    private String description;
    @Basic
    @Column(name = "LINE_NUM", nullable = false, precision = 0)
    private Integer lineNum;
    @Basic
    @Column(name = "QUANTITY", nullable = false, precision = 0)
    private Integer quantity;
    @Basic
    @Column(name = "UNIT_PRICE", nullable = false, precision = 0)
    private Double unitPrice;
    @Basic
    @Column(name = "OPEN_AMOUNT", nullable = true, precision = 0)
    private Double openAmount;
    @Basic
    @Column(name = "TAX_EXEMPT_NUMBER", nullable = true, length = 80)
    private String taxExemptNumber;
    @Basic
    @Column(name = "TAX_REGIME", nullable = true, length = 80)
    private String taxRegime;
    @Basic
    @Column(name = "TAX_RATE", nullable = true, precision = 0)
    private BigInteger taxRate;
    @Basic
    @Column(name = "LEGACY_SOURCE_NAME", nullable = false, length = 150)
    private String legacySourceName;
    @Basic
    @Column(name = "LEGACY_NATIONAL_IDENTIFIER", nullable = true, length = 150)
    private String legacyNationalIdentifier;
    @Basic
    @Column(name = "LEGACY_SOURCEVALUE", nullable = false, length = 150)
    private String legacySourcevalue;
    @Basic
    @Column(name = "LEGACY_POLICY_NUMBER", nullable = false, length = 150)
    private String legacyPolicyNumber;
    @Basic
    @Column(name = "LEGACY_REF1", nullable = true, length = 150)
    private String legacyRef1;
    @Basic
    @Column(name = "LEGACY_REF2", nullable = true, length = 150)
    private String legacyRef2;
    @Basic
    @Column(name = "PROCESS_FLAG", nullable = true, length = 2)
    private String processFlag;
    @Basic
    @Column(name = "ERROR_MESSAGE", nullable = true, length = 1000)
    private String errorMessage;
    @Basic
    @Column(name = "CREATED_BY", nullable = true, precision = 0)
    private Integer createdBy;
    @Basic
    @Column(name = "CREATION_DATE", nullable = true)
    private LocalDate creationDate;
    @Basic
    @Column(name = "LAST_UPDATED_BY", nullable = true, precision = 0)
    private Integer lastUpdatedBy;
    @Basic
    @Column(name = "LAST_UPDATE_DATE", nullable = true)
    private LocalDate lastUpdateDate;
    @Basic
    @Column(name = "LAST_UPDATE_LOGIN", nullable = true, precision = 0)
    private Integer lastUpdateLogin;
    @Basic
    @Column(name = "REQUEST_ID", nullable = true, precision = 0)
    private BigInteger requestId;
    @Basic
    @Column(name = "LINE_AMOUNT", nullable = true, precision = 0)
    private Double lineAmount;
    @Basic
    @Column(name = "ORG_ID", nullable = true, precision = 0)
    private BigInteger orgId;
    @Basic
    @Column(name = "TRANSACTION_SOURCE_ID", nullable = true, precision = 0)
    private BigInteger transactionSourceId;
    @Basic
    @Column(name = "CUST_ACCT_ID", nullable = true, precision = 0)
    private BigInteger custAcctId;
    @Basic
    @Column(name = "PAYING_SITE_USE_ID", nullable = true, precision = 0)
    private BigInteger payingSiteUseId;
    @Basic
    @Column(name = "BILL_TO_CUST_ID", nullable = true, precision = 0)
    private BigInteger billToCustId;
    @Basic
    @Column(name = "BILL_TO_ADD_ID", nullable = true, precision = 0)
    private BigInteger billToAddId;
    @Basic
    @Column(name = "SHIP_TO_CUST_ID", nullable = true, precision = 0)
    private BigInteger shipToCustId;
    @Basic
    @Column(name = "SOLD_TO_CUST_ID", nullable = true, precision = 0)
    private BigInteger soldToCustId;
    @Basic
    @Column(name = "SHIP_TO_ADD_ID", nullable = true, precision = 0)
    private BigInteger shipToAddId;
    @Basic
    @Column(name = "PAYING_CUST_ID", nullable = true, precision = 0)
    private BigInteger payingCustId;
    @Basic
    @Column(name = "BILL_PARTY_SITE_ID", nullable = true, precision = 0)
    private Integer billPartySiteId;
    @Basic
    @Column(name = "TERM_ID", nullable = true, precision = 0)
    private BigInteger termId;
    @Basic
    @Column(name = "CUST_TRX_TYPE_ID", nullable = true, precision = 0)
    private BigInteger custTrxTypeId;
    @Basic
    @Column(name = "INT_LINE_ATT6", nullable = true, length = 10)
    private String intLineAtt6;
    @Basic
    @Column(name = "INT_LINE_ATT7", nullable = true, length = 10)
    private String intLineAtt7;
    @Basic
    @Column(name = "INT_LINE_ATT8", nullable = true, length = 10)
    private String intLineAtt8;
    @Basic
    @Column(name = "CURR_EXC_RATE_NEW", nullable = true, precision = 0)
    private BigInteger currExcRateNew;
    @Basic
    @Column(name = "INTERFACE_ID", nullable = true, precision = 0)
    private BigInteger interfaceId;
    @Basic
    @Column(name = "CUST_ACCT_NAME", nullable = true, length = 160)
    private String custAcctName;
    @Basic
    @Column(name = "SHIP_TO_PARTY_SITE_ID", nullable = true, precision = 0)
    private BigInteger shipToPartySiteId;
    @Basic
    @Column(name = "SHIP_PARTY_ID", nullable = true, precision = 0)
    private BigInteger shipPartyId;
    @Basic
    @Column(name = "PAYING_PARTY_ID", nullable = true, precision = 0)
    private BigInteger payingPartyId;
    @Basic
    @Column(name = "CUST_PARTY_SITE_ID", nullable = true, precision = 0)
    private BigInteger custPartySiteId;
    @Basic
    @Column(name = "CUST_PARTY_ID", nullable = true, precision = 0)
    private BigInteger custPartyId;
    @Basic
    @Column(name = "BROKER_NAME", nullable = true, length = 150)
    private String brokerName;
    @Basic
    @Column(name = "BROKER_CODE", nullable = true, length = 150)
    private String brokerCode;
    @Basic
    @Column(name = "INSURED_NAME", nullable = true, length = 240)
    private String insuredName;
//    @Basic
//    @Column(name = "INSURED_CODE", nullable = true, length = 240)
//    private String insuredCode;
//    @Basic
//    @Column(name = "CLAIM_NO", nullable = true, length = 240)
//    private String claimNo;
//    @Basic
//    @Column(name = "DATE_OF_LOSS", nullable = true, length = 240)
//    private String dateOfLoss;
//    @Basic
//    @Column(name = "PRODUCT_DESC", nullable = true, length = 240)
//    private String productDesc;
}
