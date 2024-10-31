package com.example.bunsanedthinking_springback.entity.counsel;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum CounselProcessStatus {
	Completed("처리완료"),
	Unprocessed("미처리");
	
	private String name;
	
	private CounselProcessStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}