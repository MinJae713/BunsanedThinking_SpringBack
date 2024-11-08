package com.example.bunsanedthinking_springback.entity.paymentDetail;

import com.example.bunsanedthinking_springback.vo.BenefitVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
@Data
@NoArgsConstructor
public class Benefit extends PaymentDetail {

//	private int employeeID;
	private BenefitType type;
	// BenefitType으로 수정

	public Benefit(String accountHolder, String bank, String bankAccount, int money, PaymentType paymentType,
			int contractId) {
		super(accountHolder, bank, bankAccount, money, paymentType, contractId);
	}
	public BenefitVO findVO() {
		return new BenefitVO(getId(), type.ordinal());
	}
}