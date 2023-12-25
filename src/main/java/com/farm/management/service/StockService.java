package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Stock;

public interface StockService {

	Stock createStock(Stock stock);

	Stock getStockById(Long id);
    
    List<Stock> getStockByUserId(Long userId);

    Stock updateStock(Stock stock);

    void deleteStock(Long id);

}
