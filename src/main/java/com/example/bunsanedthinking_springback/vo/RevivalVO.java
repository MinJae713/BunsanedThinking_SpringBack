package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.revival.RevivalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevivalVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int origin_contract_id;
	private int revival_status;

	public Revival getEntity(Contract contract) {
		Revival result = new Revival(contract);
		result.setApplyDate(Date.from(apply_date.atZone(ZoneId.systemDefault()).toInstant()));
		result.setRevivalStatus(RevivalStatus.values()[revival_status]);
		return result;
	}
}
