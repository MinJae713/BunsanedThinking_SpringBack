package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.CollateralType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCollateralLoanProductRequest {
	private int loanType;
	private String name;
	private int interestRate;
	private int maximumMoney;
	private int minimumAsset;
	private CollateralType collateralType;
	private int minimumValue;
	private int monthlyIncome;
}
