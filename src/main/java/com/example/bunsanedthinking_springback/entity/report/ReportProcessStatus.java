package com.example.bunsanedthinking_springback.entity.report;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum ReportProcessStatus {
	Completed("처리완료"),
	Unprocessed("미처리");
	
	private String name;
	
	private ReportProcessStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}