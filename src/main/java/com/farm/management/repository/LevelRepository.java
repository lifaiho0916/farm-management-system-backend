package com.farm.management.repository;

import com.farm.management.model.Level;
import com.farm.management.model.LevelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByName(LevelName levelName);
}
