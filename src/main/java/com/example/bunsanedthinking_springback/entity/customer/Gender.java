package com.example.bunsanedthinking_springback.entity.customer;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum Gender {
	Male(0, "남자"),
	Female(1, "여자");

	private final int value;
	private final String name;

	Gender(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}

	public static Gender fromInt(int number) {
		for (Gender gender : Gender.values()) {
			if (gender.getValue() == number) {
				return gender;
			}
		}
		throw new IllegalArgumentException("Unknown Gender number: " + number);
	}
}