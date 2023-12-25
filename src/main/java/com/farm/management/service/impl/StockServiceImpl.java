package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.Stock;
import com.farm.management.repository.StockRepository;
import com.farm.management.service.StockService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class StockServiceImpl implements StockService {
	
	private StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockById(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        return optionalStock.get();
    }

    @Override
    public Stock updateStock(Stock stock) {
    	Stock existingStock = stockRepository.findById(stock.getId()).get();
    	Stock updatedStock = stockRepository.save(existingStock);
        return updatedStock;
    }

    @Override
    public void deleteStock(Long id) {
    	stockRepository.deleteById(id);
    }
    
    public List<Stock> getStockByUserId(Long userId){
        return stockRepository.getStockByUserId(userId);
    }

}
