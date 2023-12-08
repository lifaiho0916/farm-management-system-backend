package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCropRequest {
	
	private Long unitId;
	private Long farmId;
	private Long cropId;
	private String quantity;

}
