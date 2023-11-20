package com.farm.management.service;

import com.farm.management.model.Farm;
import com.farm.management.model.FarmsOwner;

import java.util.List;

public interface FarmService {

    Farm createFarm(Farm farm);

    Farm getFarmById(Long id);

    List<Farm> findById_user(Long id);

    Farm updateFarm(Farm farm);

    void deleteFarm(Long farmId);

}
