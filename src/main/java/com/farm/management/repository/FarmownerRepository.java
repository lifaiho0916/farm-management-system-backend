package com.farm.management.repository;

import com.farm.management.model.Farmsowner;
import com.farm.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmownerRepository extends JpaRepository<Farmsowner, Long> {

    @Query(value = "DELETE from tb_farms_owner WHERE id_farm is %:farmId%", nativeQuery = true)
    void deleteFarmOwnerByFarmId(@Param("farmId") Long farmId);

}
