package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	@Query(value = "SELECT * FROM tb_purchase WHERE id_farm = :farmId", nativeQuery = true)
    List<Purchase> getPurchaseByFarmId(@Param("farmId") Long farmId);
	
	@Query(value = "SELECT * FROM tb_purchase p LEFT JOIN tb_farm f ON f.id = p.id_farm LEFT JOIN tb_farms_owner m ON m.id_farm = f.id WHERE m.id_users = :adminId", nativeQuery = true)
    List<Purchase> getPurchasesByAdminId(@Param("adminId") Long adminId);

}
