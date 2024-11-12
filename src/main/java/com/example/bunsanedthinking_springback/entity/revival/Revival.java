package com.example.bunsanedthinking_springback.entity.revival;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.RevivalVO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.Data;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
@Data
public class Revival extends Contract {

	private Date applyDate;
	private RevivalStatus revivalStatus;
	private Contract originalContract;

	public Revival(Contract contract) {
		super(contract);
		this.applyDate = new Date();
		this.setOriginalContract(contract);
		this.revivalStatus = RevivalStatus.Unprocessed;
		// TODO Auto-generated constructor stub
	}

	public RevivalVO findRevivalVO() {
		LocalDateTime lApplyDate = new java.util.Date(applyDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return new RevivalVO(getId(), lApplyDate,
				originalContract.getId(),
				revivalStatus.ordinal());
	}

}