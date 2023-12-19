package com.farm.management.service;

import java.util.List;

import com.farm.management.model.PurchaseDetail;

public interface PurchaseDetailService {
	
	PurchaseDetail createPurchaseDetail(PurchaseDetail purchaseDetail);

	PurchaseDetail getPurchaseDetailById(Long id);

	PurchaseDetail updatePurchaseDetail(PurchaseDetail purchaseDetail);

    void deletePurchaseDetail(Long id);
    
    List<PurchaseDetail> getPurchaseDetailByFarmId(Long farmId);

}
