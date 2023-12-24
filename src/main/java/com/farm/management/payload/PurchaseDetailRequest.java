package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseDetailRequest {
	
	private Long productId;
	private Long purchaseId;
	private Long unitId;
	private Double quantity;
	private Double price;
	private String lote;

}
