package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	@Query(value = "select * from tb_suppliers where id_farm = :farmId", nativeQuery = true)
    List<Supplier> getSupplierByFarmId(@Param("farmId") Long farmId);
}
