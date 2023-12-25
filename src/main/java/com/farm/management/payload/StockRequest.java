package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockRequest {
	
	private Long purchaseId;
	private Long unitId;
	private Long productId;
	private String types;
	private Double quantity_moved;
	private Instant date;

}
