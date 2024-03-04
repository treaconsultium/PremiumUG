package com.dailycode.premiumug.odi.repository;

import com.dailycode.premiumug.odi.model.OFApremiums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OFApremiumRepo extends JpaRepository<OFApremiums, Long> {

    @Query(value = "select count (*) as CountPremiums \n" +
            "        from xxjicke_ar_trans_stg_medis \n" +
            "       where     operating_unit =:operatingUnit\n" +
            "             and line_num = :entryLine \n" +
            "             and transaction_number = :journalId \n" +
            "             and legacy_policy_number = :policynumber \n" +
            "             and class = :invoiceType ", nativeQuery = true)
    Integer findIfExist(@Param("operatingUnit") String operatingUnit, @Param("entryLine") Integer entryLine, @Param("journalId") String journalId,
                        @Param("policynumber") String policynumber, @Param("invoiceType") String invoiceType);

    Optional<OFApremiums> findByOperatingUnitAndLineNumAndTransactionNumberAndLegacyPolicyNumberAndClazzAndUnitPrice(String operatingUnit, Integer entryLine,
                                                                                                                     String journalId, String policynumber,
                                                                                                                     String invoiceType, Double unitPrice);
}
