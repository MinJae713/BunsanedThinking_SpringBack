package com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ManageInsuranceProductAutomobileDetailResponse extends ManageInsuranceProductDetailResponse {

	private Integer accidentLimit;
	private String vehicleType;
	private List<String> serviceTypes;

	public static ManageInsuranceProductDetailResponse from(Automobile automobile){
		return ManageInsuranceProductAutomobileDetailResponse.builder().id(automobile.getId()).name(automobile.getName()).insuranceType(automobile.getInsuranceType().getName())
			.ageRange(automobile.getAgeRange()).coverage(automobile.getCoverage()).monthlyPremium(automobile.getMonthlyPremium())
			.contractPeriod(automobile.getContractPeriod()).accidentLimit(automobile.getAccidentLimit())
			.vehicleType(automobile.getVehicleType().getName()).serviceTypes(automobile.getServiceListToString()).maximumMoney(automobile.getMaximumMoney()).build();
	}
}
