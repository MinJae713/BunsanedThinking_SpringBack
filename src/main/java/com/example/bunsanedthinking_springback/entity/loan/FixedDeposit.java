package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;
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
public class FixedDeposit extends Loan {
	private int minimumAmount;

	public FixedDeposit(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
		int minimumAmount, int monthlyIncome){
		this.setLoanType(loanType);
		this.setName(name);
		this.setInterestRate(interestRate);
		this.setMaximumMoney(limit);
		this.setMinimumAsset(minimumAsset);
		this.setMinimumAmount(minimumAmount);
		this.setMonthlyIncome(monthlyIncome);
	}

	public FixedDeposit(ProductVO productVO, LoanVO loanVO, int minimumValue){
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

		this.setMinimumAmount(minimumValue);
	}

	public int getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(int minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
	
	@Override
	public Product clone() {
		FixedDeposit fixedDepositLoan = new FixedDeposit(getLoanType(), getName(),getInterestRate(), getMaximumMoney(), getMinimumAsset(), getMinimumAmount(), getMonthlyIncome());
		fixedDepositLoan.setId(getId());
		return fixedDepositLoan;
	}

}
