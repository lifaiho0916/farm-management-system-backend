package com.farm.management.service.impl;

import com.farm.management.model.Farm;
import com.farm.management.repository.FarmRepository;
import com.farm.management.service.FarmService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {

    private FarmRepository farmRepository;

    @Override
    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm getFarmById(Long farmId) {
        Optional<Farm> optionalFarm = farmRepository.findById(farmId);
        return optionalFarm.get();
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public Farm updateFarm(Farm farm) {
        Farm existingFarm = farmRepository.findById(farm.getId()).get();
        existingFarm.setDescription(farm.getDescription());
        existingFarm.setAddress(farm.getAddress());
        existingFarm.setCity(farm.getCity());
        existingFarm.setZipcode(farm.getZipcode());
        existingFarm.setStates(farm.getStates());
        Farm updatedFarm = farmRepository.save(existingFarm);
        return updatedFarm;
    }

    @Override
    public void deleteFarm(Long farmId) {
        farmRepository.deleteById(farmId);
    }
}

