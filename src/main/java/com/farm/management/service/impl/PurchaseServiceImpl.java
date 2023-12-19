package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.Purchase;
import com.farm.management.repository.PurchaseRepository;
import com.farm.management.service.PurchaseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
	
	private PurchaseRepository purchaseRepository;

    @Override
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        return optionalPurchase.get();
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
    	Purchase existingPurchase = purchaseRepository.findById(purchase.getId()).get();
    	existingPurchase.setFarm(purchase.getFarm());
    	existingPurchase.setSupplier(purchase.getSupplier());
    	existingPurchase.setDate(purchase.getDate());
    	existingPurchase.setTotalPrice(purchase.getTotalPrice());
    	existingPurchase.setTotalInstallment(purchase.getTotalInstallment());
        return purchaseRepository.save(existingPurchase);
    }

    @Override
    public void deletePurchase(Long id) {
    	purchaseRepository.deleteById(id);
    }
    
    public List<Purchase> getPurchaseByFarmId(Long farmId){
        return purchaseRepository.getPurchaseByFarmId(farmId);
    }

}
