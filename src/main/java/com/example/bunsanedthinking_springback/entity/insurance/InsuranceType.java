package com.example.bunsanedthinking_springback.entity.insurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum InsuranceType {
	
//	1. 질병 2. 상해 3. 자동차
	Injury("상해"),
	Automobile("자동차"),
	Disease("질병");

	private String name;
	
	InsuranceType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static InsuranceType indexOf(int index) {
		for (InsuranceType type : InsuranceType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Insurance Type이 입력되었습니다.");
	}
	
}
