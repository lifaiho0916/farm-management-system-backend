package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.ProductionSale;

public interface ProductionSaleRepository extends JpaRepository<ProductionSale, Long> {
	
	@Query(value = "SELECT * FROM tb_production_sales s LEFT JOIN tb_production_crop c ON c.id = s.id_production_crop WHERE c.id_farm = :farmId", nativeQuery = true)
    List<ProductionSale> getProductionSaleByFarmId(@Param("farmId") Long farmId);

}
