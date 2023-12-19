package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	@Query(value = "select * from tb_purchase where id_farm = :farmId", nativeQuery = true)
    List<Purchase> getPurchaseByFarmId(@Param("farmId") Long farmId);

}
