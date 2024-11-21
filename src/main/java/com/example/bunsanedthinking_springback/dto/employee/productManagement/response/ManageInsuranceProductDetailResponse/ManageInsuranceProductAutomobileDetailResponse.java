package com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ManageInsuranceProductAutomobileDetailResponse extends ManageInsuranceProductDetailResponse {

	private Integer accidentLimit;
	private VehicleType vehicleType;
	private List<ServiceType> serviceTypes;

	public static ManageInsuranceProductDetailResponse from(Automobile automobile){
		return ManageInsuranceProductAutomobileDetailResponse.builder().name(automobile.getName()).insuranceType(automobile.getInsuranceType())
			.ageRange(automobile.getAgeRange()).coverage(automobile.getCoverage()).monthlyPremium(automobile.getMonthlyPremium())
			.contractPeriod(automobile.getContractPeriod()).accidentLimit(automobile.getAccidentLimit())
			.vehicleType(automobile.getVehicleType()).serviceTypes(automobile.getServiceList()).build();
	}
}
