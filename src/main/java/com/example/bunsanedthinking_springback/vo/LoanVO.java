package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanVO {
	private int product_id;
	private int loan_type;
	private int minimum_asset;
	private int monthly_income;
	private int interest_rate;
}
