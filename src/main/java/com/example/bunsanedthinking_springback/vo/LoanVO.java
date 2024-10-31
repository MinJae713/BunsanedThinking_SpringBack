package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class LoanVO {
	private int product_id;
	private int loan_type;
	private int minimum_asset;
	private int monthly_income;
	private int interest_rate;
}
