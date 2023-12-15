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
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;
	
	@PostMapping("category")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Category> createUnit(@RequestBody Category category, @CurrentUser UserPrincipal currentUser) {
		category.setDescription(category.getDescription());
		Category savedUnit = categoryService.createCategory(category);
	    return new ResponseEntity<>(savedUnit, HttpStatus.CREATED);
	}
	
	@GetMapping("category/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Category> getUnitById(@PathVariable("id") Long id){
	    Category category = categoryService.getCategoryById(id);
	    return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@GetMapping("categories")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Category>> getAllCategory(){
	    List<Category> unit = categoryService.getAllCategory();
	    return new ResponseEntity<>(unit, HttpStatus.OK);
	}
	
	@PutMapping("category/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Category> updateUser(@PathVariable("id") Long id,
	                                       @RequestBody Category category){
		category.setId(category.getId());
	    Category updatedCategory = categoryService.updateCategory(category);
	    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("category/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
		categoryService.deleteCategory(id);
	    return new ResponseEntity<>("Unit successfully deleted!", HttpStatus.OK);
	}

}
