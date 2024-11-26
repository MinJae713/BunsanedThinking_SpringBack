package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.managementLoanProduct;

import com.example.bunsanedthinking_springback.entity.loan.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagementLoanProductResponse {
	private int id;
	private int maximumMoney;
	private String name;
	private int interestRate;
	private String loanType;
	private int minimumAsset;
	private int monthlyIncome;

	public static ManagementLoanProductResponse from(Loan loan) {
		return new ManagementLoanProductResponse(loan.getId(), loan.getMaximumMoney(), loan.getName(),
			loan.getInterestRate(), loan.getLoanType().getName(), loan.getMinimumAsset(), loan.getMonthlyIncome());
	}
}
