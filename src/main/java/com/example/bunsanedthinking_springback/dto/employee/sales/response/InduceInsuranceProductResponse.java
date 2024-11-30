package com.example.bunsanedthinking_springback.dto.employee.sales.response;

import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InduceInsuranceProductResponse {

	private String name;
	private String insuranceType;
	private Integer id;
	private Integer ageRange;
	private Integer monthlyPremium;

	public static InduceInsuranceProductResponse from(Insurance insurance) {
		return InduceInsuranceProductResponse.builder().name(insurance.getName()).insuranceType(insurance.getInsuranceType().getName()).id(insurance.getId()).ageRange(insurance.getAgeRange()).monthlyPremium(insurance.getMonthlyPremium()).build();
	}
}
