package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.Product;
import com.farm.management.repository.ProductRepository;
import com.farm.management.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
    	Product existingProduct = productRepository.findById(product.getId()).get();
    	existingProduct.setCategory(product.getCategory());
    	existingProduct.setUnit(product.getUnit());
    	existingProduct.setDescription(product.getDescription());
    	existingProduct.setType(product.getType());
    	existingProduct.setBarcode(product.getBarcode());
    	existingProduct.setStock(product.getStock());
    	Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }

}
