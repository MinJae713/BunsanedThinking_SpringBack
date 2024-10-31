package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:24
 */

//2024-05-29 김대현
//2024-05-31 김대현
//2024-06-02 김대현
public class Disease extends Insurance {

	private int diseaseLimit;
	private String diseaseName;
	private int surgeriesLimit;

	public Disease(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage,
			int monthlyPremium, int contractPeriod, String diseaseName, int diseaseLimit, int surgeriesLimit) {
		this.setInsuranceType(insuranceType);
		this.setName(name);
		this.setMaximumMoney(limit);
		this.setAgeRange(ageRange);
		this.setCoverage(coverage);
		this.setMonthlyPremium(monthlyPremium);
		this.setContractPeriod(contractPeriod);
		this.setDiseaseName(diseaseName);
		this.setDiseaseLimit(diseaseLimit);
		this.setSurgeriesLimit(surgeriesLimit);
	}
	
	public int getDiseaseLimit() {
		return diseaseLimit;
	}

	public void setDiseaseLimit(int diseaseLimit) {
		this.diseaseLimit = diseaseLimit;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public int getSurgeriesLimit() {
		return surgeriesLimit;
	}

	public void setSurgeriesLimit(int surgeriesLimit) {
		this.surgeriesLimit = surgeriesLimit;
	}

	@Override
	public Product clone() {
		Disease cloneDisease = new Disease(getInsuranceType(), getName(), getMaximumMoney(), getAgeRange(), getCoverage(), getMonthlyPremium(), getContractPeriod(),getDiseaseName(),getDiseaseLimit(), getSurgeriesLimit());
		cloneDisease.setId(getId());
		return cloneDisease;
	}

}