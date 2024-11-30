package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceInsuranceProductInjuryDetailResponse extends InduceInsuranceProductDetailResponse {

	private InjuryType injuryType;
	private Integer surgeriesLimit;

	public static InduceInsuranceProductDetailResponse from(Injury injury){
		return InduceInsuranceProductInjuryDetailResponse.builder().id(injury.getId()).name(injury.getName()).insuranceType(injury.getInsuranceType().getName())
			.ageRange(injury.getAgeRange()).coverage(injury.getCoverage()).monthlyPremium(injury.getMonthlyPremium())
			.contractPeriod(injury.getContractPeriod()).injuryType(injury.getInjuryType()).surgeriesLimit(injury.getSurgeriesLimit()).build();
	}
}
