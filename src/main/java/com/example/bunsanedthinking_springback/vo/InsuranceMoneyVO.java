package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceMoneyVO {
	private int id;
	private String bank_account;
	private String bank_name;
	private String resident_registration_card;
	private String receipt;
	private String medical_certificate;
	private int contract_id;
	private LocalDate apply_date;
	private int process_status;
}
