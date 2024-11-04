package com.example.bunsanedthinking_springback.entity.customer;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum Gender {
	Male("남자"),
	Female("여자");

	private String name;

	Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Gender fromInt(int number) {
		switch (number) {
			case 1:
				return Male;
			case 2:
				return Female;
			default:
				throw new IllegalArgumentException("Unknown Gender number: " + number);
		}
	}

	public int getValue() {
		switch (this) {
			case Male:
				return 1;
			case Female:
				return 2;
			default:
				throw new IllegalArgumentException("Unknown Gender: " + this);
		}
	}
}
