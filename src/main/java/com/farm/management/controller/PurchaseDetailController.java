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
import com.farm.management.model.Product;
import com.farm.management.model.Purchase;
import com.farm.management.model.PurchaseDetail;
import com.farm.management.model.Supplier;
import com.farm.management.model.Unit;
import com.farm.management.payload.PurchaseDetailRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.ProductService;
import com.farm.management.service.PurchaseDetailService;
import com.farm.management.service.PurchaseService;
import com.farm.management.service.SupplierService;
import com.farm.management.service.UnitService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PurchaseDetailController {
	
	@Autowired
    private PurchaseDetailService purchaseDetailService;
	
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UnitService unitService;

	@Autowired
	private FarmService farmService;
	
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("purchaseDetail")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PurchaseDetail> createPurchaseDetail(@RequestBody PurchaseDetailRequest purchaseRequest, @CurrentUser UserPrincipal currentUser) {
		System.out.println(purchaseRequest);
		PurchaseDetail purchaseDetail = new PurchaseDetail();
		
		Purchase purchase = new Purchase();
		Farm selectedFarm = farmService.getFarmById(purchaseRequest.getFarmId());
		Supplier selectedSupplier = supplierService.getSupplierById(purchaseRequest.getSupplierId());
		purchase.setFarm(selectedFarm);
		purchase.setSupplier(selectedSupplier);
		purchase.setDate(purchaseRequest.getDate());
		purchase.setTotalPrice(purchaseRequest.getTotalPrice());
		purchase.setTotalInstallment(purchaseRequest.getTotalInstallment());
		Purchase savedPurchase = purchaseService.createPurchase(purchase);
		purchaseDetail.setPurchase(savedPurchase);
		Unit selectedUnit = unitService.getUnitById(purchaseRequest.getUnitId());
		purchaseDetail.setUnit(selectedUnit);
		Product selectedProduct = productService.getProductById(purchaseRequest.getProductId());
		purchaseDetail.setProduct(selectedProduct);
		purchaseDetail.setQuantity(purchaseRequest.getQuantity());
		purchaseDetail.setPrice(purchaseRequest.getPrice());
		purchaseDetail.setLote(purchaseRequest.getLote());
		PurchaseDetail result = purchaseDetailService.createPurchaseDetail(purchaseDetail);
	    return new ResponseEntity<PurchaseDetail>(result, HttpStatus.CREATED);
	}
	
	
	@GetMapping("purchaseDetail/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PurchaseDetail> getPurchaseDetailById(@PathVariable("id") Long id){
		PurchaseDetail result = purchaseDetailService.getPurchaseDetailById(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @GetMapping("purchasesDetail/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PurchaseDetail>> findByFarmId(@PathVariable("id") Long id){
        List<PurchaseDetail> result = purchaseDetailService.getPurchaseDetailByFarmId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("purchaseDetail/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PurchaseDetail> updatePurchaseDetail(@PathVariable("id") Long id,
	                                       @RequestBody PurchaseDetailRequest purchaseRequest){
		System.out.println(purchaseRequest);
		PurchaseDetail setPurchaseDetail = purchaseDetailService.getPurchaseDetailById(id);
		Purchase setPurchase = purchaseService.getPurchaseById(purchaseRequest.getPurchaseId());
		Supplier setSupplier = supplierService.getSupplierById(purchaseRequest.getSupplierId());
		setPurchase.setSupplier(setSupplier);
		setPurchase.setDate(purchaseRequest.getDate());
		setPurchase.setTotalInstallment(purchaseRequest.getTotalInstallment());
		setPurchase.setTotalPrice(purchaseRequest.getTotalPrice());
		Purchase updatedPurchase = purchaseService.updatePurchase(setPurchase);
		Product setProduct = productService.getProductById(purchaseRequest.getProductId());
		setPurchaseDetail.setPurchase(updatedPurchase);
		setPurchaseDetail.setProduct(setProduct);
		setPurchaseDetail.setPrice(purchaseRequest.getPrice());
		setPurchaseDetail.setQuantity(purchaseRequest.getQuantity());
		setPurchaseDetail.setLote(purchaseRequest.getLote());
		PurchaseDetail result = purchaseDetailService.updatePurchaseDetail(setPurchaseDetail);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("purchaseDetail/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deletePurchaseDetail(@PathVariable("id") Long id){
		purchaseDetailService.deletePurchaseDetail(id);
	    return new ResponseEntity<>("PurchaseDetail successfully deleted!", HttpStatus.OK);
	}

}
