package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CropRequest {
	
	private Long farmId;
	private String description;
	private String year;
	private String start_date;
	private String end_date;

}
