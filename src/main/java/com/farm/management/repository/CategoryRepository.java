package com.farm.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.management.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
