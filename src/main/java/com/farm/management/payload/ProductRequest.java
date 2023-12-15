package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
	
	private Long categoryId;
	private Long unitId;
	private String description;
	private Long type;
	private String barcode;
	private Long stock;

}
