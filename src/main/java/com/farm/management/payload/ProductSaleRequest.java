package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSaleRequest {
	
	private Long supplierId;
	private Long productCropId;
	private Instant sale_date;
	private Double quantity;
	private Double quotes;
	private Double amount_money;
	private Double total_installment;

}
