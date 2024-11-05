package com.example.bunsanedthinking_springback.entity.depositDetail;

public enum DepositPath {

	Cash(0, "현금"),
	CreditCard(1, "카드"),
	AccountTransfer(2, "계좌이체");

	private final int value;
	private final String text;

	DepositPath(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getValue() {
		return value;
	}

	// 번호에 따라 DepositPath 반환
	public static DepositPath fromInt(int number) {
		for (DepositPath depositPath : DepositPath.values()) {
			if (depositPath.getValue() == number) {
				return depositPath;
			}
		}
		throw new IllegalArgumentException("Unknown DepositPath number: " + number);
	}

	// Enum ordinal에 따른 DepositPath 반환
	public static DepositPath indexOf(int index) {
		for (DepositPath depositPath : DepositPath.values()) {
			if (depositPath.ordinal() == index) {
				return depositPath;
			}
		}
		throw new IllegalArgumentException("잘못된 Deposit Path가 들어왔습니다.");
	}
}
