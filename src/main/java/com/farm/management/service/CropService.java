package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Crop;

public interface CropService {
	
	Crop createCrop(Crop crop);

    Crop getCropById(Long id);
	
	List<Crop> getCropByFarmId(Long farmId);

    Crop updateCrop(Crop crop);

    void deleteCrop(Long id);

}
