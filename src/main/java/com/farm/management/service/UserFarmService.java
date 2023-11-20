package com.farm.management.service;

import com.farm.management.model.UserFarm;

public interface UserFarmService {

    UserFarm createUserfarm(UserFarm userfarm);
    
    boolean isUserIdExist(Long userId);
    
    void updateUserFarmByUserId(Long userId, Long farmId);

    void deleteUserFarmByFarmId(Long farmId);
    
    void deleteUserFarmByUserId(Long userId);
}
