package com.farm.management.service.impl;

import com.farm.management.model.Userfarm;
import com.farm.management.repository.UserfarmRepository;
import com.farm.management.service.UserfarmService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class UserfarmServiceImpl implements UserfarmService {

    private UserfarmRepository userfarmRepository;

    public Userfarm createUserfarm(Userfarm userfarm) {
        return userfarmRepository.save(userfarm);
    }

    public Userfarm updateUserfarm(Userfarm userfarm){
        Userfarm existingUserfarm = userfarmRepository.findById(userfarm.getId()).get();
        existingUserfarm.setFarm(userfarm.getFarm());
        existingUserfarm.setUser(userfarm.getUser());
        existingUserfarm.setDescription(userfarm.getDescription());
        Userfarm updatedUserfarm = userfarmRepository.save(existingUserfarm);
        return updatedUserfarm;
    };
    @Override
    public void deleteUserFarmByFarmId(Long farmId) {
        userfarmRepository.deleteUserFarmByFarmId(farmId);
    }
}
