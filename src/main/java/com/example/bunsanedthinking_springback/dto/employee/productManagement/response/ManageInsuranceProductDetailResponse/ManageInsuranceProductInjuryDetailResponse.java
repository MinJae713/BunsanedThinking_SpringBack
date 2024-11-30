package com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ManageInsuranceProductInjuryDetailResponse extends ManageInsuranceProductDetailResponse {

	private String injuryType;
	private Integer surgeriesLimit;

	public static ManageInsuranceProductDetailResponse from(Injury injury){
		return ManageInsuranceProductInjuryDetailResponse.builder().id(injury.getId()).name(injury.getName()).insuranceType(injury.getInsuranceType().getName())
			.ageRange(injury.getAgeRange()).coverage(injury.getCoverage()).monthlyPremium(injury.getMonthlyPremium())
			.contractPeriod(injury.getContractPeriod()).injuryType(injury.getInjuryType().getName()).surgeriesLimit(injury.getSurgeriesLimit())
			.maximumMoney(injury.getMaximumMoney()).build();
	}
}
