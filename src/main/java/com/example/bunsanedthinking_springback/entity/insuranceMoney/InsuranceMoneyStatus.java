package com.example.bunsanedthinking_springback.entity.insuranceMoney;

public enum InsuranceMoneyStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");

	private String name;

	InsuranceMoneyStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static InsuranceMoneyStatus fromInt(int number) {
		switch (number) {
			case 1:
				return Completed;
			case 2:
				return Unprocessed;
			default:
				throw new IllegalArgumentException("Unknown InsuranceMoneyStatus number: " + number);
		}
	}

	public int getValue() {
		switch (this) {
			case Completed:
				return 1;
			case Unprocessed:
				return 2;
			default:
				throw new IllegalArgumentException("Unknown InsuranceMoneyStatus: " + this);
		}
	}
}

