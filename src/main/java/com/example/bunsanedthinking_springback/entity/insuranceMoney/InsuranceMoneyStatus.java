package com.example.bunsanedthinking_springback.entity.insuranceMoney;

public enum InsuranceMoneyStatus {

	Completed(0, "처리 완료"),
	Unprocessed(1, "미처리");

	private final int value;
	private final String name;

	InsuranceMoneyStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	// 번호에 따라 InsuranceMoneyStatus 반환
	public static InsuranceMoneyStatus fromInt(int number) {
		for (InsuranceMoneyStatus status : InsuranceMoneyStatus.values()) {
			if (status.getValue() == number) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown InsuranceMoneyStatus number: " + number);
	}
}