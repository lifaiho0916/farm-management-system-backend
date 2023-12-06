package com.farm.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.management.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long>  {

}
