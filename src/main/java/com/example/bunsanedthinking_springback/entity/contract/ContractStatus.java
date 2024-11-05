package com.example.bunsanedthinking_springback.entity.contract;

public enum ContractStatus {

	Terminating(0, "계약 해지"), // 해지된 계약
	Maturing(1, "계약 만기"), // 만기된 계약
	Maintaining(2, "계약"), // 계약 유지중
	CompensationApplying(3, "보상 처리중"), // 보상 요청중
	ContractRequesting(4, "가입 심사중"), // 보험 가입(계약) 요청
	RevivalRequesting(5, "부활 심사중"), // 부활 요청
	RecontractRequesting(6, "만기 재가입 심사중"), // 만기 재가입 요청
	TerminationRequesting(7, "해지 처리중"), // 해지 요청
	EndorsementRequesting(8, "배서 심사중"); // 배서 요청

	private final int value;
	private final String text;

	ContractStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public int getValue() {
		return this.value;
	}

	// 번호에 따라 ContractStatus 반환
	public static ContractStatus fromInt(int number) {
		for (ContractStatus status : ContractStatus.values()) {
			if (status.getValue() == number) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown ContractStatus number: " + number);
	}

	// Enum ordinal에 따른 ContractStatus 반환 메서드
	public static ContractStatus indexOf(int contractStatus) {
		for (ContractStatus status : ContractStatus.values()) {
			if (status.ordinal() == contractStatus) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Contract Status가 입력되었습니다.");
	}
}
