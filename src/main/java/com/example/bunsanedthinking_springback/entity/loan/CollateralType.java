package com.example.bunsanedthinking_springback.entity.loan;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum CollateralType {
	RealEstate(0, "부동산"),
	Car(1, "자동차");

	private final int value;
	private final String name;

	CollateralType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}

	// 번호에 따라 CollateralType을 반환하는 메서드
	public static CollateralType fromInt(int number) {
		for (CollateralType type : CollateralType.values()) {
			if (type.getValue() == number) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown CollateralType number: " + number);
	}

	// 인덱스를 기준으로 CollateralType을 반환하는 메서드
	public static CollateralType indexOf(int index) {
		for (CollateralType type : CollateralType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Collateral Type이 전달되었습니다.");
	}
}
