package com.farm.management.service;

import java.util.List;

import com.farm.management.model.ProductCrop;
import com.farm.management.model.User;

public interface ProductCropService {
	
	ProductCrop createProductionCrop(ProductCrop productionCrop);
	
	ProductCrop getProductCropById(Long productCropId);

    List<ProductCrop> getProductionCropByFarmId(Long farmId);
    
    List<ProductCrop> getProductionCropByUserId(Long userId);

    ProductCrop updateProductionCrop(ProductCrop productionCrop);

    void deleteProductionCrop(Long id);

}
