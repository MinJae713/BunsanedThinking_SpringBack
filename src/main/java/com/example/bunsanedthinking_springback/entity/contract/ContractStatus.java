package com.example.bunsanedthinking_springback.entity.contract;


/**
 * , , ,
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum ContractStatus {
	Terminating("계약 해지"), //해지된 계약
	Maturing("계약 만기"), //만기된 계약
	Maintaining("계약"), //계약 유지중
	CompensationApplying("보상 처리중"), //보상 요청중
	ContractRequesting("가입 심사중"), //보험 가입(계약) 요청
	RevivalRequesting("부활 심사중"), //부활 요청
	RecontractRequesting("만기 재가입 심사중"), //만기 재가입 요청
	TerminationRequesting("해지 처리중"), //해지 요청
	EndorsementRequesting("배서 심사중"); //배서 요청
	
	private String text;
	private ContractStatus(String text) {
		this.setText(text);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
