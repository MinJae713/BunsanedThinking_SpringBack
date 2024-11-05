package com.example.bunsanedthinking_springback.entity.insurance;

public enum ServiceType {

	EmergencyTowing(0, "긴급견인"),
	EmergencyStart(1, "긴급시동"),
	EmergencyRefueling(2, "비상급유"),
	BatteryCharging(3, "배터리충전"),
	EngineOverheatingRepair(4, "엔진과열 수리"),
	TirepunkRepair(5, "타이어펑크 수리");

	private final int value;
	private final String name;

	ServiceType(int value, String name) {
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
	public static ServiceType fromInt(int value) {
		for (ServiceType type : ServiceType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid int value for ServiceType: " + value);
	}

	// Enum ordinal에 따른 ServiceType 반환 메서드
	public static ServiceType indexOf(int index) {
		for (ServiceType service : ServiceType.values()) {
			if (service.ordinal() == index) {
				return service;
			}
		}
		throw new IllegalArgumentException("잘못된 Service Type이 입력되었습니다.");
	}
}
