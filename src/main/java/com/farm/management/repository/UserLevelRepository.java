package com.farm.management.repository;

import com.farm.management.model.UserLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {
	
	@Query(value = "SELECT * FROM tb_user_level WHERE description = :levelName", nativeQuery = true)
    Optional<UserLevel> findByDescription(@Param("levelName") String levelName);
}
