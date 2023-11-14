package com.farm.management.repository;

import com.farm.management.model.UserLevel;
import com.farm.management.model.LevelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {
    Optional<UserLevel> findByName(LevelName levelName);
}
