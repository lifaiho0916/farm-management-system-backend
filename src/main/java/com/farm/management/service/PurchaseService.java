package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Purchase;

public interface PurchaseService {
	
	Purchase createPurchase(Purchase purchase);

	Purchase getPurchaseById(Long id);
	
	List<Purchase> getPurchaseByFarmId(Long farmId);

	Purchase updatePurchase(Purchase purchase);

    void deletePurchase(Long id);


}
