package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.ManagementLoanProduct;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;

@Getter
public class ManagementInsuranceContractProductResponse extends ManagementLoanProductResponse {
	private int insuranceId;

	public ManagementInsuranceContractProductResponse(int id, int maximumMoney, String name, int interestRate,
		LoanType loanType, int minimumAsset, int monthlyIncome, int insuranceId) {
		super(id, maximumMoney, name, interestRate, loanType.getName(), minimumAsset, monthlyIncome);
		this.insuranceId = insuranceId;
	}

	public static ManagementInsuranceContractProductResponse from(InsuranceContract insuranceContract) {
		return new ManagementInsuranceContractProductResponse(insuranceContract.getId(),
			insuranceContract.getMaximumMoney(), insuranceContract.getName(), insuranceContract.getInterestRate(),
			insuranceContract.getLoanType(), insuranceContract.getMinimumAsset(), insuranceContract.getMonthlyIncome(),
			insuranceContract.getInsuranceId());
	}
}
