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

import com.farm.management.model.Category;
import com.farm.management.model.Product;
import com.farm.management.model.Unit;
import com.farm.management.payload.ProductRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.CategoryService;
import com.farm.management.service.ProductService;
import com.farm.management.service.UnitService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UnitService unitService;
	
	@PostMapping("product")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest, @CurrentUser UserPrincipal currentUser) {
		System.out.println(productRequest);
		Product product = new Product();
		Category setCategory = categoryService.getCategoryById(productRequest.getCategoryId());
		Unit setUnit = unitService.getUnitById(productRequest.getUnitId());
		product.setCategory(setCategory);
		product.setUnit(setUnit);
		product.setDescription(productRequest.getDescription());
		product.setType(productRequest.getType());
		product.setBarcode(productRequest.getBarcode());
		product.setStock(productRequest.getStock());
		Product savedProduct = productService.createProduct(product);
	    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}
	
	
	@GetMapping("product/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
		Product product = productService.getProductById(id);
	    return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	
	@GetMapping("products")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Product>> getAllProducts(){
	    List<Product> product = productService.getAllProduct();
	    return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("product/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
	                                       @RequestBody ProductRequest productRequest){
		Product setProduct = productService.getProductById(id);
		Category setCategory = categoryService.getCategoryById(productRequest.getCategoryId());
		Unit setUnit = unitService.getUnitById(productRequest.getUnitId());
		setProduct.setCategory(setCategory);
		setProduct.setUnit(setUnit);
		setProduct.setDescription(productRequest.getDescription());
		setProduct.setType(productRequest.getType());
		setProduct.setBarcode(productRequest.getBarcode());
		setProduct.setStock(productRequest.getStock());
	    Product updatedProduct = productService.updateProduct(setProduct);
	    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@DeleteMapping("product/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
		productService.deleteProduct(id);
	    return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
	}

}
