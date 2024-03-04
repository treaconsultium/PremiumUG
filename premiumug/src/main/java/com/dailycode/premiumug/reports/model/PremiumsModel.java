package com.dailycode.premiumug.reports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OFAPremiums", schema = "dbo",catalog = "DIGITAL_APPS")
public class PremiumsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "COUNTRYID", nullable = false, length = 2)
    private String countryid;
    @Basic
    @Column(name = "POLICYHOLDERID", nullable = true, length = 30)
    private String policyholderid;
    @Basic
    @Column(name = "ENTRYLINE", nullable = true)
    private Integer entryline;
    @Basic
    @Column(name = "ENTRYLINEDESC", nullable = false, length = 12)
    private String entrylinedesc;
    @Basic
    @Column(name = "ACCOUNTCODE", nullable = true, length = 50)
    private String accountcode;
    @Basic
    @Column(name = "POSTINGDATE", nullable = true)
    private Date postingdate;
    @Basic
    @Column(name = "JOURNALID", nullable = true, length = 30)
    private String journalid;
    @Basic
    @Column(name = "JOURNALDESC", nullable = true, length = 255)
    private String journaldesc;
    @Basic
    @Column(name = "CURRENCYNAME", nullable = true, length = 50)
    private String currencyname;
    @Basic
    @Column(name = "POLICYNUMBER", nullable = true, length = 27)
    private String policynumber;
    @Basic
    @Column(name = "ACCOUNTNAME", nullable = true, length = 100)
    private String accountname;
    @Basic
    @Column(name = "BROKED", nullable = true, length = 50)
    private String broked;
    @Basic
    @Column(name = "ACCOUNTID", nullable = true)
    private Integer accountid;
    @Basic
    @Column(name = "SOURCECOMPANY", nullable = true, length = 20)
    private String sourcecompany;
    @Basic
    @Column(name = "AMOUNT", nullable = true, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "GROSS_AMOUNT", nullable = true, precision = 2)
    private BigDecimal grossAmount;
    @Basic
    @Column(name = "INTERMEDIARYID", nullable = true, length = 30)
    private String intermediaryid;
    @Basic
    @Column(name = "INTERMEDIARYNAME", nullable = true, length = 255)
    private String intermediaryname;
    @Basic
    @Column(name = "BRANCHCODE", nullable = true, length = 3)
    private String branchcode;
    @Basic
    @Column(name = "BRANCHNAME", nullable = true, length = 30)
    private String branchname;
    @Basic
    @Column(name = "LOGTIME")
    private Date logtime;
    @Basic
    @Column(name = "PICKED_STATUS")
    private Integer pickedStatus;
    @Basic
    @Column(name = "PICKED_DATE")
    private LocalDateTime pickedTime;
    @Basic
    @Column(name = "PICKED_STATUS_MSG")
    private String pickedStatusMsg;
}
