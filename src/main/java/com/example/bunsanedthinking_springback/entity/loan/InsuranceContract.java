package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */

//2024-06-02 김대현
//2024-06-04 김대현
public class InsuranceContract extends Loan {

	private int productID;

	public InsuranceContract(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			int parameter) {
		this.setLoanType(loanType);
		this.setName(name);
		this.setInterestRate(interestRate);
		this.setMaximumMoney(limit);
		this.setMinimumAsset(minimumAsset);
		this.setProductID(parameter);
	}

	@Override
	public Product clone() {
		InsuranceContract insuranceContractLoan = new InsuranceContract(getLoanType(), getName(),getInterestRate(), getMaximumMoney(), getMinimumAsset(),getProductID());
		insuranceContractLoan.setId(getId());
		return insuranceContractLoan;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}