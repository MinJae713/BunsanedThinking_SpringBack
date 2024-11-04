package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TerminationVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int termination_fee;
	private int origin_contract_id;
	private int termination_status;

	public Termination getTermination(Contract contract) {
		Termination result = new Termination(contract);
		int year = apply_date.getYear();
		int month = apply_date.getMonthValue();
		int day = apply_date.getDayOfMonth();
		result.setApplyDate(new Date(year, month, day));
		result.setTerminationFee(termination_fee);
		result.setTerminationStatus(TerminationStatus.values()[termination_status]);
		return result;
	}
}
