package com.example.bunsanedthinking_springback.entity.revival;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.RevivalVO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public class Revival extends Contract {

	public Revival(Contract contract) {
		super(contract);
		this.applyDate = new Date();
		this.setOriginalContract(contract);
		this.revivalStatus = RevivalStatus.Unprocessed;
		// TODO Auto-generated constructor stub
	}

	private Date applyDate;
	private RevivalStatus revivalStatus;
	private Contract originalContract;

	public RevivalVO findRevivalVO() {
		LocalDateTime lApplyDate = new java.util.Date(applyDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return new RevivalVO(getId(), lApplyDate,
				originalContract.getId(),
				revivalStatus.ordinal());
	}

	public String getApplyDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.applyDate);
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public RevivalStatus getRevivalStatus() {
		return revivalStatus;
	}

	public void setRevivalStatus(RevivalStatus revivalStatus) {
		this.revivalStatus = revivalStatus;
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