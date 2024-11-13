package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InjuryVO {
	private int product_id;
	private int injury_type;
	private int surgeries_limit;

	public Injury getEntity(ProductVO productVO, InsuranceVO insuranceVO,
							List<Contract> contracts, List<Counsel> counsels) {
		Injury injury = new Injury();
		injury.setInjuryType(InjuryType.values()[injury_type]);
		injury.setSurgeriesLimit(surgeries_limit);

		injury.setAgeRange(insuranceVO.getAge_range());
		injury.setContractPeriod(insuranceVO.getContract_period());
		injury.setCoverage(insuranceVO.getCoverage());
		injury.setInsuranceType(InsuranceType.values()[insuranceVO.getInsurance_type()]);
		injury.setMonthlyPremium(insuranceVO.getMonthly_premium());

		injury.setId(product_id);
		injury.setMaximumMoney(productVO.getMaximum_money());
		injury.setName(productVO.getName());

		injury.setContractList(contracts);
		injury.setCounselList(counsels);
		return injury;
	}
}
