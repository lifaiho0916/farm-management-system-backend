package com.farm.management.repository;

import com.farm.management.model.Subscription;
import com.farm.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
