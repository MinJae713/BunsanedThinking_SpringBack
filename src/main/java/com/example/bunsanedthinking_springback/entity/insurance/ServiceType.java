package com.example.bunsanedthinking_springback.entity.insurance;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum ServiceType {

	EmergencyTowing("긴급견인"),
	EmergencyStart("긴급시동"),
	EmergencyRefueling("비상급유"),
	BatteryCharging("배터리충전"),
	EngineOverheatingRepair("엔진과열 수리"),
	TirepunkRepair("타이어펑크 수리");

	private final String name;

	ServiceType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public static ServiceType fromInt(int number) {
		switch (number) {
			case 1:
				return EmergencyTowing;
			case 2:
				return EmergencyStart;
			case 3:
				return EmergencyRefueling;
			case 4:
				return BatteryCharging;
			case 5:
				return EngineOverheatingRepair;
			case 6:
				return TirepunkRepair;
			default:
				throw new IllegalArgumentException("Unknown ServiceType number: " + number);
		}
	}

	public int getValue() {
		switch (this) {
			case EmergencyTowing:
				return 1;
			case EmergencyStart:
				return 2;
			case EmergencyRefueling:
				return 3;
			case BatteryCharging:
				return 4;
			case EngineOverheatingRepair:
				return 5;
			case TirepunkRepair:
				return 6;
			default:
				throw new AssertionError("Unknown ServiceType: " + this);
		}
	}
	public static ServiceType indexOf(int index) {
		for (ServiceType service : ServiceType.values()) {
			if (service.ordinal() == index) {
				return service;
			}
		}
		throw new IllegalArgumentException("잘못된 Service Type이 입력되었습니다.");
	}

}
