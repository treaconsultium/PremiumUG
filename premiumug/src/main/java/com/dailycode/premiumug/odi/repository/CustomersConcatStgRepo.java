package com.dailycode.premiumug.odi.repository;


import com.dailycode.premiumug.odi.model.CustomersConctStg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersConcatStgRepo extends JpaRepository<CustomersConctStg, Long> {
}
