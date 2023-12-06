package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlotRequest {
	
	private Long farmId;
	private String name;
	private String description;
	private Long area;
	private String type;

}
