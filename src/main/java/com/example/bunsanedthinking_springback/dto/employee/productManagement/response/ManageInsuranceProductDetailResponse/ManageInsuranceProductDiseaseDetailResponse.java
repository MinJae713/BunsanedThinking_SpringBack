package com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ManageInsuranceProductDiseaseDetailResponse extends ManageInsuranceProductDetailResponse {

	private Integer diseaseLimit;
	private String diseaseName;
	private Integer surgeriesLimit;

	public static ManageInsuranceProductDetailResponse from(Disease disease){
		return ManageInsuranceProductDiseaseDetailResponse.builder().id(disease.getId()).name(disease.getName()).insuranceType(disease.getInsuranceType())
			.ageRange(disease.getAgeRange()).coverage(disease.getCoverage()).monthlyPremium(disease.getMonthlyPremium())
			.contractPeriod(disease.getContractPeriod()).diseaseLimit(disease.getDiseaseLimit())
			.diseaseName(disease.getDiseaseName()).surgeriesLimit(disease.getSurgeriesLimit())
			.maximumMoney(disease.getMaximumMoney()).build();
	}
}
