package com.farm.management.service.impl;

import org.springframework.stereotype.Service;

import com.farm.management.model.Crop;
import com.farm.management.repository.CropRepository;
import com.farm.management.service.CropService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class CropServiceImpl implements CropService {
	
	private CropRepository cropRepository;

    @Override
    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    @Override
    public Crop updateCrop(Crop crop) {
    	Crop existingCrop = cropRepository.findById(crop.getId()).get();
    	existingCrop.setDescription(crop.getDescription());
    	existingCrop.setYear(crop.getYear());
        return cropRepository.save(existingCrop);
    }

    @Override
    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }

}
