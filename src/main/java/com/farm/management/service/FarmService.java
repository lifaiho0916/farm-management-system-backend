package com.farm.management.service;


import com.farm.management.model.Farm;

import java.util.List;

public interface FarmService {

    Farm createFarm(Farm farm);

    Farm getFarmById(Long farmId);

    List<Farm> getAllFarms();

    Farm updateFarm(Farm farm);

    void deleteFarm(Long farmId);
}
