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

import com.farm.management.model.BillsReceive;
import com.farm.management.model.PaymentMethod;
import com.farm.management.model.ProductionSale;
import com.farm.management.payload.BillsReceiveRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.BillsReceiveService;
import com.farm.management.service.PaymentMethodService;
import com.farm.management.service.ProductionSaleService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BillsToReceiveController {
	
	@Autowired
    private BillsReceiveService billsReceiveService;
	
	@Autowired
    private PaymentMethodService paymentMethodService;
	
	@Autowired
    private ProductionSaleService productSaleService;
	
	@PostMapping("toReceive")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsReceive> createBillsRecevie(@RequestBody BillsReceiveRequest billsReceiveReq, @CurrentUser UserPrincipal currentUser) {
		BillsReceive newBills = new BillsReceive();
		PaymentMethod setPayMethod = paymentMethodService.getPaymentMethodById(billsReceiveReq.getPaymentMethodId());
		ProductionSale setProductionSale = productSaleService.getProductionSaleById(billsReceiveReq.getProductSaleId());
		newBills.setPaymentMethod(setPayMethod);
		newBills.setProductionSale(setProductionSale);
		newBills.setAmount(billsReceiveReq.getAmount());
		newBills.setInstallment(billsReceiveReq.getInstallment());
		newBills.setAmount_received(billsReceiveReq.getAmount_received());
		newBills.setExpected_receive_date(billsReceiveReq.getExpected_receive_date());
		newBills.setReceive_date_made(billsReceiveReq.getReceive_date_made());
		newBills.setQuantity(billsReceiveReq.getQuantity());
		BillsReceive result = billsReceiveService.createBillsReceive(newBills);
	    return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("toReceive/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsReceive> getBillsReceiveById(@PathVariable("id") Long id){
	    BillsReceive billsReceive = billsReceiveService.getBillsReceiveById(id);
	    return new ResponseEntity<>(billsReceive, HttpStatus.OK);
	}
	
    
    @GetMapping("toReceives/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BillsReceive>> findByFarmId(@PathVariable("id") Long id){
        List<BillsReceive> result = billsReceiveService.getBillsReceiveBySaleId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("toReceive/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsReceive> updateUser(@PathVariable("id") Long id,
	                                       @RequestBody BillsReceiveRequest billsReceiveReq){
		BillsReceive setBill = billsReceiveService.getBillsReceiveById(id);
		PaymentMethod setPaymentMethod = paymentMethodService.getPaymentMethodById(billsReceiveReq.getPaymentMethodId());
		ProductionSale setProductSale = productSaleService.getProductionSaleById(billsReceiveReq.getProductSaleId());
		setBill.setPaymentMethod(setPaymentMethod);
		setBill.setProductionSale(setProductSale);
		setBill.setAmount(billsReceiveReq.getAmount());
//		setBill.setInstallment(billsReceiveReq.getInstallment());
		setBill.setAmount_received(billsReceiveReq.getAmount_received());
		setBill.setQuantity(billsReceiveReq.getQuantity());
//		setBill.setExpected_receive_date(billsReceiveReq.getExpected_receive_date());
//		setBill.setReceive_date_made(billsReceiveReq.getReceive_date_made());
	    BillsReceive updatedBillsReceive = billsReceiveService.updateBillsReceive(setBill);
	    return new ResponseEntity<>(updatedBillsReceive, HttpStatus.OK);
	}
	
	@DeleteMapping("toReceive/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteBillsReceive(@PathVariable("id") Long id){
		billsReceiveService.deleteBillsReceive(id);
	    return new ResponseEntity<>("Bill to receive is successfully deleted!", HttpStatus.OK);
	}

}
