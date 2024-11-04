package com.example.bunsanedthinking_springback.entity.contract;


/**
 * , , ,
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum ContractStatus {
	Terminating("계약 해지"), // 해지된 계약
	Maturing("계약 만기"), // 만기된 계약
	Maintaining("계약"), // 계약 유지중
	CompensationApplying("보상 처리중"), // 보상 요청중
	ContractRequesting("가입 심사중"), // 보험 가입(계약) 요청
	RevivalRequesting("부활 심사중"), // 부활 요청
	RecontractRequesting("만기 재가입 심사중"), // 만기 재가입 요청
	TerminationRequesting("해지 처리중"), // 해지 요청
	EndorsementRequesting("배서 심사중"); // 배서 요청

	private String text;

	ContractStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	// 번호에 따라 ContractStatus 반환
	public static ContractStatus fromInt(int number) {
		switch (number) {
			case 1:
				return Terminating;
			case 2:
				return Maturing;
			case 3:
				return Maintaining;
			case 4:
				return CompensationApplying;
			case 5:
				return ContractRequesting;
			case 6:
				return RevivalRequesting;
			case 7:
				return RecontractRequesting;
			case 8:
				return TerminationRequesting;
			case 9:
				return EndorsementRequesting;
			default:
				throw new IllegalArgumentException("Unknown ContractStatus number: " + number);
		}
	}

	// ContractStatus의 번호 반환
	public int getValue() {
		switch (this) {
			case Terminating:
				return 1;
			case Maturing:
				return 2;
			case Maintaining:
				return 3;
			case CompensationApplying:
				return 4;
			case ContractRequesting:
				return 5;
			case RevivalRequesting:
				return 6;
			case RecontractRequesting:
				return 7;
			case TerminationRequesting:
				return 8;
			case EndorsementRequesting:
				return 9;
			default:
				throw new IllegalArgumentException("Unknown ContractStatus: " + this);
		}
	public static ContractStatus indexOf(int contractStatus) {
		for (ContractStatus status : ContractStatus.values()) {
			if (status.ordinal() == contractStatus) {
				return status;
			}
		}
		throw new IllegalArgumentException("잘못된 Contract Status가 입력되었습니다.");
	}
}

