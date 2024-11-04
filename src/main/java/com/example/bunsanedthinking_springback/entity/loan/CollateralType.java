package com.example.bunsanedthinking_springback.entity.loan;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum CollateralType {
	RealEstate("부동산"),
	Car("자동차");

	private String name;

	CollateralType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static CollateralType fromInt(int number) {
		switch (number) {
			case 1:
				return RealEstate;
			case 2:
				return Car;
			default:
				throw new IllegalArgumentException("Unknown CollateralType number: " + number);
		}
	}
}