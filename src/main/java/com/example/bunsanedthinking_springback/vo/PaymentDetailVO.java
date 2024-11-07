package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;

import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailVO {
	private int id;
	private String account_holder;
	private String bank;
	private String bank_account;
	private int money;
	private int payment_type;
	private int process_status;
	private Integer contract_id;
	private Integer employee_id;

	public PaymentDetailVO(int id, String account_holder,
						   String bank, String bank_account,
						   int money, int payment_type,
						   int process_status, Integer contract_id) {
		this.id = id;
		this.account_holder = account_holder;
		this.bank = bank;
		this.bank_account = bank_account;
		this.money = money;
		this.payment_type = payment_type;
		this.process_status = process_status;
		this.contract_id = contract_id;
	}
	public PaymentDetail getEntity() {
		PaymentDetail paymentDetail = new PaymentDetail();
		paymentDetail.setId(id);
		paymentDetail.setProcessStatus(PaymentProcessStatus.values()[process_status]);
		paymentDetail.setAccountHolder(account_holder);
		paymentDetail.setBank(bank);
		paymentDetail.setBankAccount(bank_account);
		paymentDetail.setMoney(money);
		paymentDetail.setPaymentType(PaymentType.values()[payment_type]);
		paymentDetail.setContractId(contract_id);
		paymentDetail.setEmployeeId(employee_id);
		return paymentDetail;
	}

	public static PaymentDetailVO from(PaymentDetail paymentDetail) {
		return new PaymentDetailVO(paymentDetail.getId(), paymentDetail.getAccountHolder(), paymentDetail.getBank(),
			paymentDetail.getBankAccount(), paymentDetail.getMoney(), paymentDetail.getPaymentType().ordinal(),
			paymentDetail.getProcessStatus().ordinal(), paymentDetail.getContractId(), paymentDetail.getEmployeeId());
	}
}
