package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceContractVO {
	private int product_id;
	private int insurance_id;

	public static InsuranceContractVO from(InsuranceContract insuranceContract) {
		return new InsuranceContractVO(insuranceContract.getId(), insuranceContract.getInsuranceId());
	}
}
