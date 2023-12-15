package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

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
	
	private UnitRepository unitReqository;
	
	@Override
    public Unit createUnit(Unit unit) {
        return unitReqository.save(unit);
    }

    @Override
    public Unit getUnitById(Long id) {
        Optional<Unit> optionalUnit = unitReqository.findById(id);
        return optionalUnit.get();
    }

    @Override
    public List<Unit> getAllUnits() {
        return unitReqository.findAll();
    }

    @Override
    public Unit updateUnit(Unit unit) {
    	Unit existingUnit = unitReqository.findById(unit.getId()).get();
    	existingUnit.setDescription(unit.getDescription());
    	existingUnit.setType(unit.getType());
        return unitReqository.save(existingUnit);
    }

    @Override
    public void deleteUnit(Long id) {
    	unitReqository.deleteById(id);
    }

}
