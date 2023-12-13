package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCropRequest {
	
	private Long farmId;
	private String cropDescription;
	private String year;
	private String unitDescription;
	private String Type;
	private String quantity;
	private Instant date;

}
