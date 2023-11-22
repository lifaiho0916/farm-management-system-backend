package com.farm.management.service;

import com.farm.management.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(Subscription subscription);

    void deleteSubscription(Long id);
}
