package com.example.bunsanedthinking_springback.dto.employee.loanManagement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCollateralLoanProductDTO {
	private int loanType;
	private String name;
	private int interestRate;
	private int maximumMoney;
	private int minimumAsset;
	private int collateralType;
	private int minimumValue;
	private int monthlyIncome;
}
