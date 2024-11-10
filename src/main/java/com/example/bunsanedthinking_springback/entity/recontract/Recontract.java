package com.example.bunsanedthinking_springback.entity.recontract;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.vo.RecontractVO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
		// 이거 다른 필드값도 지정하나유
		// TODO Auto-generated constructor stub
	}

	private Date applyDate;
	private RecontractStatus recontractStatus;
	private Contract originalContract;

	public RecontractVO findRecontractVO() {
		LocalDateTime lApplyDate = applyDate.toInstant().
				atZone(ZoneId.systemDefault()).
				toLocalDateTime();
		return new RecontractVO(getId(), lApplyDate,
				originalContract.getId(),
				recontractStatus.ordinal());
	}

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