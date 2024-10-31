package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:25
 */

//2024-05-29 김대현
//2024-05-31 김대현
public abstract class Insurance extends Product {

	public static final int INSURANCE_SERIAL_NUMBER = 100;

	
	private int ageRange;
	private int contractPeriod;
	private String coverage;
	private InsuranceType insuranceType;
	private int monthlyPremium;

	public Insurance() {
		super();
	}

	public Product add() {
		return this;
	}
	
	public Product add(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
			 Date contractPeriod, String diseaseName, int diseaseLimit, int surgeriesLimit) {
		return this;
	}
	
	public Product add(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium2,
			Date contractPeriod, InjuryType injuryType, int surgeriesLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product add(InsuranceType insuranceType, String name, int limit, int ageRange2, String coverage2, int monthlyPremium2,
			Date contractPeriod, int accidentLimit, VehicleType vehicleType, ArrayList<ServiceType> serviceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}

	public int getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(int contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public int getMonthlyPremium() {
		return monthlyPremium;
	}

	public void setMonthlyPremium(int monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}
	
}