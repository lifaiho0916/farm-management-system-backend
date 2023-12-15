package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Unit;

public interface UnitService {
	
	Unit createUnit(Unit unit);

	Unit getUnitById(Long id);

    List<Unit> getAllUnits();

	Unit updateUnit(Unit unit);

    void deleteUnit(Long id);

}
