package com.example.bunsanedthinking_springback.entity.depositDetail;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
@NoArgsConstructor
@Data
public class DepositDetail {
	private int contractId;
	private Date date;
	private String depositorName;
	private int id;
	private int money;
	private DepositPath path;

	public DepositDetail(int id, String depositorName, int contractId, int money, DepositPath path) {
		this.id = id;
		this.contractId = contractId;
		this.date = new Date();
		this.depositorName = depositorName;
		this.money = money;
		this.path = path;
	}

	private DepositDetail(DepositDetail depositDetail) {
		this.contractId = depositDetail.getContractId();
		this.date = (Date)depositDetail.date.clone();
		this.depositorName = depositDetail.getDepositorName() + "";
		this.id = depositDetail.getId();
		this.money = depositDetail.getMoney();
		this.path = depositDetail.getPath();

	}

	public DepositDetailVO findVO() {
		LocalDate lDate = new java.util.Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new DepositDetailVO(id, depositorName,
			lDate, money, path.ordinal(), contractId);
	}

	public DepositDetail clone() {
		return new DepositDetail(this);
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.date);
	}
}
