package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class InsuranceMoneyVO {
	private int id;
	private String bank_account;
	private String resident_registration_card;
	private String receipt;
	private String medical_certificate;
	private int contract_id;
}
