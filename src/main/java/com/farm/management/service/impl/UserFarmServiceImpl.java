package com.farm.management.service.impl;

import com.farm.management.model.UserFarm;
import com.farm.management.repository.UserFarmRepository;
import com.farm.management.service.UserFarmService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class UserFarmServiceImpl implements UserFarmService {

    private UserFarmRepository userfarmRepository;

    public UserFarm createUserfarm(UserFarm userfarm) {
        return userfarmRepository.save(userfarm);
    }
    
    @Override
    public boolean isUserIdExist(Long userId) {
    	List<UserFarm> userfarms = userfarmRepository.getUserFarmByUserId(userId);
    	return !userfarms.isEmpty();
    }
    
    @Override
    public void updateUserFarmByUserId(Long userId, Long farmId) {
    	userfarmRepository.updateUserFarmByUserId(userId, farmId);
    }
   
    
    @Override
    public void deleteUserFarmByFarmId(Long farmId) {
        userfarmRepository.deleteUserFarmByFarmId(farmId);
    }
    
    @Override
    public void deleteUserFarmByUserId(Long userId) {
    	userfarmRepository.deleteUserFarmByUserId(userId);
    }
}
