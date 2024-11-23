package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddLoanProductRequest {
	private LoanType loanType;
	private String name;
	private int interestRate;
	private int maximumMoney;
	private int minimumAsset;
	private int parameter;
	private int monthlyIncome;
}
