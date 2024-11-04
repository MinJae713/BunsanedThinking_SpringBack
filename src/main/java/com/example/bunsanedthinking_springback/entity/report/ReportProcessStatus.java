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

	public static ReportProcessStatus indexOf(int index) {
		for (ReportProcessStatus status : ReportProcessStatus.values()) {
			if (status.ordinal() == index) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Report Process Status가 입력되었습니다.");
	}
}
