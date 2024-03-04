package com.dailycode.premiumug.odi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "XXJIC_GL_DAILY_RATES", schema = "APPS")
public class DailyRates {
    private String fromCurrency;
    private String toCurrency;
    @Id
    private LocalDate conversionDate;
    private String conversionType;
    private Float conversionRate;
}
