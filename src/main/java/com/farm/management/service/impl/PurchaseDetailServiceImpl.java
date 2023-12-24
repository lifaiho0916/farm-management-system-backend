package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.PurchaseDetail;
import com.farm.management.repository.PurchaseDetailRepository;
import com.farm.management.service.PurchaseDetailService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
	
	private PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public PurchaseDetail createPurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    @Override
    public PurchaseDetail getPurchaseDetailById(Long id) {
        Optional<PurchaseDetail> optionalPurchaseDetail = purchaseDetailRepository.findById(id);
        return optionalPurchaseDetail.get();
    }

    @Override
    public PurchaseDetail updatePurchaseDetail(PurchaseDetail purchaseDetail) {
    	PurchaseDetail existingPurchase = purchaseDetailRepository.findById(purchaseDetail.getId()).get();
        return purchaseDetailRepository.save(existingPurchase);
    }

    @Override
    public void deletePurchaseDetail(Long id) {
    	purchaseDetailRepository.deleteById(id);
    }

    public List<PurchaseDetail> getPurchaseDetailByPurchaseId(Long id){
        return purchaseDetailRepository.getPurchaseDetailByPurchaseId(id);
    }

}
