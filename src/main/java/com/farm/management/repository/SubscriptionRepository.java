package com.farm.management.repository;

import com.farm.management.model.Subscriptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
	 
	 @Query(value = "SELECT * FROM tb_subscriptions WHERE id_users = :userId", nativeQuery = true)
	 Subscriptions getByUserId(@Param("userId") Long userId);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE tb_subscriptions SET subscription_id = :subscriptionId WHERE id_users = :userId", nativeQuery = true)
	 void updateSubscription(@Param("subscriptionId") String subscriptionId, @Param("userId") Long userId);
}
