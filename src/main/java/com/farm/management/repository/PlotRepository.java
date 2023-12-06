package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Plot;

public interface PlotRepository extends JpaRepository<Plot, Long> {

	@Query(value = "select * from tb_plot where id_farm = :farmId", nativeQuery = true)
    List<Plot> getPlotByFarmId(@Param("farmId") Long farmId);
	
	@Query(value = "SELECT * FROM tb_plot p LEFT JOIN tb_farm f ON f.id = p.id_farm LEFT JOIN tb_farms_owner o ON o.id_farm = f.id WHERE o.id_users = :userId", nativeQuery = true)
	List<Plot> getPlotByUserId(@Param("userId") Long userId);
}
