package com.example.bunsanedthinking_springback.entity.employee;

/**
 * manager, team leader, manager, deputy manager, deputy manager, ,
 * @version 1.0
 * @created 27-5-2024
 */
public enum EmployeePosition {
	// 0. 인턴 1. 사원 2. 주임 3. 과장 4. 차장 5. 부장
	Intern(0, "인턴"),
	Staff(1, "사원"),
	SeniorStaff(2, "주임"),
	Manager(3, "과장"),
	DeputyGeneralManager(4, "차장"),
	GeneralManager(5, "부장");

	private final int value;
	private final String name;

	EmployeePosition(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// Method to get the integer value corresponding to the enum position
	public int getValue() {
		return this.value;
	}

	// Method to get the enum based on an integer input
	public static EmployeePosition fromInt(int number) {
		for (EmployeePosition position : EmployeePosition.values()) {
			if (position.getValue() == number) {
				return position;
			}
		}
		throw new IllegalArgumentException("Unknown EmployeePosition number: " + number);
	}

	public static EmployeePosition indexOf(int index) {
		for (EmployeePosition position : EmployeePosition.values()) {
			if (position.ordinal() == index) {
				return position;
			}
		}
		throw new IllegalArgumentException("잘못된 Employee Position이 입력되었습니다.");
	}
}
