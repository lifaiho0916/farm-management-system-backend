package com.farm.management.repository;

import com.farm.management.model.Farmsowner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FarmownerRepository extends JpaRepository<Farmsowner, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tb_farms_owner WHERE id_farm = :farmId", nativeQuery = true)
	void deleteFarmOwnerByFarmId(@Param("farmId") Long farmId);

}
