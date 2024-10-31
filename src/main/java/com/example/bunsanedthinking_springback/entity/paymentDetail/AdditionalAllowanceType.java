package com.example.bunsanedthinking_springback.entity.paymentDetail;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum AdditionalAllowanceType {

	Overtime("초과수당"), Weekend("주말특근");

	private String name;

	AdditionalAllowanceType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}