package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:25
 */
@Data
public abstract class Insurance extends Product {
	private int ageRange;
	private int contractPeriod;
	private String coverage;
	private InsuranceType insuranceType;
	private int monthlyPremium;

	public Insurance() {
		super();
	}

	public InsuranceVO findInsuranceVO() {
		return new InsuranceVO(getId(),
				insuranceType.ordinal(),
				ageRange, monthlyPremium,
				contractPeriod, coverage);
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
}