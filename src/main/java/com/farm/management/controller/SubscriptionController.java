package com.farm.management.controller;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodCreateParams;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/subscription")
public class SubscriptionController {

    @GetMapping("/update-payment")
    public ResponseEntity<?> updatePayment(){
    	
    	Stripe.apiKey="sk_test_51OE96nJExAbTkPoHu2V3phxUqKTSrkywE8XYPoymUoYin8WM5JrwbjqJYwdwkUSWpOj74u2AorWgxDrbulmHlIAZ00RscCDuZJ";
    			
    	PaymentMethodCreateParams paramsBuilder = 
    			PaymentMethodCreateParams.builder()
    				.setCard(PaymentMethodCreateParams.CardDetails.builder()
    				        .setNumber("4242424242424242") // Card number
    				        .setExpMonth((long) 12) // Expire month
    				        .setExpYear((long) 2023) // Expire year
    				        .setCvc("123") // CVC/CVV
    				        .build())
    				.setType(PaymentMethodCreateParams.Type.CARD)
    			    .build();
    	
    	PaymentMethod paymentMethod = null;
    	
    	try {
    	    paymentMethod = PaymentMethod.create(paramsBuilder);
    	} catch (StripeException e) {
    	    // Handle any exceptions that may occur
    	}
    	
    	System.out.println(paymentMethod.getId());
    	return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}