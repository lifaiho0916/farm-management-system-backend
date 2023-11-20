package com.farm.management.service;

import com.farm.management.model.Userfarm;

public interface UserfarmService {

    Userfarm createUserfarm(Userfarm userfarm);

    Userfarm updateUserfarm(Userfarm userfarm);

    void deleteUserFarmByFarmId(Long farmId);
}
