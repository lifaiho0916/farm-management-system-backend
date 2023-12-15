package com.farm.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.management.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
