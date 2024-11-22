package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceLoanProductInsuranceContractDetailResponse extends InduceLoanProductDetailResponse {

	private Integer productId;

	public static InduceLoanProductDetailResponse from(InsuranceContract insuranceContract){
		return InduceLoanProductInsuranceContractDetailResponse.builder().id(insuranceContract.getId()).name(insuranceContract.getName())
			.loanType(insuranceContract.getLoanType()).loanType(insuranceContract.getLoanType()).interestRate(insuranceContract.getInterestRate())
			.maximumMoney(insuranceContract.getMaximumMoney()).minimumAsset(insuranceContract.getMinimumAsset()).productId(insuranceContract.getInsuranceId()).build();
	}
}
