package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Product;

public interface ProductService {
	
	Product createProduct(Product product);

	Product getProductById(Long id);

    List<Product> getAllProduct();

    Product updateProduct(Product product);

    void deleteProduct(Long id);

}
