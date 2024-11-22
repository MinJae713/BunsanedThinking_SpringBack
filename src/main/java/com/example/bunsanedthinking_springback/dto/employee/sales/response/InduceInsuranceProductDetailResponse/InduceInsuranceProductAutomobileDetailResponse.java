package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InduceInsuranceProductAutomobileDetailResponse extends InduceInsuranceProductDetailResponse {

	private Integer accidentLimit;
	private VehicleType vehicleType;
	private List<ServiceType> serviceTypes;

	public static InduceInsuranceProductDetailResponse from(Automobile automobile){
		return InduceInsuranceProductAutomobileDetailResponse.builder().id(automobile.getId()).name(automobile.getName()).insuranceType(automobile.getInsuranceType())
			.ageRange(automobile.getAgeRange()).coverage(automobile.getCoverage()).monthlyPremium(automobile.getMonthlyPremium())
			.contractPeriod(automobile.getContractPeriod()).accidentLimit(automobile.getAccidentLimit())
			.vehicleType(automobile.getVehicleType()).serviceTypes(automobile.getServiceList()).build();
	}
}
