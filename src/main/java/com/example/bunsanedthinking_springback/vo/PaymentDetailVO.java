package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;

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
}
