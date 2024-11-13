package com.example.bunsanedthinking_springback.entity.paymentDetail;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetail {

	public static final int PAYMENT_DETAIL_SERIAL_NUMBER = 900;
	private int id;
	private PaymentProcessStatus processStatus;
	private String accountHolder;
	private String bank;
	private String bankAccount;
	private int money;
	private PaymentType paymentType;
	private Integer contractId;
	private Integer employeeId;

	public PaymentDetail(String accountHolder, String bank, String bankAccount, int money,
		PaymentType paymentType, Integer contractId, Integer employeeId) {
		super();
		this.accountHolder = accountHolder;
		this.bank = bank;
		this.bankAccount = bankAccount;
		this.money = money;
		this.paymentType = paymentType;
		this.processStatus = PaymentProcessStatus.Unprocessed;
		this.contractId = contractId;
		this.employeeId = employeeId;
	}

	private PaymentDetail(PaymentDetail paymentDetail) {
		this.accountHolder = paymentDetail.getAccountHolder() + "";
		this.bank = paymentDetail.getBank() + "";
		this.bankAccount = paymentDetail.getBankAccount() + "";
		this.money = paymentDetail.getMoney();
		this.paymentType = paymentDetail.getPaymentType();
		this.processStatus = paymentDetail.getProcessStatus();
		this.contractId = paymentDetail.getContractId();
		this.employeeId = paymentDetail.getEmployeeId();
		this.id = paymentDetail.getId();
	}

	public PaymentDetail(String name, String bankName, String bankAccount,
		int totalMoney, PaymentType paymentType, int id) {
		this.accountHolder = name;
		this.bank = bankName;
		this.bankAccount = bankAccount;
		this.money = totalMoney;
		this.paymentType = paymentType;
		this.processStatus = PaymentProcessStatus.Unprocessed;
		this.contractId = id;
	}

	public PaymentDetailVO findPaymentDetailVO() {
		return new PaymentDetailVO(id, accountHolder, bank,
			bankAccount, money, paymentType.ordinal(),
			processStatus.ordinal(),
			contractId, employeeId);
	}

	public PaymentDetail clone() {
		return new PaymentDetail(this);
	}
}
