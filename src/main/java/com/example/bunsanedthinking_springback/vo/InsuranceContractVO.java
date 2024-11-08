package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceContractVO {
	private int product_id;
	private int insurance_id;

	public InsuranceContract getEntity(ProductVO productVO, LoanVO loanVO) {
		InsuranceContract insuranceContract = new InsuranceContract();
		insuranceContract.setInsuranceId(insurance_id);

		insuranceContract.setInterestRate(loanVO.getInterest_rate());
		insuranceContract.setLoanType(LoanType.values()[loanVO.getLoan_type()]);
		insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());
		insuranceContract.setMonthlyIncome(loanVO.getMonthly_income());

		insuranceContract.setId(productVO.getId());
		insuranceContract.setMaximumMoney(productVO.getMaximum_money());
		insuranceContract.setName(productVO.getName());
		return insuranceContract;
	}
}
