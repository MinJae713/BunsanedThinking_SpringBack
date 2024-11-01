package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;

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

	public Collateral(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			CollateralType collateralType, int minimumValue) {
		this.setLoanType(loanType);
		this.setName(name);
		this.setInterestRate(interestRate);
		this.setMaximumMoney(limit);
		this.setMinimumAsset(minimumAsset);
		this.setCollateralType(collateralType);
		this.setMinimumValue(minimumValue);
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
		Collateral collateralLoan = new Collateral(getLoanType(), getName(),getInterestRate(), getMaximumMoney(), getMinimumAsset(), getCollateralType() ,getMinimumValue());
		collateralLoan.setId(getId());
		return collateralLoan;
	}

}