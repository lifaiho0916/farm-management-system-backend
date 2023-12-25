package com.farm.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.management.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	@Query(value = "SELECT * FROM tb_stock_movement p WHERE p.id_users = :userId", nativeQuery = true)
	List<Stock> getStockByUserId(@Param("userId") Long userId);

}
