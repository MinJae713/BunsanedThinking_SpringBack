package com.example.bunsanedthinking_springback.entity.insurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum InsuranceType {
	
//	1. 질병 2. 자동차 3. 상해
	// 이거 순서 바꿨심다 - 원래는 (상해, 자동차, 질병) 순인디,
	// 상해 테이블에 InjuryType이 질병, 질병 테이블에 InjuryType이 질병인 더미데이터가 있으요
	// 1001이 Disease, 1002가 Automobile, 1003이 injury에 들어간 상태
	Disease("질병"),
	Automobile("자동차"),
	Injury("상해");

	private String name;
	
	InsuranceType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}