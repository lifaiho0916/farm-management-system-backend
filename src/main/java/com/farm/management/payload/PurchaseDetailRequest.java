package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseDetailRequest {
	
	private Long farmId;
	private Long supplierId;
	private Instant date;
	private Long productId;
	private Long purchaseId;
	private Long unitId;
	private Double quantity;
	private Double price;
	private String lote;

}
