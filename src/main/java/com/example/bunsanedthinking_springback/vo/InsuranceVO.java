package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceVO {
	private int product_id;
	private int insurance_type;
	private int age_range;
	private int monthly_premium;
	private int contract_period;
	private String coverage;
}
