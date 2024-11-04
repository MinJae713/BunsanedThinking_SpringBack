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
	public static CollateralType indexOf(int index) {
		for (CollateralType type : CollateralType.values()) {
			if (type.ordinal() == index)
				return type;
		}
		throw new IllegalArgumentException("잘못된 Collateral Type이 전달되었습니다.");
	}
}
