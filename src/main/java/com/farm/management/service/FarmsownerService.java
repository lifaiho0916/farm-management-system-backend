package com.farm.management.service;

import com.farm.management.model.Farm;
import com.farm.management.model.Farmsowner;
import com.farm.management.model.User;

public interface FarmsownerService {

    Farmsowner createFarmowner(Farmsowner farmowner);

    void deleteFarmonwerByFarmId(Long farmId);
}
