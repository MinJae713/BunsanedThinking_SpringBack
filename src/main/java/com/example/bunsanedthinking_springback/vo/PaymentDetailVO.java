package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class PaymentDetailVO {
	private int id;
	private String account_holder;
	private String bank;
	private String bank_account;
	private int money;
	private int payment_type;
	private int process_status;
	private int contract_id;
	private int employee_id;
}
