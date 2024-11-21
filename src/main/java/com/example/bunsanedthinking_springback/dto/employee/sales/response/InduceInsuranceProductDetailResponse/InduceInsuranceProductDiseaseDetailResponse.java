package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceInsuranceProductDiseaseDetailResponse extends
	InduceInsuranceProductDetailResponse {

	private Integer diseaseLimit;
	private String diseaseName;
	private Integer surgeriesLimit;

	public static InduceInsuranceProductDetailResponse from(Disease disease){
		return InduceInsuranceProductDiseaseDetailResponse.builder().id(disease.getId()).name(disease.getName()).insuranceType(disease.getInsuranceType())
			.ageRange(disease.getAgeRange()).coverage(disease.getCoverage()).monthlyPremium(disease.getMonthlyPremium())
			.contractPeriod(disease.getContractPeriod()).diseaseLimit(disease.getDiseaseLimit())
			.diseaseName(disease.getDiseaseName()).surgeriesLimit(disease.getSurgeriesLimit()).build();
	}
}
