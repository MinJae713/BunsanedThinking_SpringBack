package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import java.util.ArrayList;
import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAutomobileInsuranceRequest {

	private Integer ageRange;
	private Integer contractPeriod;
	private String coverage;
	private Integer id;
	private String name;
	private InsuranceType insuranceType;
	private Integer monthlyPremium;
	private ArrayList<ServiceType> serviceTypes;
	private VehicleType vehicleType;
	private Integer maximumMoney;

	public Automobile toEntity(){
		Automobile automobile = new Automobile(insuranceType , name, maximumMoney, ageRange, coverage, monthlyPremium
			, contractPeriod,3, vehicleType, serviceTypes);
		automobile.setId(id);
		return automobile;
	}
}