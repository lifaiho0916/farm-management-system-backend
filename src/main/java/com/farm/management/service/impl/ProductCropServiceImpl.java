package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.ProductCrop;
import com.farm.management.repository.ProductCropRepository;
import com.farm.management.service.ProductCropService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCropServiceImpl implements ProductCropService {
	
	private ProductCropRepository productCropRepository;

	@Override
    public ProductCrop createProductionCrop(ProductCrop productionCrop) {
        return productCropRepository.save(productionCrop);
    }
	
	@Override
    public ProductCrop getProductCropById(Long productCropId) {
        Optional<ProductCrop> optionalProductCrop = productCropRepository.findById(productCropId);
        return optionalProductCrop.get();
    }
	
	@Override
    public void deleteProductionCrop(Long productionCropId) {
		productCropRepository.deleteById(productionCropId);
    }

	@Override
	public ProductCrop updateProductionCrop(ProductCrop productionCrop) {
		
		ProductCrop existingProductCrop = productCropRepository.findById(productionCrop.getId()).get();
		existingProductCrop.setUnit(productionCrop.getUnit());
		existingProductCrop.setCrop(productionCrop.getCrop());
		existingProductCrop.setQuantity(productionCrop.getQuantity());
		ProductCrop updatedPlot = productCropRepository.save(existingProductCrop);
        return updatedPlot;
	}

    public List<ProductCrop> getProductionCropByFarmId(Long farmId){
        return productCropRepository.getProductionCropByFarmId(farmId);
    }
    
    public List<ProductCrop> getProductionCropByUserId(Long userId){
        return productCropRepository.getProductionCropByUserId(userId);
    }

}
