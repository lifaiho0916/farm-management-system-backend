package com.farm.management.service.impl;

import org.springframework.stereotype.Service;

import com.farm.management.model.Unit;
import com.farm.management.repository.UnitRepository;
import com.farm.management.service.UnitService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UnitServiceImpl implements UnitService {
	
	private UnitRepository usnitReqository;
	
	@Override
    public Unit createUnit(Unit unit) {
        return usnitReqository.save(unit);
    }

    @Override
    public Unit updateUnit(Unit unit) {
    	Unit existingUnit = usnitReqository.findById(unit.getId()).get();
    	existingUnit.setDescription(unit.getDescription());
    	existingUnit.setType(unit.getType());
        return usnitReqository.save(existingUnit);
    }

    @Override
    public void deleteUnit(Long id) {
    	usnitReqository.deleteById(id);
    }

}
