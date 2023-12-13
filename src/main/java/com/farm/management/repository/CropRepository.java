package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Crop;

public interface CropRepository extends JpaRepository<Crop, Long>  {
	@Query(value = "select * from tb_crop where id_farm = :farmId", nativeQuery = true)
    List<Crop> getCropByFarmId(@Param("farmId") Long farmId);

}
