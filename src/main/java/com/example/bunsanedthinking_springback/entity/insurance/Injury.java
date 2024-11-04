package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

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

	public Injury(ProductVO productVO, InsuranceVO insuranceVO, InjuryType injuryType, int surgeriesLimit) {
		int productID = productVO.getId();
		String name = productVO.getName();
		int maximum_money = productVO.getMaximum_money();

		int insurance_type_ordinal = insuranceVO.getInsurance_type();
		InsuranceType insurance_type = InsuranceType.values()[insurance_type_ordinal];
		int age_range = insuranceVO.getAge_range();
		int monthly_premium = insuranceVO.getMonthly_premium();
		int contract_period = insuranceVO.getContract_period();
		String coverage = insuranceVO.getCoverage();

		this.setId(productID);
		this.setName(name);
		this.setMaximumMoney(maximum_money);

		this.setInsuranceType(insurance_type);
		this.setAgeRange(age_range);
		this.setMonthlyPremium(monthly_premium);
		this.setContractPeriod(contract_period);
		this.setCoverage(coverage);

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