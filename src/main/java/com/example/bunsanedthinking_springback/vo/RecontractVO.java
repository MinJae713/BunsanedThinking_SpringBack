package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecontractVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int origin_contract_id;
	private int recontract_status;

	public Recontract getRecontract(Contract contract) {
		Recontract result = new Recontract(contract);
		int year = apply_date.getYear();
		int month = apply_date.getMonthValue();
		int day = apply_date.getDayOfMonth();
		result.setApplyDate(new Date(year, month, day));
		result.setRecontractStatus(RecontractStatus.values()[recontract_status]);
		return result;
	}
}
