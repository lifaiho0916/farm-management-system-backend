package com.farm.management.controller;

import com.farm.management.model.*;
import com.farm.management.repository.UserFarmRepository;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.FarmsOwnerService;
import com.farm.management.service.UserService;
import com.farm.management.service.UserFarmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class FarmController {

    @Autowired
    private FarmService farmService;
    
    @Autowired
    private UserFarmRepository userFarmRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFarmService userfarmService;

    @Autowired
    private FarmsOwnerService farmsownerService;

    @PostMapping("/farm")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm, @CurrentUser UserPrincipal currentUser){
        Farm savedFarm = farmService.createFarm(farm);
        User user = userService.getUserById(currentUser.getId());
        FarmsOwner farmsowner = new FarmsOwner(savedFarm, user);
        farmsownerService.createFarmowner(farmsowner);
        return new ResponseEntity<>(savedFarm, HttpStatus.CREATED);
    }

    @GetMapping("farm/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Farm> getUserById(@PathVariable("id") Long id){
        Farm farm = farmService.getFarmById(id);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @GetMapping("farms/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Farm>> findById_user(@PathVariable("id") Long id){
        List<Farm> farms = farmService.findById_user(id);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }
    
    @PutMapping("farm/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Farm> updateFarm(@PathVariable("id") Long id, @RequestBody Farm farm){
        farm.setId(id);
        Farm updatedFarm = farmService.updateFarm(farm);
        return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
    }

    // Build Delete Farm REST API
    @DeleteMapping("farm/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteFarm(@PathVariable("id") Long id){
        farmsownerService.deleteFarmonwerByFarmId(id);
        userfarmService.deleteUserFarmByFarmId(id);
        farmService.deleteFarm(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
    
    @GetMapping("user-farm")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Farm> getUserFarmByUserId(@CurrentUser UserPrincipal currentUser){
    	System.out.println(currentUser.getId());
        UserFarm userFarm = userFarmRepository.findUserFarmByUserId(currentUser.getId());
        Farm farm = userFarm.getFarm();
        System.out.println(farm);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }
}