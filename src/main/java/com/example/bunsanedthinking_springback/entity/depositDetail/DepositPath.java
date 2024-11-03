package com.example.bunsanedthinking_springback.entity.depositDetail;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum DepositPath {
	Cash("현금"),
	CreditCard("카드"),
	AccountTransfer("계좌이체");

	private String text;

	DepositPath(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// 번호에 따라 DepositPath를 반환하는 메서드
	public static DepositPath fromInt(int number) {
		switch (number) {
			case 1:
				return Cash;
			case 2:
				return CreditCard;
			case 3:
				return AccountTransfer;
			default:
				throw new IllegalArgumentException("Unknown DepositPath number: " + number);
		}
	}

	// DepositPath에 대한 번호를 반환하는 메서드
	public int getValue() {
		switch (this) {
			case Cash:
				return 1;
			case CreditCard:
				return 2;
			case AccountTransfer:
				return 3;
			default:
				throw new IllegalArgumentException("Unknown DepositPath: " + this);
		}
	}
}