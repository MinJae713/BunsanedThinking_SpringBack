package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class InsuranceVO {
	private int product_id;
	private int insurance_type;
	private int age_range;
	private int monthly_premium;
	private int contract_period;
	private String coverage;
}
