package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response;

import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;

@Getter
public class ManagementFixedDepositProductResponse extends ManagementLoanProductResponse {
	private int minimumAmount;

	public ManagementFixedDepositProductResponse(int id, int maximumMoney, String name, int interestRate,
		LoanType loanType, int minimumAsset, int monthlyIncome, int minimumAmount) {
		super(id, maximumMoney, name, interestRate, loanType, minimumAsset, monthlyIncome);
		this.minimumAmount = minimumAmount;
	}

	public static ManagementFixedDepositProductResponse from(FixedDeposit fixedDeposit) {
		return new ManagementFixedDepositProductResponse(fixedDeposit.getId(), fixedDeposit.getMaximumMoney(),
			fixedDeposit.getName(), fixedDeposit.getInterestRate(), fixedDeposit.getLoanType(),
			fixedDeposit.getMinimumAsset(), fixedDeposit.getMonthlyIncome(), fixedDeposit.getMinimumAmount());
	}
}
