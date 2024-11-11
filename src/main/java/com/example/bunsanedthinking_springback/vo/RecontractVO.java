package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecontractVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int origin_contract_id;
	private int recontract_status;

	public Recontract getEntity(Contract contract) {
		Recontract result = new Recontract(contract);
		result.setApplyDate(Date.from(apply_date.atZone(ZoneId.systemDefault()).toInstant()));
		result.setRecontractStatus(RecontractStatus.values()[recontract_status]);
		return result;
	}
}
