package com.example.bunsanedthinking_springback.entity.insurance;

public enum InjuryType {
	Minor(0, "경상"),
	Serious(1, "중상");

	private final int value;
	private final String name;

	InjuryType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}

	// int 값을 Enum으로 변환하는 정적 메서드
	public static InjuryType fromInt(int value) {
		for (InjuryType type : InjuryType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid int value for InjuryType: " + value);
	}

	// Enum ordinal에 따른 InjuryType 반환 메서드
	public static InjuryType indexOf(int index) {
		for (InjuryType type : InjuryType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Injury Type이 입력되었습니다.");
	}
}