package com.farm.management.repository;

import com.farm.management.model.Userfarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserfarmRepository extends JpaRepository<Userfarm, Long> {
	
	@Query(value = "DELETE FROM tb_user_permission WHERE id_farm = :farmId", nativeQuery = true)
    void deleteUserFarmByFarmId(@Param("farmId") Long farmId);
}
