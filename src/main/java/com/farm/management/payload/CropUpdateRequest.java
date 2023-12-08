package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CropUpdateRequest {
	
	private String cropDescription;
	private String unitDescription;
	private String Type;
	private String quantity;

}
