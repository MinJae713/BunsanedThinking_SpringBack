package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminationVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int termination_fee;
	private int origin_contract_id;
	private int termination_status;

	public Termination getEntity(Contract contract) {
		Termination result = new Termination(contract);
		// 위 생성자에서 contract, originCOntract 필드값 지정
		result.setApplyDate(Date.from(apply_date.atZone(ZoneId.systemDefault()).toInstant()));
		result.setTerminationFee(termination_fee);
		result.setTerminationStatus(TerminationStatus.values()[termination_status]);
		return result;
	}
}
