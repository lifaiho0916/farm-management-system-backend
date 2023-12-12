package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierRequest {

	private Long farmId;
	private String name;
	private Long cnpj;
	private String city;
	private String state;
	private String phone;
	private String email;
	private String street;
}
