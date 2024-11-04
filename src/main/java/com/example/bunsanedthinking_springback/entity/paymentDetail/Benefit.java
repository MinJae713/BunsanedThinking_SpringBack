package com.example.bunsanedthinking_springback.entity.paymentDetail;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public class Benefit extends PaymentDetail {

//	private int employeeID;
//	private Benefit type;
	// 일단 안써서 주석처리

	public Benefit(String accountHolder, String bank, String bankAccount, int money, PaymentType paymentType,
			int contractId) {
		super(accountHolder, bank, bankAccount, money, paymentType, contractId, 0);
	}
}
