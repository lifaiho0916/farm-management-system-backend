package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.BillsReceive;

public interface BillsReceiveRepository extends JpaRepository<BillsReceive, Long> {
	
	@Query(value = "select * from tb_bills_to_receive where id_production_sales = :id", nativeQuery = true)
    List<BillsReceive> getBillsReceiveBySaleId(@Param("id") Long id);

}
