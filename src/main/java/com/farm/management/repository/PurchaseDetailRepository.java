package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.PurchaseDetail;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {
	
	@Query(value = "SELECT * FROM tb_purchase_detail d LEFT JOIN tb_purchase p ON p.id = d.id_purchase WHERE p.id_farm = :farmId", nativeQuery = true)
    List<PurchaseDetail> getPurchaseDetailByFarmId(@Param("farmId") Long farmId);

}
