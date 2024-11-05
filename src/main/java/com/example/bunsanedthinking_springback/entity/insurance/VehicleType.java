package com.example.bunsanedthinking_springback.entity.insurance;

public enum VehicleType {
	Small(0, "소형"),
	Medium(1, "중형"),
	Large(2, "대형");

	private final int value;
	private final String name;

	VehicleType(int value, String name) {
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
	public static VehicleType fromInt(int value) {
		for (VehicleType type : VehicleType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid int value for VehicleType: " + value);
	}

	// Enum ordinal에 따른 VehicleType 반환 메서드
	public static VehicleType indexOf(int index) {
		for (VehicleType type : VehicleType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Vehicle Type이 입력되었습니다.");
	}
}