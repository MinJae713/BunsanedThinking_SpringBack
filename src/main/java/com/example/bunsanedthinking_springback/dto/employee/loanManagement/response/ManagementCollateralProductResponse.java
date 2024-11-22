package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;

@Getter
public class ManagementCollateralProductResponse extends ManagementLoanProductResponse {
	private CollateralType collateralType;
	private int minimumValue;

	public ManagementCollateralProductResponse(int id, int maximumMoney, String name, int interestRate,
		LoanType loanType,
		int minimumAsset, int monthlyIncome, CollateralType collateralType, int minimumValue) {
		super(id, maximumMoney, name, interestRate, loanType, minimumAsset, monthlyIncome);
		this.collateralType = collateralType;
		this.minimumValue = minimumValue;
	}

	public static ManagementCollateralProductResponse from(Collateral collateral) {
		return new ManagementCollateralProductResponse(collateral.getId(), collateral.getMaximumMoney(),
			collateral.getName(), collateral.getInterestRate(), collateral.getLoanType(), collateral.getMinimumAsset(),
			collateral.getMonthlyIncome(), collateral.getCollateralType(), collateral.getMinimumValue());
	}
}
