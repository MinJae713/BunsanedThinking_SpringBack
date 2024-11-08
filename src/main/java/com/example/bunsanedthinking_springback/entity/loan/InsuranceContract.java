package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */

//2024-06-02 김대현
//2024-06-04 김대현
@NoArgsConstructor
public class InsuranceContract extends Loan {

	private int insuranceId;

	public InsuranceContract(int id, LoanType loanType, String name, int interestRate, int limit,
		int minimumAsset, int insuranceId, int monthlyIncome) {
		this.setId(id);
		this.setLoanType(loanType);
		this.setName(name);
		this.setInterestRate(interestRate);
		this.setMaximumMoney(limit);
		this.setMinimumAsset(minimumAsset);
		this.setInsuranceId(insuranceId);
		this.setMonthlyIncome(monthlyIncome);
	}

	public InsuranceContractVO findVO() {
		return new InsuranceContractVO(getId(), insuranceId);
	}

	public InsuranceContract(ProductVO productVO, LoanVO loanVO, int insuranceId) {
		int id = productVO.getId();
		String name = productVO.getName();
		int maximumMoney = productVO.getMaximum_money();

		LoanType loanType = LoanType.values()[loanVO.getLoan_type()];
		int minimumAsset = loanVO.getMinimum_asset();
		int monthlyIncome = loanVO.getMonthly_income();
		int interestRate = loanVO.getInterest_rate();

		this.setId(id);
		this.setName(name);
		this.setMaximumMoney(maximumMoney);

		this.setLoanType(loanType);
		this.setMinimumAsset(minimumAsset);
		this.setMonthlyIncome(monthlyIncome);
		this.setInterestRate(interestRate);

		this.setInsuranceId(insuranceId);
	}

	@Override
	public Product clone() {
		InsuranceContract insuranceContractLoan = new InsuranceContract(getId(), getLoanType(), getName(),getInterestRate(), getMaximumMoney(), getMinimumAsset(),
			getInsuranceId(), getMonthlyIncome());
		insuranceContractLoan.setId(getId());
		return insuranceContractLoan;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

}
