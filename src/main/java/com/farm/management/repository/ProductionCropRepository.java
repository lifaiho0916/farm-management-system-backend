package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.ProductionCrop;

public interface ProductionCropRepository extends JpaRepository<ProductionCrop, Long> {
	
	@Query(value = "select * from tb_production_crop where id_farm = :farmId", nativeQuery = true)
    List<ProductionCrop> getProductionCropByFarmId(@Param("farmId") Long farmId);
	
	@Query(value = "SELECT * FROM tb_production_crop p LEFT JOIN tb_farm f ON f.id = p.id_farm LEFT JOIN tb_farms_owner o ON o.id_farm = f.id WHERE o.id_users = :userId", nativeQuery = true)
	List<ProductionCrop> getProductionCropByUserId(@Param("userId") Long userId);

}
