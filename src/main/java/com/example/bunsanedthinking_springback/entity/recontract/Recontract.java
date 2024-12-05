package com.example.bunsanedthinking_springback.entity.recontract;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.RecontractVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@Data
public class Recontract extends Contract {

	private Date applyDate;
	private RecontractStatus recontractStatus;
	private Contract originalContract;

	public Recontract(Contract contract) {
		super(contract);
		this.setOriginalContract(contract);
		applyDate = new Date();
		recontractStatus = RecontractStatus.Unprocessed;
	}

	public RecontractVO findRecontractVO() {
		LocalDateTime lApplyDate = new java.util.Date(applyDate.getTime()).toInstant().
				atZone(ZoneId.systemDefault()).
				toLocalDateTime();
		return new RecontractVO(getId(), lApplyDate,
				originalContract.getId(),
				recontractStatus.ordinal());
	}

}