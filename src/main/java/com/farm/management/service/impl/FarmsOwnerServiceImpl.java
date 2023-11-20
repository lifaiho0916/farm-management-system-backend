package com.farm.management.service.impl;

import com.farm.management.model.FarmsOwner;
import com.farm.management.repository.FarmOwnerRepository;
import com.farm.management.service.FarmsOwnerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class FarmsOwnerServiceImpl implements FarmsOwnerService {

    private FarmOwnerRepository farmownerRepository;

    public FarmsOwner createFarmowner(FarmsOwner farmsowner) {
        return farmownerRepository.save(farmsowner);
    }

    @Override
    public void deleteFarmonwerByFarmId(Long farmId) {
        farmownerRepository.deleteFarmOwnerByFarmId(farmId);
    }
    
}
