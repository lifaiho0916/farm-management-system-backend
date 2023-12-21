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
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.BillsReceiveService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BillsToReceiveController {
	
	@Autowired
    private BillsReceiveService billsReceiveService;
	
	@PostMapping("toReceive")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<BillsReceive> createUnit(@RequestBody BillsReceive billsReceive, @CurrentUser UserPrincipal currentUser) {
		billsReceive.setAmount(billsReceive.getAmount());
		BillsReceive result = billsReceiveService.createBillsReceive(billsReceive);
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
	                                       @RequestBody BillsReceive billsReceive){
		billsReceive.setId(id);
	    BillsReceive updatedBillsReceive = billsReceiveService.updateBillsReceive(billsReceive);
	    return new ResponseEntity<>(updatedBillsReceive, HttpStatus.OK);
	}
	
	@DeleteMapping("toReceive/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteBillsReceive(@PathVariable("id") Long id){
		billsReceiveService.deleteBillsReceive(id);
	    return new ResponseEntity<>("Bill to receive is successfully deleted!", HttpStatus.OK);
	}

}
