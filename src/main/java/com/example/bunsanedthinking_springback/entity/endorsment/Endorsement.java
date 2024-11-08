package com.example.bunsanedthinking_springback.entity.endorsment;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.EndorsementVO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public class Endorsement extends Contract {

	public Endorsement(Contract contract) {
		super(contract);
		// TODO Auto-generated constructor stub
	}

	private Date applyDate;
	private EndorsementStatus endorsementStatus;

	// 이건 좀 생각좀 해보자
	public EndorsementVO getEndorsementVO() {
		LocalDate lApplyDate = applyDate.toInstant().
				atZone(ZoneId.systemDefault()).
				toLocalDate();
		return new EndorsementVO(getId(), lApplyDate,
				endorsementStatus.ordinal());
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public EndorsementStatus getEndorsementStatus() {
		return endorsementStatus;
	}

	public void setEndorsementStatus(EndorsementStatus endorsementStatus) {
		this.endorsementStatus = endorsementStatus;
	}

	public void apply(){

	}

	public void review(){

	}

}