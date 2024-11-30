package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceLoanProductFixedDepositDetailResponse extends
	InduceLoanProductDetailResponse {

	private Integer minimumAmount;

	public static InduceLoanProductDetailResponse from(FixedDeposit fixedDeposit){
		return InduceLoanProductFixedDepositDetailResponse.builder().id(fixedDeposit.getId()).name(fixedDeposit.getName())
			.loanType(fixedDeposit.getLoanType().getName()).interestRate(fixedDeposit.getInterestRate())
			.maximumMoney(fixedDeposit.getMaximumMoney()).minimumAsset(fixedDeposit.getMinimumAsset()).minimumAmount(fixedDeposit.getMinimumAmount()).build();
	}
}