package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateCollateralRequest implements UpdateLoanRequest {
	private int id;
	private int maximumMoney;
	private String name;
	private int interestRate;
	private LoanType loanType;
	private int minimumAsset;
	private int monthlyIncome;
	private CollateralType collateralType;
	private int minimumValue;

	public Collateral toEntity() {
		return new Collateral(id, loanType, name, interestRate, maximumMoney, minimumAsset, collateralType,
			minimumValue, monthlyIncome);
	}
}
