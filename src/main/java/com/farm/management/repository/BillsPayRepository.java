package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.BillsPay;

public interface BillsPayRepository extends JpaRepository<BillsPay, Long> {
	
	@Query(value = "select * from tb_bills_to_pay where id_purchase = :id", nativeQuery = true)
    List<BillsPay> getBillsPayByPurchaseId(@Param("id") Long id);

}
