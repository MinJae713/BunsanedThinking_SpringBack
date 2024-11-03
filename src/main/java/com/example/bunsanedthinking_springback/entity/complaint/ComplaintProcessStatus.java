package com.example.bunsanedthinking_springback.entity.complaint;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:40
 */

public enum ComplaintProcessStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");
	
	private String text;
	
	ComplaintProcessStatus(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static ComplaintProcessStatus indexOf(int index) {
		for (ComplaintProcessStatus status : ComplaintProcessStatus.values()) {
			if (status.ordinal() == index) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Complaint Process Status가 입력되었습니다.");
	}
}
