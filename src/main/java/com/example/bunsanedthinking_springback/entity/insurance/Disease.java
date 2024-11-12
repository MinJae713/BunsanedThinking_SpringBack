package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:24
 */

@Data
@NoArgsConstructor
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

	public Disease(ProductVO productVO, InsuranceVO insuranceVO, String diseaseName, int diseaseLimit, int surgeriesLimit) {
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

		this.setDiseaseName(diseaseName);
		this.setDiseaseLimit(diseaseLimit);
		this.setSurgeriesLimit(surgeriesLimit);
	}

	public DiseaseVO findVO() {
		return new DiseaseVO(getId(), diseaseName,
				diseaseLimit, surgeriesLimit);
	}

	@Override
	public Product clone() {
		Disease cloneDisease = new Disease(getInsuranceType(), getName(), getMaximumMoney(), getAgeRange(), getCoverage(), getMonthlyPremium(), getContractPeriod(),getDiseaseName(),getDiseaseLimit(), getSurgeriesLimit());
		cloneDisease.setId(getId());
		return cloneDisease;
	}

}