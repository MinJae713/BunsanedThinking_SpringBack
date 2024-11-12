package com.example.bunsanedthinking_springback.entity.compensationDetail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompensationDetail {

	private int contractID;
	private int id;
	private int money;
	private Date paymentDate;

	public CompensationDetail(int contractId, int money) {
		this.contractID = contractId;
		this.money = money;
		this.paymentDate = new Date();
	}

	public CompensationDetail(CompensationDetail compensationDetail) {
		this.id = compensationDetail.getId();
		this.contractID = compensationDetail.getContractId();
		this.money = compensationDetail.getMoney();
		this.paymentDate = (Date)compensationDetail.getPaymentDate().clone();
	}

	public CompensationDetailVO findVO() {
		LocalDate localDate = new java.util.Date(paymentDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new CompensationDetailVO(id,
			money, localDate, contractID);
	}

	public CompensationDetail clone() {
		return new CompensationDetail(this);
	}

	public int getContractId() {
		return this.contractID;
	}
}
