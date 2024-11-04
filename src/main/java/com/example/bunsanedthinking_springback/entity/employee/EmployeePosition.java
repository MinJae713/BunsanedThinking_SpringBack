package com.example.bunsanedthinking_springback.entity.employee;


/**
 * manager, team leader, manager, deputy manager, deputy manager, ,
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum EmployeePosition {
	// 1. 인턴 2. 사원 3. 주임 4. 과장 5. 차장 6. 부장
	Intern("인턴"),
	Staff("사원"),
	SeniorStaff("주임"),
	Manager("과장"),
	DeputyGeneralManager("차장"),
	GeneralManager("부장");

	private String name;

	EmployeePosition(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	// Method to get the integer value corresponding to the enum position
	public int getValue() {
		switch (this) {
			case Intern:
				return 1;
			case Staff:
				return 2;
			case SeniorStaff:
				return 3;
			case Manager:
				return 4;
			case DeputyGeneralManager:
				return 5;
			case GeneralManager:
				return 6;
			default:
				throw new IllegalArgumentException("Unknown position: " + this);
		}
	}

	// Method to get the enum based on an integer input
	public static EmployeePosition fromInt(int number) {
		switch (number) {
			case 1:
				return Intern;
			case 2:
				return Staff;
			case 3:
				return SeniorStaff;
			case 4:
				return Manager;
			case 5:
				return DeputyGeneralManager;
			case 6:
				return GeneralManager;
			default:
				throw new IllegalArgumentException("Unknown EmployeePosition number: " + number);
		}
	}
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
