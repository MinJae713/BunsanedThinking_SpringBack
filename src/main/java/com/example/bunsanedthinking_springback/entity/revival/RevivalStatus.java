package com.example.bunsanedthinking_springback.entity.revival;

public enum RevivalStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");
	
	private String text;
	
	private RevivalStatus(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
