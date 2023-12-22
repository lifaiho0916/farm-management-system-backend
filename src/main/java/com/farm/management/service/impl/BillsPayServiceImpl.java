package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.BillsPay;
import com.farm.management.repository.BillsPayRepository;
import com.farm.management.service.BillsPayService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillsPayServiceImpl implements BillsPayService {
	
	private BillsPayRepository billsPayRepository;

    @Override
    public BillsPay createBillsPay(BillsPay billsPay) {
        return billsPayRepository.save(billsPay);
    }

    @Override
    public BillsPay getBillsPayById(Long id) {
        Optional<BillsPay> optionalBillsPay = billsPayRepository.findById(id);
        return optionalBillsPay.get();
    }

    @Override
    public BillsPay updateBillsPay(BillsPay billsPay) {
    	BillsPay existingBillsPay = billsPayRepository.findById(billsPay.getId()).get();
        return billsPayRepository.save(existingBillsPay);
    }

    @Override
    public void deleteBillsPay(Long id) {
        billsPayRepository.deleteById(id);
    }
    
    public List<BillsPay> getBillsPayByPurchaseId(Long id){
        return billsPayRepository.getBillsPayByPurchaseId(id);
    }

}
