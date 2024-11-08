package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
//2024-06-02 김대현
@NoArgsConstructor
public class Collateral extends Loan {

	private CollateralType collateralType;
	private int minimumValue;

	public Collateral(int id, LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			CollateralType collateralType, int minimumValue, int monthlyIncome) {
		this.setId(id);
		this.setLoanType(loanType);
		this.setName(name);
		this.setInterestRate(interestRate);
		this.setMaximumMoney(limit);
		this.setMinimumAsset(minimumAsset);
		this.setCollateralType(collateralType);
		this.setMinimumValue(minimumValue);
		this.setMonthlyIncome(monthlyIncome);
	}

	public Collateral(ProductVO productVO, LoanVO loanVO, CollateralType collateralType, int minimumValue) {
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

		this.setCollateralType(collateralType);
		this.setMinimumValue(minimumValue);
	}

	public CollateralVO getVO() {
		return new CollateralVO(getId(), collateralType.ordinal(), minimumValue);
	}

	public CollateralType getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(CollateralType collateralType) {
		this.collateralType = collateralType;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public void setMinimumValue(int minimumValue) {
		this.minimumValue = minimumValue;
	}
	
	@Override
	public Product clone() {
		Collateral collateralLoan = new Collateral(getId(), getLoanType(), getName(),getInterestRate(), getMaximumMoney(), getMinimumAsset(), getCollateralType() ,getMinimumValue(), getMonthlyIncome());
		collateralLoan.setId(getId());
		return collateralLoan;
	}

}
