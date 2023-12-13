package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.Crop;
import com.farm.management.repository.CropRepository;
import com.farm.management.service.CropService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CropServiceImpl implements CropService {
	
	private CropRepository cropRepository;

    @Override
    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    @Override
    public Crop getCropById(Long id) {
        Optional<Crop> optionalCrop = cropRepository.findById(id);
        return optionalCrop.get();
    }

    @Override
    public Crop updateCrop(Crop crop) {
    	Crop existingCrop = cropRepository.findById(crop.getId()).get();
    	existingCrop.setFarm(crop.getFarm());
    	existingCrop.setDescription(crop.getDescription());
    	existingCrop.setYear(crop.getYear());
    	existingCrop.setStart_date(crop.getStart_date());
    	existingCrop.setEnd_date(crop.getEnd_date());
        return cropRepository.save(existingCrop);
    }

    @Override
    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }
    
    public List<Crop> getCropByFarmId(Long farmId){
        return cropRepository.getCropByFarmId(farmId);
    }

}
