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
import com.farm.management.model.ProductCrop;
import com.farm.management.model.Unit;
import com.farm.management.payload.CropRequest;
import com.farm.management.payload.CropUpdateRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.CropService;
import com.farm.management.service.FarmService;
import com.farm.management.service.ProductCropService;
import com.farm.management.service.UnitService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductCropController {
	
	@Autowired
    private ProductCropService productCropService;
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private CropService cropService;
	
	// Build Get All Users REST API
    
	@PostMapping("/productCrop")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductCrop> createPlot(@RequestBody CropRequest cropRequest, @CurrentUser UserPrincipal currentUser){
		
		System.out.println(cropRequest);
		
        Farm savedFarm = farmService.getFarmById(cropRequest.getFarmId());
        
        Unit savedUnit = new Unit();
        savedUnit.setDescription(cropRequest.getUnitDescription());
        savedUnit.setType(cropRequest.getType());
        Unit newUnit = unitService.createUnit(savedUnit);
        
        Crop savedCrop = new Crop();
        savedCrop.setFarm(savedFarm);
        savedCrop.setDescription(cropRequest.getCropDescription());
        savedCrop.setYear(cropRequest.getYear());
        Crop newCrop = cropService.createCrop(savedCrop);
        
        ProductCrop productionCrop = new ProductCrop();
        productionCrop.setFarm(savedFarm);
        productionCrop.setUnit(newUnit);
        productionCrop.setCrop(newCrop);
        productionCrop.setQuantity(cropRequest.getQuantity());
        productionCrop.setDate(cropRequest.getDate());
        ProductCrop saveProductCrop = productCropService.createProductionCrop(productionCrop);
        return new ResponseEntity<>(saveProductCrop, HttpStatus.CREATED);
	}
	
    
    @GetMapping("productCrops/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductCrop>> findByFarmId(@PathVariable("id") Long id){
        List<ProductCrop> productionCrop = productCropService.getProductionCropByFarmId(id);
        return new ResponseEntity<>(productionCrop, HttpStatus.OK);
    }
    
    
    @GetMapping("owner-productCrops")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductCrop>> getProductionCropByUserId(@CurrentUser UserPrincipal currentUser){
        List<ProductCrop> productionCrop = productCropService.getProductionCropByUserId(currentUser.getId());
        return new ResponseEntity<>(productionCrop, HttpStatus.OK);
    }
    
    
    @PutMapping("productCrop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductCrop> updateProductionCrop(@PathVariable("id") Long productCropId,
                                           @RequestBody CropUpdateRequest cropUpdateRequest){
    	ProductCrop getProductCrop = productCropService.getProductCropById(productCropId);
    	
    	Crop getCrop = getProductCrop.getCrop();
    	getCrop.setDescription(cropUpdateRequest.getCropDescription());
    	Crop updatedCrop = cropService.updateCrop(getCrop);
    	
    	Unit getUnit = getProductCrop.getUnit();
    	getUnit.setDescription(cropUpdateRequest.getUnitDescription());
    	getUnit.setType(cropUpdateRequest.getType());
    	Unit updatedUnit = unitService.updateUnit(getUnit);
    	
    	getProductCrop.setCrop(updatedCrop);
    	getProductCrop.setUnit(updatedUnit);
    	getProductCrop.setQuantity(cropUpdateRequest.getQuantity());
    	
    	ProductCrop updatedProductionCrop = productCropService.updateProductionCrop(getProductCrop);
        return new ResponseEntity<>(updatedProductionCrop, HttpStatus.OK);
    }
    
    @DeleteMapping("productCrop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteProductCrop(@PathVariable("id") Long productCropId){
    	productCropService.deleteProductionCrop(productCropId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
