package com.example.bunsanedthinking_springback.entity.depositDetail;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum DepositPath {
	Cash("현금"), CreditCard("카드"), AccountTransfer("계좌이체");

	private String text;

	private DepositPath(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}