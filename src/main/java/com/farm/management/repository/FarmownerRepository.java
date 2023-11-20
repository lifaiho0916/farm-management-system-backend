package com.farm.management.repository;

import com.farm.management.model.Farmsowner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FarmownerRepository extends JpaRepository<Farmsowner, Long> {

	@Query(value = "DELETE FROM tb_farms_owner WHERE id_farm = :farmId", nativeQuery = true)
	void deleteFarmOwnerByFarmId(@Param("farmId") Long farmId);

}
