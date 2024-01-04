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

import com.farm.management.model.ProductCrop;
import com.farm.management.model.ProductionSale;
import com.farm.management.model.Supplier;
import com.farm.management.payload.ProductSaleRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.ProductCropService;
import com.farm.management.service.ProductionSaleService;
import com.farm.management.service.SupplierService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductionSaleController {
	
	@Autowired
    private ProductionSaleService productionSaleService;
	
	@Autowired
    private SupplierService supplierService;
	
	@Autowired
	private ProductCropService productCropService;
	
	@PostMapping("productSale")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ProductionSale> createProductionSale(@RequestBody ProductSaleRequest saleReq, @CurrentUser UserPrincipal currentUser) {
		ProductionSale newProductSale = new ProductionSale();
		Supplier setSupplier = supplierService.getSupplierById(saleReq.getSupplierId());
		ProductCrop setProductCrop = productCropService.getProductCropById(saleReq.getProductCropId());
		newProductSale.setSupplier(setSupplier);
		newProductSale.setProductCrop(setProductCrop);
		newProductSale.setSale_date(saleReq.getSale_date());
		newProductSale.setQuantity(saleReq.getQuantity());
		newProductSale.setQuotes(saleReq.getQuotes());
		newProductSale.setAmount_money(saleReq.getAmount_money());
		newProductSale.setTotal_installment(saleReq.getTotal_installment());
		ProductionSale result = productionSaleService.createProductionSale(newProductSale);
	    return new ResponseEntity<>(result, HttpStatus.CREATED); 
	}
	
	@GetMapping("productSale/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ProductionSale> getProductionSaleById(@PathVariable("id") Long id){
		ProductionSale result = productionSaleService.getProductionSaleById(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    
    @GetMapping("productSales/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductionSale>> findByFarmId(@PathVariable("id") Long id){
        List<ProductionSale> result = productionSaleService.getProductionSaleByFarmId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("productSale/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ProductionSale> updateProductionSale(@PathVariable("id") Long id,
	                                       @RequestBody ProductSaleRequest saleReq){
		ProductionSale selectedProductSale = productionSaleService.getProductionSaleById(id);
		Supplier setSupplier = supplierService.getSupplierById(saleReq.getSupplierId());
		ProductCrop setProductCrop = productCropService.getProductCropById(saleReq.getProductCropId());
		selectedProductSale.setSupplier(setSupplier);
		selectedProductSale.setProductCrop(setProductCrop);
//		selectedProductSale.setSale_date(saleReq.getSale_date());
		selectedProductSale.setAmount_money(saleReq.getAmount_money());
		selectedProductSale.setQuantity(saleReq.getQuantity());
		selectedProductSale.setQuotes(saleReq.getQuotes());
		selectedProductSale.setTotal_installment(saleReq.getTotal_installment());
		ProductionSale result = productionSaleService.updateProductionSale(selectedProductSale);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("productSale/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteProductionSale(@PathVariable("id") Long id){
		productionSaleService.deleteProductionSale(id);
	    return new ResponseEntity<>("Production Sale method successfully deleted!", HttpStatus.OK);
	}

}
