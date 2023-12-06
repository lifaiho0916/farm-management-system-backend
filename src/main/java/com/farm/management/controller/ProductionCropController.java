package com.farm.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.management.model.Farm;
import com.farm.management.model.ProductionCrop;
import com.farm.management.payload.ProductionCropRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.ProductionCropService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductionCropController {
	
	@Autowired
    private ProductionCropService productionCropService;
	
	@Autowired
	private FarmService farmService;
	
	// Build Get All Users REST API
    
	@PostMapping("/new-productionCrop")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductionCrop> createPlot(@RequestBody ProductionCropRequest productionCropRequest, @CurrentUser UserPrincipal currentUser){
        Farm savedFarm = farmService.getFarmById(productionCropRequest.getFarmId());
        
        ProductionCrop productionCrop = new ProductionCrop();
        productionCrop.setFarm(savedFarm);
        ProductionCrop saveProductionCrop = productionCropService.createProductionCrop(productionCrop);
        return new ResponseEntity<>(saveProductionCrop, HttpStatus.CREATED);
	}
	
    
    @GetMapping("productionCrop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductionCrop>> findByCreated_by(@PathVariable("id") Long id){
        List<ProductionCrop> productionCrop = productionCropService.getProductionCropByFarmId(id);
        return new ResponseEntity<>(productionCrop, HttpStatus.OK);
    }
    
    
    @GetMapping("owner-productionCrops")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductionCrop>> getProductionCropByUserId(@CurrentUser UserPrincipal currentUser){
        List<ProductionCrop> productionCrop = productionCropService.getProductionCropByUserId(currentUser.getId());
        return new ResponseEntity<>(productionCrop, HttpStatus.OK);
    }
    
    
    @PutMapping("update-productionCrop/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductionCrop> updateProductionCrop(@PathVariable("id") Long productionCropId,
                                           @RequestBody ProductionCrop productionCrop){
    	productionCrop.setId(productionCropId);
    	ProductionCrop updatedProductionCrop = productionCropService.updateProductionCrop(productionCrop);
        return new ResponseEntity<>(updatedProductionCrop, HttpStatus.OK);
    }

}
