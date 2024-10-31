package com.example.bunsanedthinking_springback.entity.recontract;

import com.example.bunsanedthinking_springback.entity.contract.Contract;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public class Recontract extends Contract {

	public Recontract(Contract contract) {
		super(contract);
		this.setOriginalContract(contract);
		// TODO Auto-generated constructor stub
	}

	private Date applyDate;
	private RecontractStatus recontractStatus;
	private Contract originalContract;

	public String getApplyDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.applyDate);
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public RecontractStatus getRecontractStatus() {
		return recontractStatus;
	}

	public void setRecontractStatus(RecontractStatus recontractStatus) {
		this.recontractStatus = recontractStatus;
	}

	public void apply(){

	}

	public void review(){

	}

	public Contract getOriginalContract() {
		return originalContract;
	}

	public void setOriginalContract(Contract originalContract) {
		this.originalContract = originalContract;
	}

}