package com.example.bunsanedthinking_springback.entity.termination;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 4:40:44
 */
@NoArgsConstructor
public class Termination extends Contract {

	public Termination(Contract contract) {
		super(contract);
		this.applyDate = new Date();
		this.setOriginalContract(contract);
		this.terminationStatus = TerminationStatus.Unprocessed;
		// TODO Auto-generated constructor stub
	}

	private Date applyDate;
	private int terminationFee;
	private TerminationStatus terminationStatus;
	private Contract originalContract;

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public int getTerminationFee() {
		return terminationFee;
	}

	public void setTerminationFee(int terminationFee) {
		this.terminationFee = terminationFee;
	}

	public TerminationStatus getTerminationStatus() {
		return terminationStatus;
	}

	public void setTerminationStatus(TerminationStatus terminationStatus) {
		this.terminationStatus = terminationStatus;
	}

	public void apply() {

	}

	public void requestTerminationFee() {

	}

	public Contract getOriginalContract() {
		return originalContract;
	}

	public void setOriginalContract(Contract originalContract) {
		this.originalContract = originalContract;
	}

}