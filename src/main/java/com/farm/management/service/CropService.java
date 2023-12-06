package com.farm.management.service;

import com.farm.management.model.Crop;

public interface CropService {
	
	Crop createCrop(Crop crop);

    Crop updateCrop(Crop crop);

    void deleteCrop(Long id);

}
