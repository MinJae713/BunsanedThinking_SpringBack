package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:22
 */

//2024-05-29 김대현
//2024-05-31 김대현
//2024-06-02 김대현
@NoArgsConstructor
public class Automobile extends Insurance {

	private int accidentLimit;
	private ArrayList<ServiceType> serviceList;
	private VehicleType vehicleType;

	public Automobile(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage,
			int monthlyPremium, int contractPeriod, int accidentLimit, VehicleType vehicleType,
			ArrayList<ServiceType> serviceTypeList) {
		this.setInsuranceType(insuranceType);
		this.setName(name);
		this.setMaximumMoney(limit);
		this.setAgeRange(ageRange);
		this.setCoverage(coverage);
		this.setMonthlyPremium(monthlyPremium);
		this.setContractPeriod(contractPeriod);
		this.setAccidentLimit(accidentLimit);
		this.setVehicleType(vehicleType);
		this.setServiceList((ArrayList<ServiceType>) serviceTypeList.stream().distinct().collect(Collectors.toList()));
	}
	
	public int getAccidentLimit() {
		return accidentLimit;
	}

	public void setAccidentLimit(int accidentLimit) {
		this.accidentLimit = accidentLimit;
	}

	public ArrayList<ServiceType> getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList<ServiceType> serviceList) {
		this.serviceList = serviceList;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public Product clone() {
		
		Automobile cloneAutomobile =  new Automobile(getInsuranceType(),getName(), getMaximumMoney(), getAgeRange(), getCoverage(), getMonthlyPremium(), getContractPeriod(), getAccidentLimit(), getVehicleType(), getServiceList());
		cloneAutomobile.setId(getId());
		return cloneAutomobile;
	}
}