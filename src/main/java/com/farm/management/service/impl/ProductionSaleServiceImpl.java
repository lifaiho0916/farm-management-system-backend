package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.ProductionSale;
import com.farm.management.repository.ProductionSaleRepository;
import com.farm.management.service.ProductionSaleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductionSaleServiceImpl implements ProductionSaleService {

    private ProductionSaleRepository productionSaleRepository;

    @Override
    public ProductionSale createProductionSale(ProductionSale productionSale) {
        return productionSaleRepository.save(productionSale);
    }

    @Override
    public ProductionSale getProductionSaleById(Long id) {
        Optional<ProductionSale> optionalProductionSale = productionSaleRepository.findById(id);
        return optionalProductionSale.get();
    }

    @Override
    public ProductionSale updateProductionSale(ProductionSale productionSale) {
    	ProductionSale existingProductionSale = productionSaleRepository.findById(productionSale.getId()).get();
    	existingProductionSale.setSupplier(productionSale.getSupplier());
    	existingProductionSale.setProductCrop(productionSale.getProductCrop());
    	existingProductionSale.setSaleDate(productionSale.getSaleDate());
    	existingProductionSale.setQuotes(productionSale.getQuotes());
    	existingProductionSale.setAmountMoney(productionSale.getAmountMoney());
    	existingProductionSale.setPaymentDate(productionSale.getPaymentDate());
        return productionSaleRepository.save(existingProductionSale);
    }

    @Override
    public void deleteProductionSale(Long id) {
    	productionSaleRepository.deleteById(id);
    }

    public List<ProductionSale> getProductionSaleByFarmId(Long farmId){
        return productionSaleRepository.getProductionSaleByFarmId(farmId);
    }

}
