package com.farm.management.service;

import com.farm.management.model.Subscriptions;
import com.farm.management.payload.CustomerResponse;

public interface SubscriptionService {

    void updateSubscription(String plan, Long userId);
    
    Subscriptions getSubscriptionByUserId(Long userId);
    
    CustomerResponse updatePayment(String token, String holder, Long userId, String email);
    
    CustomerResponse getCardInfo(Long userId);

    void deleteSubscription(Long id);
}
