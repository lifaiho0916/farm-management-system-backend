package com.farm.management.service;

import com.farm.management.model.Unit;

public interface UnitService {
	
	Unit createUnit(Unit unit);

	Unit updateUnit(Unit unit);

    void deleteUnit(Long id);

}
