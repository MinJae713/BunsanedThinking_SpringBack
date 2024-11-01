package com.example.bunsanedthinking_springback.entity.insurance;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum VehicleType {
	Small("소형"),
	Medium("중형"),
	Large("대형");

	private String name;

	VehicleType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// 번호에 따라 VehicleType을 반환하는 메서드
	public static VehicleType fromInt(int number) {
		switch (number) {
			case 1:
				return Small;
			case 2:
				return Medium;
			case 3:
				return Large;
			default:
				throw new IllegalArgumentException("Unknown VehicleType number: " + number);
		}
	}
}