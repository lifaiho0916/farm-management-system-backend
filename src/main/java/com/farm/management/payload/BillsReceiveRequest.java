package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillsReceiveRequest {
	
	private Long paymentMethodId;
	private Long productSaleId;
	private Double amount;
	private Double installment;
	private Double amount_received;
	private Instant expected_receive_date;
	private Instant receive_date_made;
	private String quantity;

}
