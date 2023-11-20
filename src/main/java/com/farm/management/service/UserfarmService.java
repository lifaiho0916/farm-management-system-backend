package com.farm.management.service;

import com.farm.management.model.Userfarm;

public interface UserfarmService {

    Userfarm createUserfarm(Userfarm userfarm);
    
    boolean isUserIdExist(Long userId);
    
    void updateUserFarmByUserId(Long userId, Long farmId);

    void deleteUserFarmByFarmId(Long farmId);
    
    void deleteUserFarmByUserId(Long userId);
}
