package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:25
 */
	
//2024-05-29 김대현
//2024-05-31 김대현
//2024-06-02 김대현
public class Injury extends Insurance {

	private InjuryType injuryType;
	private int surgeriesLimit;

	public Injury(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium, int contractPeriod, InjuryType injuryType, int surgeriesLimit) {
		this.setInsuranceType(insuranceType);
		this.setName(name);
		this.setMaximumMoney(limit);
		this.setAgeRange(ageRange);
		this.setCoverage(coverage);
		this.setMonthlyPremium(monthlyPremium);
		this.setContractPeriod(contractPeriod);
		this.setInjuryType(injuryType);
		this.setSurgeriesLimit(surgeriesLimit);
	}

	public InjuryType getInjuryType() {
		return injuryType;
	}

	public void setInjuryType(InjuryType injuryType) {
		this.injuryType = injuryType;
	}

	public int getSurgeriesLimit() {
		return surgeriesLimit;
	}

	public void setSurgeriesLimit(int surgeriesLimit) {
		this.surgeriesLimit = surgeriesLimit;
	}

	@Override
	public Product clone() {
		Injury cloneInjury = new Injury(getInsuranceType(), getName(), getMaximumMoney(), getAgeRange(), getCoverage(), getMonthlyPremium(), getContractPeriod(), getInjuryType(), getSurgeriesLimit());
		cloneInjury.setId(getId());
		return cloneInjury;
	}

}