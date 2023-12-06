package com.farm.management.service.impl;

import com.farm.management.model.Farm;
import com.farm.management.repository.FarmRepository;
import com.farm.management.repository.FarmOwnerRepository;
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
    private FarmOwnerRepository farmownerRepository;

    @Override
    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        Optional<Farm> optionalFarm = farmRepository.findById(id);
        return optionalFarm.get();
    }

    @Override
    public Farm updateFarm(Farm farm) {
        Farm existingFarm = farmRepository.findById(farm.getId()).get();
        existingFarm.setDescription(farm.getDescription());
        existingFarm.setAddress(farm.getAddress());
        existingFarm.setCity(farm.getCity());
        existingFarm.setZipcode(farm.getZipcode());
        existingFarm.setState(farm.getState());
        return farmRepository.save(existingFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }

    public List<Farm> findById_user(Long id){
        return farmRepository.findById_user(id);
    }

}

