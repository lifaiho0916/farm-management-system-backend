package com.farm.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.management.model.Crop;

public interface CropRepository extends JpaRepository<Crop, Long>  {

}
