package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
//2024-06-02 김대현
//2024-06-04 김대현
public abstract class Loan extends Product {

	private int interestRate;
	private LoanType loanType;
	private int minimumAsset;
	private int monthlyIncome;

	public static final int LOAN_SERIAL_NUMBER = 200;
	
	public Loan(){

	}

	public void buy(){

	}

	public void view(){

	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public int getMinimumAsset() {
		return minimumAsset;
	}

	public void setMinimumAsset(int minimumAsset) {
		this.minimumAsset = minimumAsset;
	}

	public int getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(int monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

}