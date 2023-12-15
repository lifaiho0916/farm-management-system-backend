package com.farm.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.management.model.Unit;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.UnitService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UnitController { 
	
	@Autowired
    private UnitService unitService;
	
	@PostMapping("unit")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Unit> createUnit(@RequestBody Unit unit, @CurrentUser UserPrincipal currentUser) {
		unit.setDescription(unit.getDescription());
		unit.setType(unit.getType());
	    Unit savedUnit = unitService.createUnit(unit);
	    return new ResponseEntity<>(savedUnit, HttpStatus.CREATED);
	}
	
	
	@GetMapping("unit/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Unit> getUnitById(@PathVariable("id") Long id){
	    Unit unit = unitService.getUnitById(id);
	    return new ResponseEntity<>(unit, HttpStatus.OK);
	}
	
	
	@GetMapping("units")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Unit>> getAllUnits(){
	    List<Unit> unit = unitService.getAllUnits();
	    return new ResponseEntity<>(unit, HttpStatus.OK);
	}
	
	// Build Update User REST API
	@PutMapping("unit/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Unit> updateUser(@PathVariable("id") Long id,
	                                       @RequestBody Unit unit){
	    unit.setId(unit.getId());
	    Unit updatedUnit = unitService.updateUnit(unit);
	    return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
	}
	
	// Build Delete User REST API
	@DeleteMapping("unit/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
	    unitService.deleteUnit(id);
	    return new ResponseEntity<>("Unit successfully deleted!", HttpStatus.OK);
	}

}
