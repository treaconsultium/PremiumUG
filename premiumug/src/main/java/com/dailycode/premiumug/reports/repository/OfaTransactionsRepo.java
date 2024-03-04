package com.dailycode.premiumug.reports.repository;

import com.dailycode.premiumug.reports.model.OfaTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfaTransactionsRepo extends JpaRepository<OfaTransactions,Long> {
    @Query(value = "select distinct Transaction_Id as TransactionId " +
            "from OFA_Transactions \n" +
            "where Group_Id =:status \n" +
            "and cast(Send_time as date) >= (GETDATE() -10)",nativeQuery = true)
    List<String> fetchProcessed(@Param("status") int status);
}
