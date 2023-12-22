package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseDetailRequest {
	
	private Long farmId;
	private Long supplierId;
	private Long unitId;
	private Long productId;
	private Long purchaseId;
	private Double quantity;
	private Double price;
	private String lote;
	private Double totalPrice;
	private Double totalInstallment;
	private Instant date;

}
