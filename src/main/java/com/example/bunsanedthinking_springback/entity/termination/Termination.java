package com.example.bunsanedthinking_springback.entity.termination;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.TerminationVO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 4:40:44
 */
@Data
@NoArgsConstructor
public class Termination extends Contract {

	private Date applyDate;
	private int terminationFee;
	private TerminationStatus terminationStatus;
	private Contract originalContract;

	public Termination(Contract contract) {
		super(contract);
		this.applyDate = new Date();
		this.setOriginalContract(contract);
		this.terminationStatus = TerminationStatus.Unprocessed;
		// TODO Auto-generated constructor stub
	}

	public TerminationVO findTerminationVO() {
		LocalDateTime lApplyDate = new java.util.Date(applyDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return new TerminationVO(getId(), lApplyDate,
				terminationFee, originalContract.getId(),
				terminationStatus.ordinal());
	}
}