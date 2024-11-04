package com.example.bunsanedthinking_springback.entity.paymentDetail;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public enum PaymentType {

	Cash("현금"), AccountTransfer("계좌이체");

	private String name;

	PaymentType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static PaymentType indexOf(int index) {
		for (PaymentType paymentType : PaymentType.values()) {
			if (paymentType.ordinal() == index) {
				return paymentType;
			}
		}
		throw new IllegalArgumentException("잘못된 Payment Type이 입력되었습니다.");
	}
}
