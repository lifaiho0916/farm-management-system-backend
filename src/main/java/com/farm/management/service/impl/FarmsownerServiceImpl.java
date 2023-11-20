package com.farm.management.service.impl;

import com.farm.management.model.Farmsowner;
import com.farm.management.repository.FarmownerRepository;
import com.farm.management.service.FarmsownerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class FarmsownerServiceImpl implements FarmsownerService {

    private FarmownerRepository farmownerRepository;

    public Farmsowner createFarmowner(Farmsowner farmsowner) {
        return farmownerRepository.save(farmsowner);
    }

    @Override
    public void deleteFarmonwerByFarmId(Long farmId) {
        farmownerRepository.deleteFarmOwnerByFarmId(farmId);
    }
    
}
