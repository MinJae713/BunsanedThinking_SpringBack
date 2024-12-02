package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class InduceInsuranceProductDetailResponse {
	protected Integer id;
	protected String name;
	protected String insuranceType;
	protected Integer ageRange;
	protected String coverage;
	protected Integer monthlyPremium;
	protected Integer contractPeriod;
}
