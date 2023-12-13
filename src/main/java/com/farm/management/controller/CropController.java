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

import com.farm.management.model.Crop;
import com.farm.management.model.Farm;
import com.farm.management.payload.CropRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.CropService;
import com.farm.management.service.FarmService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CropController {
	
	@Autowired
    private CropService cropService;
	
	@Autowired
	private FarmService farmService;
	
	// Build Get All Users REST API
	@PostMapping("/crop")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Crop> createPlot(@RequestBody CropRequest cropRequest, @CurrentUser UserPrincipal currentUser){
		System.out.println(cropRequest);
        Crop newCrop = new Crop();
        Farm farm = farmService.getFarmById(cropRequest.getFarmId());
        newCrop.setFarm(farm);
        newCrop.setDescription(cropRequest.getDescription());
        newCrop.setYear(cropRequest.getYear());
        Crop saveCrop = cropService.createCrop(newCrop);
        return new ResponseEntity<>(saveCrop, HttpStatus.CREATED);
	}
    
    @GetMapping("crops/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Crop>> findByFarmId(@PathVariable("id") Long id){
        List<Crop> crops = cropService.getCropByFarmId(id);
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }
    
    @GetMapping("crop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Crop> getCropById(@PathVariable("id") Long id){
        Crop crop = cropService.getCropById(id);
        return new ResponseEntity<>(crop, HttpStatus.OK);
    }
    
    @PutMapping("crop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Crop> updatePlot(@PathVariable("id") Long cropId,
                                           @RequestBody CropRequest cropRequest){
    	System.out.println(cropRequest);
    	Crop selectedCrop = cropService.getCropById(cropId);
    	Farm selectedFarm = farmService.getFarmById(cropRequest.getFarmId());
    	selectedCrop.setFarm(selectedFarm);
    	selectedCrop.setDescription(cropRequest.getDescription());
    	selectedCrop.setYear(cropRequest.getYear());
    	selectedCrop.setStart_date(cropRequest.getStart_date());
    	selectedCrop.setEnd_date(cropRequest.getEnd_date());
        Crop updatedCrop = cropService.updateCrop(selectedCrop);
        return new ResponseEntity<>(updatedCrop, HttpStatus.OK);
    }
    
    @DeleteMapping("crop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deletePlot(@PathVariable("id") Long cropId){
    	cropService.deleteCrop(cropId);
        return new ResponseEntity<>("Crop successfully deleted!", HttpStatus.OK);
    }

}