package com.dailycode.premiumug.odi.repository;

import com.dailycode.premiumug.odi.model.DailyRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyRatesRepo extends JpaRepository<DailyRates, LocalDate> {
    @Query(value = "select from_currency as FromCurrency, conversion_date as ConversionDate," +
            "conversion_type as ConversionType, conversion_rate as ConversionRate " +
            "from apps.xxjic_gl_daily_rates " +
            "where conversion_date =:postingDate", nativeQuery = true)
    Optional<RatesInterface> fetchConversionRates(@Param("postingDate") LocalDate postingDate);


    @Query(value = "select from_currency as FromCurrency, conversion_date as ConversionDate," +
            "conversion_type as ConversionType, conversion_rate as ConversionRate " +
            "from apps.xxjic_gl_daily_rates " +
            "where conversion_date between (:postingDate + INTERVAL '-1' DAY) and :postingDate", nativeQuery = true)
    Optional<RatesInterface> fetchConversionRatesDiff(@Param("postingDate") LocalDate parse);
}
