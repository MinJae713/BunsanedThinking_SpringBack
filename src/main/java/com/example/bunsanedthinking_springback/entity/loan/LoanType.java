package com.example.bunsanedthinking_springback.entity.loan;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum LoanType {
	
	
	Collateral("담보"),
	FixedDeposit("정기 예금"),
	InsuranceContract("보험 계약");
	
	private String name;

	LoanType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
}