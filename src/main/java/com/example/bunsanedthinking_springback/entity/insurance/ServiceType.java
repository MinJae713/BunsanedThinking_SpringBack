package com.example.bunsanedthinking_springback.entity.insurance;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum ServiceType {

//	1.긴급견인 2. 긴급시동 3. 비상급유 4. 배터리충전 5. 엔진과열 수리 6. 타이어펑크 수리 
	EmergencyTowing("긴급견인"), EmergencyStart("긴급시동"), EmergencyRefueling("비상급유"), BatteryCharging("배터리충전"),
	EngineOverheatingRepair("엔진과열 수리"), TirepunkRepair("타이어펑크 수리");

	private String name;

	ServiceType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}