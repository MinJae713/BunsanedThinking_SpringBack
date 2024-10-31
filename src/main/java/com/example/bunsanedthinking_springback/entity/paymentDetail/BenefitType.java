package com.example.bunsanedthinking_springback.entity.paymentDetail;


/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum BenefitType {
	GiftMoney("경조사비"),
	Scholarship("장학금");
	
	private String name;
	
	BenefitType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}