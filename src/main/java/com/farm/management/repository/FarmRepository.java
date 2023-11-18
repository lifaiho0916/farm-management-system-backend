package com.farm.management.repository;

import com.farm.management.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query(value = "select * from tb_farm left join tb_farm_owner on tb_farm.id = tb_farm_owner.id_farm e where e.id_user like %:id_user%", nativeQuery = true)
    List<Farm> findById_user(@Param("id_user") Long id_user);
}