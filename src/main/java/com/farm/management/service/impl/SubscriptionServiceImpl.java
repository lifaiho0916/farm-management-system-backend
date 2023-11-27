package com.farm.management.service.impl;

import org.springframework.stereotype.Service;

import com.farm.management.model.Subscriptions;
import com.farm.management.model.User;
import com.farm.management.payload.CustomerResponse;
import com.farm.management.repository.SubscriptionRepository;
import com.farm.management.repository.UserRepository;
import com.farm.management.service.SubscriptionService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository subscriptionRepository;
	
	private UserRepository userRepository;
    
    @Override
    public CustomerResponse updatePayment(String token, String holder, Long userId, String email) {
    	Stripe.apiKey="sk_test_51OE96nJExAbTkPoHu2V3phxUqKTSrkywE8XYPoymUoYin8WM5JrwbjqJYwdwkUSWpOj74u2AorWgxDrbulmHlIAZ00RscCDuZJ";
    	PaymentMethodCreateParams paramsBuilder = 
    			PaymentMethodCreateParams.builder()
    			  .setType(PaymentMethodCreateParams.Type.CARD)
    			  .setCard(PaymentMethodCreateParams.Token.builder()
    			    .setToken(token)
    			    .build())
    			  .build();
    	try {
    		PaymentMethod paymentMethod = PaymentMethod.create(paramsBuilder);
    	    Subscriptions subscription = subscriptionRepository.getByUserId(userId);
    	    
    	    if(subscription != null) {
    	    	Customer customer = Customer.retrieve(subscription.getCustomer_id());
    	    	PaymentMethod oldPaymentMethod = PaymentMethod.retrieve(customer.getInvoiceSettings().getDefaultPaymentMethod());
    	    	oldPaymentMethod.detach();
    	    	PaymentMethodAttachParams attachParams = 
    	    		PaymentMethodAttachParams.builder()
    	    			.setCustomer(customer.getId())
    	    			.build();
    	    	paymentMethod.attach(attachParams);
    	    	CustomerUpdateParams updateParams = 
        	    		CustomerUpdateParams.builder()
    	    				.setInvoiceSettings(
    	    					CustomerUpdateParams.InvoiceSettings.builder()
    	    						.setDefaultPaymentMethod(paymentMethod.getId())
    	    						.build())
        	    			.build();
        	    customer.update(updateParams);
    	    }  else {
    	    	CustomerCreateParams customerParams =
    	    		CustomerCreateParams.builder()
    	    			.setEmail(email)
    	    			.setName(holder)
    	    			.setPaymentMethod(paymentMethod.getId())
    	    			.build();
    	    	Customer customer = Customer.create(customerParams);
    	    	
    	    	CustomerUpdateParams updateParams = 
        	    		CustomerUpdateParams.builder()
    	    				.setInvoiceSettings(
    	    					CustomerUpdateParams.InvoiceSettings.builder()
    	    						.setDefaultPaymentMethod(paymentMethod.getId())
    	    						.build())
        	    			.build();
        	    customer.update(updateParams);
    	    	User user = userRepository.getById(userId);
    	    	Subscriptions newSubscripiton = new Subscriptions(user, email, email);
    	    	newSubscripiton.setCustomer_id(customer.getId());
    	    	newSubscripiton.setUser(user);
    	    	subscriptionRepository.save(newSubscripiton);
    	    } 
			CustomerResponse customerResponse = new CustomerResponse();
			customerResponse.setType(paymentMethod.getCard().getBrand());
			customerResponse.setLast4(paymentMethod.getCard().getLast4());
			customerResponse.setExpiry((paymentMethod.getCard().getExpMonth() < 10 ? '0' + Long.toString(paymentMethod.getCard().getExpMonth()) : Long.toString(paymentMethod.getCard().getExpMonth())) + '/' + Long.toString(paymentMethod.getCard().getExpYear()).substring(2, 4));
			return customerResponse;
    	} catch (StripeException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }

    @Override 
    public CustomerResponse getCardInfo(Long userId) {
    	Stripe.apiKey="sk_test_51OE96nJExAbTkPoHu2V3phxUqKTSrkywE8XYPoymUoYin8WM5JrwbjqJYwdwkUSWpOj74u2AorWgxDrbulmHlIAZ00RscCDuZJ";
    	Subscriptions subscription = subscriptionRepository.getByUserId(userId);
    	if(subscription != null) {
    		try {
    			Customer customer = Customer.retrieve(subscription.getCustomer_id());
    			PaymentMethod paymentMethod = PaymentMethod.retrieve(customer.getInvoiceSettings().getDefaultPaymentMethod());
    			CustomerResponse customerResponse = new CustomerResponse();
    			customerResponse.setType(paymentMethod.getCard().getBrand());
    			customerResponse.setLast4(paymentMethod.getCard().getLast4());
    			customerResponse.setExpiry((paymentMethod.getCard().getExpMonth() < 10 ? '0' + Long.toString(paymentMethod.getCard().getExpMonth()) : Long.toString(paymentMethod.getCard().getExpMonth())) + '/' + Long.toString(paymentMethod.getCard().getExpYear()).substring(2, 4));
    			return customerResponse;
    		} catch(StripeException e) {
    			System.out.println(e.getMessage());
    			return null;
    		}
    	} else return null;
    }
    
    @Override
    public Subscriptions getSubscriptionByUserId(Long userId) {
    	return subscriptionRepository.getByUserId(userId);
    }
    
    @Override
    public void updateSubscription(String plan, Long userId) {
    	Stripe.apiKey="sk_test_51OE96nJExAbTkPoHu2V3phxUqKTSrkywE8XYPoymUoYin8WM5JrwbjqJYwdwkUSWpOj74u2AorWgxDrbulmHlIAZ00RscCDuZJ";
    	Subscriptions subscription = subscriptionRepository.getByUserId(userId);
    	
    	if(subscription != null) {
	    	if(plan.equals("Pro")) {
	    		SubscriptionCreateParams params = SubscriptionCreateParams.builder()
	    				.setCustomer(subscription.getCustomer_id())
	    				.addItem(SubscriptionCreateParams
                                .Item.builder()
                                .setPrice("price_1OEm5dJExAbTkPoHllqSSzuo")
                                .build())
	    				.build();
	    		try {
					Subscription subscription1 = Subscription.create(params);
					subscriptionRepository.updateSubscription(subscription1.getId() , userId);
				} catch (StripeException e) {
					e.printStackTrace();
				}
	    	} else {
	    		try {
					Subscription subscription1 = Subscription.retrieve(subscription.getSubscription_id());
					subscription1.cancel();
					subscriptionRepository.updateSubscription(null , userId);
				} catch (StripeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}	
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
