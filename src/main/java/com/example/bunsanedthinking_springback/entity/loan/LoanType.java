package com.example.bunsanedthinking_springback.entity.loan;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum LoanType {

	Collateral(1, "담보"),
	FixedDeposit(2, "정기 예금"),
	InsuranceContract(3, "보험 계약");

	private final int value;
	private final String name;

	LoanType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public int getValue() {
		return this.value;
	}

	public static LoanType fromInt(int value) {
		for (LoanType type : LoanType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid int value for LoanType: " + value);
	}
	public static LoanType indexOf(int index) {
		for (LoanType type : LoanType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Loan Type이 전달되었습니다.");
	}
}
