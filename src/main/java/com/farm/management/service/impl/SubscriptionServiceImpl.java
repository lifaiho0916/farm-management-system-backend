package com.farm.management.service.impl;

import com.farm.management.model.Subscription;
import com.farm.management.model.User;
import com.farm.management.repository.SubscriptionRepository;
import com.farm.management.repository.UserRepository;
import com.farm.management.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
