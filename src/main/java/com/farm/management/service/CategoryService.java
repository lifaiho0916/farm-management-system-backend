package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Category;

public interface CategoryService {
	
	Category createCategory(Category category);

	Category getCategoryById(Long id);

    List<Category> getAllCategory();

	Category updateCategory(Category category);

    void deleteCategory(Long id);

}
