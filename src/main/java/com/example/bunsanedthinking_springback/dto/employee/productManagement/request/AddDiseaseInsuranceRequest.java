package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDiseaseInsuranceRequest {
	private String name;
	private Integer maximumMoney;
	private InsuranceType insuranceType;
	private Integer ageRange;
	private Integer monthlyPremium;
	private Integer contractPeriod;
	private String coverage;
	private String diseaseName;
	private Integer diseaseLimit;
	private Integer surgeriesLimit;
}
