package com.farm.management.service;

import java.util.List;

import com.farm.management.model.BillsReceive;

public interface BillsReceiveService {
	
	BillsReceive createBillsReceive(BillsReceive billsReceive);

    BillsReceive getBillsReceiveById(Long id);
	
	List<BillsReceive> getBillsReceiveBySaleId(Long id);

    BillsReceive updateBillsReceive(BillsReceive billsReceive);

    void deleteBillsReceive(Long id);

}