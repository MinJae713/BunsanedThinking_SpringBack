package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiseaseInsuranceRequest {

	private Integer ageRange;
	private Integer contractPeriod;
	private String coverage;
	private Integer diseaseLimit;
	private String diseaseName;
	private Integer id;
	private InsuranceType insuranceType;
	private String name;
	private Integer monthlyPremium;
	private Integer surgeriesLimit;
	private Integer maximumMoney;

	public Disease toEntity(){
		Disease disease = new Disease(insuranceType , name, maximumMoney, ageRange, coverage, monthlyPremium
			, contractPeriod, diseaseName, diseaseLimit, surgeriesLimit);
		disease.setId(id);
		return disease;
	}
}