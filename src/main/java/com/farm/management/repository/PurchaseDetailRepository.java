package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.PurchaseDetail;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {
	
	@Query(value = "SELECT * FROM tb_purchase_detail p WHERE p.id_purchase = :purchaseId", nativeQuery = true)
    List<PurchaseDetail> getPurchaseDetailByPurchaseId(@Param("purchaseId") Long purchaseId);

}
