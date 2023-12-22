package com.farm.management.payload;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillsPayRequest {
	
	private Long paymentMethodId;
	private Long purchaseId;
	private Double amount;
	private Double amount_paid;
	private Instant expected_payment_date;
	private Instant payment_date_made;
	private Double installment;

}
