package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateInsuranceContractRequest implements UpdateLoanRequest {
	private int id;
	private int maximumMoney;
	private String name;
	private int interestRate;
	private LoanType loanType;
	private int minimumAsset;
	private int monthlyIncome;
	private int insuranceId;

	public Loan toEntity() {
		return new InsuranceContract(id, loanType, name, interestRate, maximumMoney, minimumAsset, insuranceId,
			monthlyIncome);
	}
}
