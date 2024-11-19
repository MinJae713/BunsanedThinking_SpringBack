package com.example.bunsanedthinking_springback.dto.employee.productManagement.response;

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
public class GetAllResponseResponse {
	private String name;
	private InsuranceType insuranceType;
	private Integer id;
	private Integer ageRange;
	private Integer monthlyPremium;

	public static GetAllResponseResponse of(Insurance insurance){
		return GetAllResponseResponse.builder().name(insurance.getName()).insuranceType(insurance.getInsuranceType()).id(insurance.getId()).ageRange(insurance.getAgeRange()).monthlyPremium(insurance.getMonthlyPremium()).build();
	}
}
