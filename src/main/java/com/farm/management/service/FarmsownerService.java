package com.farm.management.service;

import com.farm.management.model.Farmsowner;

public interface FarmsownerService {

    Farmsowner createFarmowner(Farmsowner farmowner);

    void deleteFarmonwerByFarmId(Long farmId);
    
}
