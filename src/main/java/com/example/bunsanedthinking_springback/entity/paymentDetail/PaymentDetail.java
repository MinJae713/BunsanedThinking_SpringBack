package com.example.bunsanedthinking_springback.entity.paymentDetail;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public class PaymentDetail {

	public static final int PAYMENT_DETAIL_SERIAL_NUMBER = 900;
	private int id;
	private PaymentProcessStatus processStatus;
	private String accountHolder;
	private String bank;
	private String bankAccount;
	private int money;
	private PaymentType paymentType;
	private int contractId;

	public PaymentDetail(String accountHolder, String bank, String bankAccount, int money,
			PaymentType paymentType, int contractId) {
		super();
		this.accountHolder = accountHolder;
		this.bank = bank;
		this.bankAccount = bankAccount;
		this.money = money;
		this.paymentType = paymentType;
		this.processStatus = PaymentProcessStatus.Unprocessed;
		this.contractId = contractId;
	}
	
	private PaymentDetail(PaymentDetail paymentDetail) {
		this.accountHolder = paymentDetail.getAccountHolder() + "";
		this.bank = paymentDetail.getBank() + "";
		this.bankAccount = paymentDetail.getBankAccount() + "";
		this.money = paymentDetail.getMoney();
		this.paymentType = paymentDetail.getPaymentType();
		this.processStatus = paymentDetail.getProcessStatus();
		this.contractId = paymentDetail.getContractId();
		this.id = paymentDetail.getId();
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

	public int getContractId() {
		return contractId;
	}
	
}