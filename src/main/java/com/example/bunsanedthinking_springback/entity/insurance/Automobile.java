package com.example.bunsanedthinking_springback.entity.insurance;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:22
 */

//2024-05-29 김대현
//2024-05-31 김대현
//2024-06-02 김대현
@NoArgsConstructor
@Data
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

	public Automobile(ProductVO productVO, InsuranceVO insuranceVO,
					  int accidentLimit, VehicleType vehicleType,
					  ArrayList<ServiceType> serviceTypeList,
					  List<Contract> contracts, List<Counsel> counsels) {
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

		this.setAccidentLimit(accidentLimit);
		this.setServiceList((ArrayList<ServiceType>) serviceTypeList.stream().distinct().collect(Collectors.toList()));
		this.setVehicleType(vehicleType);

		this.setContractList(contracts);
		this.setCounselList(counsels);
	}

	public AutoMobileVO findVO() {
		return new AutoMobileVO(getId(), vehicleType.ordinal(), accidentLimit);
	}

	public List<String> getServiceListToString(){
		return new ArrayList<>(serviceList.stream().map(ServiceType::getName).toList());
	}

	@Override
	public Product clone() {
		Automobile cloneAutomobile =  new Automobile(getInsuranceType(),getName(), getMaximumMoney(), getAgeRange(), getCoverage(), getMonthlyPremium(), getContractPeriod(), getAccidentLimit(), getVehicleType(), getServiceList());
		cloneAutomobile.setId(getId());
		return cloneAutomobile;
	}
}