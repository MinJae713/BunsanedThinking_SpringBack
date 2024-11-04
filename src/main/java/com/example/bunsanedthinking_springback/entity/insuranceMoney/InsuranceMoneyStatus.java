package com.example.bunsanedthinking_springback.entity.insuranceMoney;

public enum InsuranceMoneyStatus {
	Completed("처리 완료"),
	Unprocessed("미처리");
	
	private String name;
	
	InsuranceMoneyStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static InsuranceMoneyStatus indexOf(int index) {
		for (InsuranceMoneyStatus status : InsuranceMoneyStatus.values()) {
			if (status.ordinal() == index) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Insurance Money Status가 입력되었습니다.");
	}
}
