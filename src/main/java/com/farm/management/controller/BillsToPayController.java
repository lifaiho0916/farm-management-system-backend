package com.farm.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.management.model.BillsPay;
import com.farm.management.model.PaymentMethod;
import com.farm.management.model.Purchase;
import com.farm.management.payload.BillsPayRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.BillsPayService;
import com.farm.management.service.PaymentMethodService;
import com.farm.management.service.PurchaseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BillsToPayController {
	
	@Autowired
    private BillsPayService BillsPayService;
	
	@Autowired
    private PaymentMethodService paymentMethodService;
	
	@Autowired
    private PurchaseService purchaseService;
	
	@PostMapping("toPay")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsPay> createBillsPay(@RequestBody BillsPayRequest BillsPayReq, @CurrentUser UserPrincipal currentUser) {
		BillsPay newBills = new BillsPay();
		PaymentMethod setPayMethod = paymentMethodService.getPaymentMethodById(BillsPayReq.getPaymentMethodId());
		Purchase setPurchase = purchaseService.getPurchaseById(BillsPayReq.getPurchaseId());
		setPurchase.setTotalInstallment(setPurchase.getTotalInstallment() + 1);
		newBills.setPaymentMethod(setPayMethod);
		newBills.setPurchase(setPurchase);
		newBills.setAmount(BillsPayReq.getAmount());
		newBills.setExpected_payment_date(BillsPayReq.getExpected_payment_date());
		newBills.setPayment_date_made(BillsPayReq.getPayment_date_made());
		newBills.setAmount_paid(BillsPayReq.getAmount_paid());
		newBills.setInstallment(BillsPayReq.getInstallment());
		BillsPay result = BillsPayService.createBillsPay(newBills);
	    return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("toPay/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsPay> getBillsPayById(@PathVariable("id") Long id){
	    BillsPay BillsPay = BillsPayService.getBillsPayById(id);
	    return new ResponseEntity<>(BillsPay, HttpStatus.OK);
	}
	
    
    @GetMapping("toPays/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BillsPay>> findByPurchaseId(@PathVariable("id") Long id){
        List<BillsPay> result = BillsPayService.getBillsPayByPurchaseId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("toPay/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsPay> updateUser(@PathVariable("id") Long id,
	                                       @RequestBody BillsPayRequest BillsPayReq){
		BillsPay setBill = BillsPayService.getBillsPayById(id);
		PaymentMethod setPaymentMethod = paymentMethodService.getPaymentMethodById(BillsPayReq.getPaymentMethodId());
		Purchase setpurchase = purchaseService.getPurchaseById(BillsPayReq.getPurchaseId());
		setBill.setPaymentMethod(setPaymentMethod);
		setBill.setPurchase(setpurchase);
		setBill.setAmount(BillsPayReq.getAmount());
		setBill.setAmount_paid(BillsPayReq.getAmount_paid());
//		setBill.setInstallment(BillsPayReq.getInstallment());
//		setBill.setExpected_payment_date(BillsPayReq.getExpected_payment_date());
//		setBill.setPayment_date_made(BillsPayReq.getPayment_date_made());
	    BillsPay updatedBillsPay = BillsPayService.updateBillsPay(setBill);
	    return new ResponseEntity<>(updatedBillsPay, HttpStatus.OK);
	}
	
	@DeleteMapping("toPay/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Purchase> deleteBillsPay(@PathVariable("id") Long id){
		BillsPay setBill = BillsPayService.getBillsPayById(id);
		Purchase setPurchase = setBill.getPurchase();
		setPurchase.setTotalInstallment(setPurchase.getTotalInstallment() - 1);
		BillsPayService.deleteBillsPay(id);
	    return new ResponseEntity<>(setPurchase, HttpStatus.OK);
	}

}
