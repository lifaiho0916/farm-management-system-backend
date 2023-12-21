package com.farm.management.service;

import java.util.List;

import com.farm.management.model.ProductionSale;

public interface ProductionSaleService {

	ProductionSale createProductionSale(ProductionSale productionSale);
    
	ProductionSale getProductionSaleById(Long id);

    ProductionSale updateProductionSale(ProductionSale productionSale);

    void deleteProductionSale(Long id);
    
    List<ProductionSale> getProductionSaleByFarmId(Long farmId);
}