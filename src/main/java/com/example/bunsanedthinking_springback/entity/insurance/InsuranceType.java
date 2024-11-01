package com.example.bunsanedthinking_springback.entity.insurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum InsuranceType {

	// 각 타입에 번호 할당
	Injury(1, "상해"),
	Automobile(2, "자동차"),
	Disease(3, "질병");

	private final int value;
	private final String name;

	InsuranceType(int value, String name) {
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
	public static InsuranceType fromInt(int value) {
		for (InsuranceType type : InsuranceType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid int value for InsuranceType: " + value);
	}
}
