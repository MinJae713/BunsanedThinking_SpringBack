package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

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
	private int process_status;
	private LocalDate apply_date;

	public InsuranceMoney getEntity() {
        InsuranceMoney insuranceMoney = new InsuranceMoney(contract_id, bank_name, bank_account,
				medical_certificate, receipt, resident_registration_card);
		insuranceMoney.setId(id);
		insuranceMoney.setProcessStatus(InsuranceMoneyStatus.values()[process_status]);
		insuranceMoney.setApplyDate(Date.valueOf(apply_date));
		return insuranceMoney;
	}
}
