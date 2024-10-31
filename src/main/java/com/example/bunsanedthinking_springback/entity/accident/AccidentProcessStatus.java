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
	
}