package com.farm.management.service;

import java.util.List;

import com.farm.management.model.PaymentMethod;

public interface PaymentMethodService {
	
	PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

	PaymentMethod getPaymentMethodById(Long id);

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);

    void deletePaymentMethod(Long id);

}
