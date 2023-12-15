package com.farm.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.management.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
