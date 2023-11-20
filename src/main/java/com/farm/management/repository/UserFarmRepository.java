package com.farm.management.repository;

import com.farm.management.model.UserFarm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserFarmRepository extends JpaRepository<UserFarm, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tb_user_permission WHERE id_farm = :farmId", nativeQuery = true)
    void deleteUserFarmByFarmId(@Param("farmId") Long farmId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE tb_user_permission SET id_farm = :farmId WHERE id_users = :userId", nativeQuery = true)
	void updateUserFarmByUserId(@Param("userId") Long userId, @Param("farmId") Long farmId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tb_user_permission WHERE id_users = :userId", nativeQuery = true)
	void deleteUserFarmByUserId(@Param("userId") Long userId);
	
	@Query(value = "SELECT * FROM tb_user_permission WHERE id_users = :userId", nativeQuery = true)
	List<UserFarm> getUserFarmByUserId(@Param("userId") Long userId);
}
