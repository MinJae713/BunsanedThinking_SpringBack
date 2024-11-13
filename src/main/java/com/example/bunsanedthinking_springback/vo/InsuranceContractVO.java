package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceContractVO {
	private int product_id;
	private int insurance_id;

	public InsuranceContract getEntity(ProductVO productVO, LoanVO loanVO, List<Contract> contracts) {
		InsuranceContract insuranceContract = new InsuranceContract();
		insuranceContract.setInsuranceId(insurance_id);

		insuranceContract.setInterestRate(loanVO.getInterest_rate());
		insuranceContract.setLoanType(LoanType.values()[loanVO.getLoan_type()]);
		insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());
		insuranceContract.setMonthlyIncome(loanVO.getMonthly_income());

		insuranceContract.setId(productVO.getId());
		insuranceContract.setMaximumMoney(productVO.getMaximum_money());
		insuranceContract.setName(productVO.getName());

		insuranceContract.setContractList(contracts);
		return insuranceContract;
	}
	public static InsuranceContractVO from(InsuranceContract insuranceContract) {
		return new InsuranceContractVO(insuranceContract.getId(), insuranceContract.getInsuranceId());
	}
}
