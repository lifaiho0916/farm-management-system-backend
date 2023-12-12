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

import com.farm.management.model.Farm;
import com.farm.management.model.Supplier;
import com.farm.management.payload.SupplierRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.SupplierService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SupplierController {
	
	@Autowired
    private SupplierService supplierService;
	
	@Autowired
	private FarmService farmService;
	
	// Build Get All Users REST API
	@PostMapping("/supplier")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierRequest supplierRequest, @CurrentUser UserPrincipal currentUser){
        Farm savedFarm = farmService.getFarmById(supplierRequest.getFarmId());
        Supplier supplier = new Supplier();
        supplier.setFarm(savedFarm);
        supplier.setName(supplierRequest.getName());
        supplier.setCnpj(supplierRequest.getCnpj());
        supplier.setCity(supplierRequest.getCity());
        supplier.setState(supplierRequest.getState());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setStreet(supplierRequest.getStreet());
        Supplier saveSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(saveSupplier, HttpStatus.CREATED);
	}
	
	@GetMapping("suppliers/{id}")
    @PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Supplier>> getSupplierByFarmId(@PathVariable("id") Long id){
		List<Supplier> getSuppliers = supplierService.findByfarmId(id);
		return new ResponseEntity<>(getSuppliers, HttpStatus.OK);
	}
	
	@PutMapping("supplier/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") Long supplierId,
                                           @RequestBody SupplierRequest supplierRequest){
		Supplier setSupplier = supplierService.getSupplierById(supplierId);
		Farm setFarm = farmService.getFarmById(supplierRequest.getFarmId());
		setSupplier.setFarm(setFarm);
		setSupplier.setName(supplierRequest.getName());
		setSupplier.setCnpj(supplierRequest.getCnpj());
		setSupplier.setCity(supplierRequest.getCity());
		setSupplier.setState(supplierRequest.getState());
		setSupplier.setPhone(supplierRequest.getPhone());
		setSupplier.setEmail(supplierRequest.getEmail());
		setSupplier.setStreet(supplierRequest.getStreet());
		Supplier updatedSupplier = supplierService.updateSupplier(setSupplier);
		return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
	}
	
	@DeleteMapping("supplier/{id}")
	@PreAuthorize("isAuthenticated()")
	ResponseEntity<String> deleteSupplier(@PathVariable("id") Long supplierId){
		supplierService.deleteSupplier(supplierId);
        return new ResponseEntity<>("Supplier successfully deleted!", HttpStatus.OK);
	}
	

}
