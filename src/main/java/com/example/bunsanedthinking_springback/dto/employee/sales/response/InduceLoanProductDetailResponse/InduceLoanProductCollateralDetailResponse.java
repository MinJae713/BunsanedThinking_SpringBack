package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceLoanProductCollateralDetailResponse extends
	InduceLoanProductDetailResponse {

	private String collateralType;
	private Integer minimumValue;

	public static InduceLoanProductDetailResponse from(Collateral collateral){
		return InduceLoanProductCollateralDetailResponse.builder().id(collateral.getId()).name(collateral.getName())
			.loanType(collateral.getLoanType().getName()).interestRate(collateral.getInterestRate())
			.maximumMoney(collateral.getMaximumMoney()).minimumAsset(collateral.getMinimumAsset()).minimumValue(collateral.getMinimumValue())
			.collateralType(collateral.getCollateralType().getName()).build();
	}
}