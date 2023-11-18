package com.farm.management.repository;

import com.farm.management.model.Farm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FarmRepository {
    Optional<Farm> findByEmail(String email);

    Optional<Farm> findByUsernameOrEmail(String username, String email);

    List<Farm> findByIdIn(List<Long> userIds);

    Optional<Farm> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select * from tb_farm left join tb_farm_owner on tb_farm.id = tb_farm_owner.id_farm e where e.id_user like %:id_user%", nativeQuery = true)
    List<Farm> findById_user(@Param("id_user") Long id_user);
}