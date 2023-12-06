package com.farm.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.management.model.ProductionCrop;
import com.farm.management.repository.ProductionCropRepository;
import com.farm.management.service.ProductionCropService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductionCropServiceImpl implements ProductionCropService {
	
	private ProductionCropRepository productionCropRepository;

	@Override
    public ProductionCrop createProductionCrop(ProductionCrop productionCrop) {
        return productionCropRepository.save(productionCrop);
    }
	
	@Override
    public void deleteProductionCrop(Long productionCropId) {
		productionCropRepository.deleteById(productionCropId);
    }

	@Override
	public ProductionCrop updateProductionCrop(ProductionCrop productionCrop) {
		
		ProductionCrop existingProductionCrop = productionCropRepository.findById(productionCrop.getId()).get();
		existingProductionCrop.setUnit(productionCrop.getUnit());
		existingProductionCrop.setCrop(productionCrop.getCrop());
		existingProductionCrop.setQuantity(productionCrop.getQuantity());
		ProductionCrop updatedPlot = productionCropRepository.save(existingProductionCrop);
        return updatedPlot;
	}

    public List<ProductionCrop> getProductionCropByFarmId(Long farmId){
        return productionCropRepository.getProductionCropByFarmId(farmId);
    }
    
    public List<ProductionCrop> getProductionCropByUserId(Long userId){
        return productionCropRepository.getProductionCropByUserId(userId);
    }

}
