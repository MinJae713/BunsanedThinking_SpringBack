package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        Image medical_certificate = null;
        try {
            medical_certificate = ImageIO.read(new File(this.medical_certificate));
        } catch (IOException e) {
            medical_certificate = null;
        }
		Image receipt = null;
		try {
			receipt = ImageIO.read(new File(this.receipt));
		} catch (IOException e) {
			receipt = null;
		}
		Image resident_registration_card = null;
		try {
			resident_registration_card = ImageIO.read(new File(this.resident_registration_card));
		} catch (IOException e) {
			resident_registration_card = null;
		}
        InsuranceMoney insuranceMoney = new InsuranceMoney(contract_id, bank_name, bank_account,
				medical_certificate, receipt, resident_registration_card);
		insuranceMoney.setId(id);
		insuranceMoney.setProcessStatus(InsuranceMoneyStatus.values()[process_status]);
		insuranceMoney.setApplyDate(Date.valueOf(apply_date));
		return insuranceMoney;
	}
}
