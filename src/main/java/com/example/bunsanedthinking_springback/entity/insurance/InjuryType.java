package com.example.bunsanedthinking_springback.entity.insurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public enum InjuryType {
	Minor("경상"),
	Serious("중상");

	private String name;
	
	InjuryType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static InjuryType indexOf(int index) {
		for (InjuryType type : InjuryType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Injury Type이 입력되었습니다.");
	}
	
}
