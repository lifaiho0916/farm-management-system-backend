package com.farm.management.repository;

import com.farm.management.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query(value = "SELECT * FROM tb_farm e LEFT JOIN tb_farms_owner p ON p.id_farm = e.id WHERE p.id_users = :id_user", nativeQuery = true)
    List<Farm> findById_user(@Param("id_user") Long id_user);
}