package com.dailycode.premiumug.odi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisModel implements Serializable {
    private int id;
    private String transactionType;
    private String transactionObject;
    private String logTime;
}
