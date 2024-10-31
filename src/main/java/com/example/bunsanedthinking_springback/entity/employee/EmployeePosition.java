package com.example.bunsanedthinking_springback.entity.employee;


/**
 * manager, team leader, manager, deputy manager, deputy manager, ,
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum EmployeePosition {
	
//	1.인턴 2. 사원 3. 주임 4. 과장 5.차장 6. 부장
	
	Intern("인턴"),
	Staff("사원"),
	SeniorStaff("주임"),
	Manager("과장"),
	DeputyGeneralManager("차장"),
	GeneralManager("부장");

	String name;
	
	EmployeePosition(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}