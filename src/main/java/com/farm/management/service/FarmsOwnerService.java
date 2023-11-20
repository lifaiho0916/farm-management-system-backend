package com.farm.management.service;

import com.farm.management.model.FarmsOwner;

public interface FarmsOwnerService {

    FarmsOwner createFarmowner(FarmsOwner farmowner);

    void deleteFarmonwerByFarmId(Long farmId);
    
}
