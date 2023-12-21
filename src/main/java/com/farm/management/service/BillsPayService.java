package com.farm.management.service;

import java.util.List;

import com.farm.management.model.BillsPay;

public interface BillsPayService {
	
	BillsPay createBillsPay(BillsPay billsPay);

    BillsPay getBillsPayById(Long id);
	
	List<BillsPay> getBillsPayByPurchaseId(Long id);

    BillsPay updateBillsPay(BillsPay billsPay);

    void deleteBillsPay(Long id);

}