package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.BillsReceive;
import com.farm.management.repository.BillsReceiveRepository;
import com.farm.management.service.BillsReceiveService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillsReceiveServiceImpl implements BillsReceiveService {
	
	private BillsReceiveRepository billsReceiveRepository;

    @Override
    public BillsReceive createBillsReceive(BillsReceive billsReceive) {
        return billsReceiveRepository.save(billsReceive);
    }

    @Override
    public BillsReceive getBillsReceiveById(Long id) {
        Optional<BillsReceive> optionalBillsReceive = billsReceiveRepository.findById(id);
        return optionalBillsReceive.get();
    }

    @Override
    public BillsReceive updateBillsReceive(BillsReceive billsReceive) {
        return billsReceiveRepository.save(billsReceive);
    }

    @Override
    public void deleteBillsReceive(Long id) {
        billsReceiveRepository.deleteById(id);
    }
    
    public List<BillsReceive> getBillsReceiveBySaleId(Long id){
        return billsReceiveRepository.getBillsReceiveBySaleId(id);
    }

}
