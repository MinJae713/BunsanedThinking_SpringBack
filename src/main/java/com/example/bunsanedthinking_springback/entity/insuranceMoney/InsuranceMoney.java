package com.example.bunsanedthinking_springback.entity.insuranceMoney;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
@NoArgsConstructor
@Data
public class InsuranceMoney {

	private String bankName;
	private String bankAccount;
	private int contractID;
	private int id;
	private Image medicalCertificate;
	private Image receipt;
	private Image residentRegistrationCard;
	private InsuranceMoneyStatus processStatus;
	private Date applyDate;

	public InsuranceMoney(int contractId, String bankName, String bankAccount, Image medicalCertificate, Image receipt, Image residentRegistrationCard){
		this.contractID = contractId;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.medicalCertificate = medicalCertificate;
		this.receipt = receipt;
		this.residentRegistrationCard = residentRegistrationCard;
		this.processStatus = InsuranceMoneyStatus.Unprocessed;
		this.applyDate = new Date();
	}
	
	private InsuranceMoney(InsuranceMoney insuranceMoney) {
		this.id = insuranceMoney.getId();
		this.contractID = insuranceMoney.getContractId();
		this.bankAccount = insuranceMoney.getBankAccount();
		this.bankName = insuranceMoney.getBankName();
		this.medicalCertificate = insuranceMoney.getMedicalCertificate();
		this.receipt = insuranceMoney.getReceipt();
		this.residentRegistrationCard = insuranceMoney.getResidentRegistrationCard();
		this.processStatus = insuranceMoney.getProcessStatus();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.applyDate = dateFormat.parse(insuranceMoney.getApplyDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InsuranceMoneyVO getVO() {
		LocalDate lApplyDate = applyDate.toInstant().
				atZone(ZoneId.systemDefault()).
				toLocalDate();
		return new InsuranceMoneyVO(id, bankAccount,
				bankName, residentRegistrationCard.toString(),
				receipt.toString(), medicalCertificate.toString(),
				contractID, processStatus.ordinal(),
				lApplyDate);
	}
	
	public InsuranceMoney clone() {
		return new InsuranceMoney(this);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public void handle(){
		this.processStatus = InsuranceMoneyStatus.Completed;
	}

	public int getContractId() {
		return this.contractID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public Image getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(Image medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	public Image getReceipt() {
		return receipt;
	}

	public void setReceipt(Image receipt) {
		this.receipt = receipt;
	}

	public Image getResidentRegistrationCard() {
		return residentRegistrationCard;
	}

	public void setResidentRegistrationCard(Image residentRegistrationCard) {
		this.residentRegistrationCard = residentRegistrationCard;
	}

	public InsuranceMoneyStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(InsuranceMoneyStatus processStatus) {
		this.processStatus = processStatus;
	}

	public String getApplyDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.applyDate);
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

}