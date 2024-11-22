package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagementLoanProductResponse {
	private int id;
	private int maximumMoney;
	private String name;
	private int interestRate;
	private LoanType loanType;
	private int minimumAsset;
	private int monthlyIncome;

	public static ManagementLoanProductResponse from(Loan loan) {
		return new ManagementLoanProductResponse(loan.getId(), loan.getMaximumMoney(), loan.getName(),
			loan.getInterestRate(), loan.getLoanType(), loan.getMinimumAsset(), loan.getMonthlyIncome());
	}
}
