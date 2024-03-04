package com.dailycode.premiumug.reports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OFA_Transactions", schema = "dbo", catalog = "DIGITAL_APPS")
public class OfaTransactions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false, precision = 0)
    private long id;
    @Basic
    @Column(name = "Transaction_Id")
    private String transactionId;
    @Basic
    @Column(name = "Group_Id", nullable = true, precision = 0)
    private Integer groupId;
    @Basic
    @Column(name = "Send_Status", nullable = true)
    private Integer sendStatus;
    @Basic
    @Column(name = "Send_Message", nullable = true, length = -1)
    private String sendMessage;
    @Basic
    @Column(name = "Send_time", nullable = true)
    private LocalDateTime sendTime;
    @Basic
    @Column(name = "Log_Time", nullable = true)
    private LocalDateTime logTime;
    @Basic
    @Column(name = "Error_Message", nullable = true)
    private String errorMessage;
    @Basic
    @Column(name = "Type_Description", nullable = true)
    private String typeDescription;
}
