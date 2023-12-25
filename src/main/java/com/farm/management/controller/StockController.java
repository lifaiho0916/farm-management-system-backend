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

import com.farm.management.model.Product;
import com.farm.management.model.Purchase;
import com.farm.management.model.Stock;
import com.farm.management.model.Unit;
import com.farm.management.model.User;
import com.farm.management.payload.StockRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.ProductService;
import com.farm.management.service.PurchaseService;
import com.farm.management.service.StockService;
import com.farm.management.service.UnitService;
import com.farm.management.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StockController {
	
	@Autowired
	private StockService stockService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UnitService unitService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseService purchaseService;

	
	@PostMapping("stock")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Stock> createStock(@RequestBody StockRequest stockRequest, @CurrentUser UserPrincipal currentUser) {
		System.out.println(stockRequest);
		Stock stock = new Stock();
		User selectedUser = userService.getUserById(currentUser.getId());
		Purchase selectedPurchase = purchaseService.getPurchaseById(stockRequest.getPurchaseId());
		Unit selectedUnit = unitService.getUnitById(stockRequest.getUnitId());
		Product selectedProduct = productService.getProductById(stockRequest.getProductId());
		stock.setPurchase(selectedPurchase);
		stock.setUser(selectedUser);
		stock.setUnit(selectedUnit);
		stock.setProduct(selectedProduct);
		stock.setDate(stockRequest.getDate());
		stock.setTypes(stockRequest.getTypes());
		stock.setQuantity_moved(stockRequest.getQuantity_moved());
		Stock savedStock = stockService.createStock(stock);
	    return new ResponseEntity<Stock>(savedStock, HttpStatus.CREATED);
	}
	
	
	@GetMapping("stock/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Long id){
		Stock result = stockService.getStockById(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @GetMapping("stocks/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Stock>> findByUserId(@PathVariable("id") Long id){
        List<Stock> result = stockService.getStockByUserId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("stock/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Stock> updateStock(@PathVariable("id") Long id,
	                                       @RequestBody StockRequest stockRequest){
		System.out.println(stockRequest);
		Stock setStock = stockService.getStockById(id);
		Purchase  setPurchase = purchaseService.getPurchaseById(stockRequest.getPurchaseId());
		Product setProduct = productService.getProductById(stockRequest.getProductId());
		Unit setUnit = unitService.getUnitById(stockRequest.getUnitId());
		setStock.setProduct(setProduct);
		setStock.setPurchase(setPurchase);
		setStock.setUnit(setUnit);
		setStock.setQuantity_moved(stockRequest.getQuantity_moved());
//		setStock.setDate(stockRequest.getDate());
		setStock.setTypes(stockRequest.getTypes());
		Stock result = stockService.updateStock(setStock);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("stock/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteStock(@PathVariable("id") Long id){
		stockService.deleteStock(id);
	    return new ResponseEntity<>("Stock successfully deleted!", HttpStatus.OK);
	}

}
