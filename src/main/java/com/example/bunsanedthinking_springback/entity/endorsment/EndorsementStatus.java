package com.example.bunsanedthinking_springback.entity.endorsment;

public enum EndorsementStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");
	
	private String text;
	
	private EndorsementStatus(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
