package com.example.bunsanedthinking_springback.entity.endorsment;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.EndorsementVO;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
@Data
public class Endorsement extends Contract {

	public Endorsement(Contract contract) {
		super(contract);
		applyDate = new Date();
		endorsementStatus = EndorsementStatus.Unprocessed;
	}

	private Date applyDate;
	private EndorsementStatus endorsementStatus;

	// 이건 좀 생각좀 해보자
	public EndorsementVO findEndorsementVO() {
		LocalDate lApplyDate = new java.util.Date(applyDate.getTime()).toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		return new EndorsementVO(getId(), lApplyDate,
			endorsementStatus.ordinal());
	}
}
