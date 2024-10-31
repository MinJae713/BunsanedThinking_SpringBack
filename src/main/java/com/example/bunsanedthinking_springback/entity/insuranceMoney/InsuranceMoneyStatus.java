package com.example.bunsanedthinking_springback.entity.insuranceMoney;

public enum InsuranceMoneyStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");
	
	private String name;
	
	private InsuranceMoneyStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
