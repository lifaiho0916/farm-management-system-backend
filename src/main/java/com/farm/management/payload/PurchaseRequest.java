package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseRequest {
	
	private Long productId;
	private Long unitId;
	private Long purchaseId;
	private Long farmId;
	private Long supplierId;
	private Double price;
	private Double quantity;
	private String lote;
	private Double totalPrice;
	private Double totalInstallment;
	private Instant date;

}
