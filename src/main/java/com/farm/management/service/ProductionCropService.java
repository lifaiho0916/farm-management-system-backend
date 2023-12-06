package com.farm.management.service;

import java.util.List;

import com.farm.management.model.ProductionCrop;

public interface ProductionCropService {
	
	ProductionCrop createProductionCrop(ProductionCrop productionCrop);

    List<ProductionCrop> getProductionCropByFarmId(Long farmId);
    
    List<ProductionCrop> getProductionCropByUserId(Long userId);

    ProductionCrop updateProductionCrop(ProductionCrop productionCrop);

    void deleteProductionCrop(Long id);

}
