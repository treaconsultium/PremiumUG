package com.dailycode.premiumug.odi.repository;

import com.dailycode.premiumug.odi.model.CustomerSTG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StagingCustRepo extends JpaRepository<CustomerSTG, Long> {

    @Query(value = "SELECT DISTINCT LEGACY_REF1 \n" +
            "FROM XXJICKE_AR_CUSTOMERS_STG \n" +
            "WHERE ORGANIZATION_NAME  =:transferSource \n" +
            "AND LEGACY_REF12 = TO_CHAR(:policyHolderId)\n" +
            "AND LEGACY_REF13 =:policyNumber \n" +
            "AND ROWNUM = 1 ", nativeQuery = true)
    String fetchStagingPin(@Param("transferSource") String transferSource, @Param("policyHolderId") String policyHolderId,
                           @Param("policyNumber") String policyNumber);

    @Query(value = "SELECT count(*) \n" +
            "           FROM XXJICKE_AR_CUSTOMERS_STG \n" +
            "WHERE \n" +
            "ORGANIZATION_NAME =:operatingUnit\n" +
            "AND CUSTOMER_SITE_NAME =:entityId\n" +
            "AND LEGACY_REF13 =:polNumber\n" +
            "AND LEGACY_REF1 =:pin " +
            "fetch first 1 rows only", nativeQuery = true)
    Integer findIfAvailable(@Param("operatingUnit") String operatingUnit, @Param("entityId") String entityId,
                            @Param("polNumber") String polNumber, @Param("pin") String pin);

    @Query(value = "SELECT legacy_ref20 \n" +
            "FROM XXJICKE_AR_CUSTOMERS_STG \n" +
            "WHERE \n" +
            "ORGANIZATION_NAME =:operatingUnit \n" +
            "AND CUSTOMER_SITE_NAME =:entityId \n" +
            "AND LEGACY_REF13 =:polNumber \n" +
            "AND LEGACY_REF1 =:pin " +
            "fetch first 1 rows only", nativeQuery = true)
    Optional<String> findAvailablePin(@Param("operatingUnit") String operatingUnit, @Param("entityId") String entityId,
                                      @Param("polNumber") String polNumber, @Param("pin") String pin);

    @Transactional
    @Modifying
    @Query(value = "update xxjicke_ar_customers_stg set legacy_ref20 =:portfolio " +
            "where organization_name =:operatingUnit " +
            "and customer_site_name =:entityId " +
            "and legacy_ref13 =:polNumber " +
            "and legacy_ref1 =:pinNo ", nativeQuery = true)
    void updatePortfolio(@Param("operatingUnit") String operatingUnit, @Param("entityId") String entityId,
                         @Param("polNumber") String polNumber, @Param("pinNo") String pinNo, @Param("portfolio") String portfolio);
}
