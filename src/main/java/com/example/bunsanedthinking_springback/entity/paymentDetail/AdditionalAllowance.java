package com.example.bunsanedthinking_springback.entity.paymentDetail;

import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
@Data
@NoArgsConstructor
public class AdditionalAllowance extends PaymentDetail {

//	private int employeeID;
	private AdditionalAllowanceType type;

	public AdditionalAllowance(String accountHolder, String bank, String bankAccount, int money,
			PaymentType paymentType, int contractId) {
		super(accountHolder, bank, bankAccount, money, paymentType, contractId);
		// TODO Auto-generated constructor stub
	}
	public AdditionalAllowanceVO getVO() {
		return new AdditionalAllowanceVO(getId(), type.ordinal());
	}
}