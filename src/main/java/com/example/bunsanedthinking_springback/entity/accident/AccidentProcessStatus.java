package com.example.bunsanedthinking_springback.entity.accident;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum AccidentProcessStatus {
	Unprocessed("미처리"),
	Completed("처리 완료"),
	Processing("처리중");
	
	private String name;

	AccidentProcessStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static AccidentProcessStatus indexOf(int index) {
		for (AccidentProcessStatus status : values()) {
			if (status.ordinal() == index) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Accident Process Status가 입력되었습니다.");
	}
	
}
