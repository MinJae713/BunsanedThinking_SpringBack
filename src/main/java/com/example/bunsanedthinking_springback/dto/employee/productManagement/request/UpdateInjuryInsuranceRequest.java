package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInjuryInsuranceRequest {

	private Integer ageRange;
	private Integer contractPeriod;
	private String coverage;
	private Integer id;
	private InjuryType injuryType;
	private String name;
	private InsuranceType insuranceType;
	private Integer monthlyPremium;
	private Integer surgeriesLimit;
	private Integer maximumMoney;

	public Injury toEntity(){
		Injury injury = new Injury(insuranceType , name, maximumMoney, ageRange, coverage, monthlyPremium
			, contractPeriod, injuryType, surgeriesLimit);
		injury.setId(id);
		return injury;
	}
}