package com.farm.management.controller;


import com.farm.management.model.Subscriptions;
import com.farm.management.payload.CustomerRequest;
import com.farm.management.payload.CustomerResponse;
import com.farm.management.payload.SubscriptionRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.SubscriptionService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Subscriptions> getSubscription(@CurrentUser UserPrincipal currentUser) {
    	Subscriptions subscription = subscriptionService.getSubscriptionByUserId(currentUser.getId());
    	return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
	
	
	@PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updateSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest, @CurrentUser UserPrincipal currentUser) {
    	subscriptionService.updateSubscription(subscriptionRequest.getPlan(), currentUser.getId());
    	return new ResponseEntity<>("Success", HttpStatus.OK);
 
    }

    @PostMapping("/update-payment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CustomerResponse> updatePayment(@Valid @RequestBody CustomerRequest customerRequest, @CurrentUser UserPrincipal currentUser){
    	CustomerResponse customerResponse = subscriptionService.updatePayment(customerRequest.getToken(), customerRequest.getHolder(), currentUser.getId(), currentUser.getEmail());
    	return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/card-info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CustomerResponse> getCardInfo(@CurrentUser UserPrincipal currentUser) {
    	CustomerResponse customerResponse = subscriptionService.getCardInfo(currentUser.getId());
    	return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
}