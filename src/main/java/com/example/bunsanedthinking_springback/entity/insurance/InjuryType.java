package com.example.bunsanedthinking_springback.entity.insurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum InjuryType {
	Minor("경상"),
	Serious("중상");

	private String name;

	InjuryType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static InjuryType fromInt(int number) {
		switch (number) {
			case 1:
				return Minor;
			case 2:
				return Serious;
			default:
				throw new IllegalArgumentException("Unknown InjuryType number: " + number);
		}
	}
}
