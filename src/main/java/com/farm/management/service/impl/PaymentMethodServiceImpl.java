package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.PaymentMethod;
import com.farm.management.repository.PaymentMethodRepository;
import com.farm.management.service.PaymentMethodService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
	
	private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(id);
        return optionalPaymentMethod.get();
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
    	PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(paymentMethod.getId()).get();
    	existingPaymentMethod.setDescription(paymentMethod.getDescription());
    	PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(existingPaymentMethod);
        return updatedPaymentMethod;
    }

    @Override
    public void deletePaymentMethod(Long id) {
    	paymentMethodRepository.deleteById(id);
    }

}
