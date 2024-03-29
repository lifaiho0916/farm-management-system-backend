package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseRequest {
	
	private Long farmId;
	private Long supplierId;
	private Double totalPrice;
	private Double totalInstallment;
	private Instant date;

}
