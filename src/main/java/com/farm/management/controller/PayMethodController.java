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

import com.farm.management.model.PaymentMethod;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.PaymentMethodService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PayMethodController {
	
	@Autowired
    private PaymentMethodService payMethodService;
	
	@PostMapping("payMethod")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PaymentMethod> createUnit(@RequestBody PaymentMethod payMethod, @CurrentUser UserPrincipal currentUser) {
		payMethod.setDescription(payMethod.getDescription());
		PaymentMethod savedPaymentMethod = payMethodService.createPaymentMethod(payMethod);
	    return new ResponseEntity<>(savedPaymentMethod, HttpStatus.CREATED);
	}
	
	@GetMapping("payMethod/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PaymentMethod> getUnitById(@PathVariable("id") Long id){
		PaymentMethod payMethod = payMethodService.getPaymentMethodById(id);
	    return new ResponseEntity<>(payMethod, HttpStatus.OK);
	}
	
	@GetMapping("payMethods")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<PaymentMethod>> getAllCategory(){
	    List<PaymentMethod> payMethod = payMethodService.getAllPaymentMethods();
	    return new ResponseEntity<>(payMethod, HttpStatus.OK);
	}
	
	@PutMapping("payMethod/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<PaymentMethod> updateUser(@PathVariable("id") Long id,
	                                       @RequestBody PaymentMethod payMethod){
		payMethod.setId(payMethod.getId());
		PaymentMethod updatedPayMethod = payMethodService.updatePaymentMethod(payMethod);
	    return new ResponseEntity<>(updatedPayMethod, HttpStatus.OK);
	}
	
	@DeleteMapping("payMethod/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
		payMethodService.deletePaymentMethod(id);
	    return new ResponseEntity<>("Payment method successfully deleted!", HttpStatus.OK);
	}

}
