package com.example.bunsanedthinking_springback.entity.paymentDetail;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@NoArgsConstructor
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

	public PaymentDetailVO getPaymentDetailVO() {
		return new PaymentDetailVO(id, accountHolder, bank,
				bankAccount, money, paymentType.ordinal(),
				processStatus.ordinal(),
				contractId, employeeId);
	}

	public PaymentDetail clone() {
		return new PaymentDetail(this);
	}

	public void handle(){
		this.processStatus = PaymentProcessStatus.Completed;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	public PaymentProcessStatus getProcessStatus() {
		return processStatus;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public String getBank() {
		return bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public int getMoney() {
		return money;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public Integer getContractId() {
		return contractId;
	}

	public Integer getEmployeeId() {return employeeId;}

	public void setProcessStatus(PaymentProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
}
