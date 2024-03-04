package com.dailycode.premiumug.odi.repository;

import java.time.LocalDate;

public interface RatesInterface {
    public String getFromCurrency();
    public LocalDate getConversionDate();
    public String getConversionType();
    public Double getConversionRate();
}
