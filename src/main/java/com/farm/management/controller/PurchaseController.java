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
import com.farm.management.model.Purchase;
import com.farm.management.model.Supplier;
import com.farm.management.payload.PurchaseRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.PurchaseService;
import com.farm.management.service.SupplierService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private FarmService farmService;
	
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("purchase")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest purchaseRequest, @CurrentUser UserPrincipal currentUser) {
		System.out.println(purchaseRequest);
		Purchase purchase = new Purchase();
		Farm selectedFarm = farmService.getFarmById(purchaseRequest.getFarmId());
		Supplier selectedSupplier = supplierService.getSupplierById(purchaseRequest.getSupplierId());
		purchase.setFarm(selectedFarm);
		purchase.setSupplier(selectedSupplier);
		purchase.setDate(purchaseRequest.getDate());
		purchase.setTotalPrice(purchaseRequest.getTotalPrice());
		purchase.setTotalInstallment(purchaseRequest.getTotalInstallment());
		Purchase savedPurchase = purchaseService.createPurchase(purchase);
	    return new ResponseEntity<Purchase>(savedPurchase, HttpStatus.CREATED);
	}
	
	
	@GetMapping("purchase/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") Long id){
		Purchase result = purchaseService.getPurchaseById(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @GetMapping("purchases/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Purchase>> findByFarmId(@PathVariable("id") Long id){
        List<Purchase> result = purchaseService.getPurchaseByFarmId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("purchase/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Long id,
	                                       @RequestBody PurchaseRequest purchaseRequest){
		System.out.println(purchaseRequest);
		Purchase setPurchase = purchaseService.getPurchaseById(id);
		Supplier setSupplier = supplierService.getSupplierById(purchaseRequest.getSupplierId());
		Farm setFarm = farmService.getFarmById(purchaseRequest.getFarmId());
		setPurchase.setFarm(setFarm);
		setPurchase.setSupplier(setSupplier);
//		setPurchase.setDate(purchaseRequest.getDate());
		setPurchase.setTotalInstallment(purchaseRequest.getTotalInstallment());
		setPurchase.setTotalPrice(purchaseRequest.getTotalPrice());
		Purchase result = purchaseService.updatePurchase(setPurchase);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("purchase/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deletePurchase(@PathVariable("id") Long id){
		purchaseService.deletePurchase(id);
	    return new ResponseEntity<>("Purchase successfully deleted!", HttpStatus.OK);
	}

}
