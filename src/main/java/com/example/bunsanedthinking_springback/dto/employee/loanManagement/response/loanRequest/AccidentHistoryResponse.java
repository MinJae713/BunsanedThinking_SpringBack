package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccidentHistoryResponse {
	private String date;
	private String accidentDetail;
	private int id;

	public static AccidentHistoryResponse from(AccidentHistory accidentHistory) {
		return new AccidentHistoryResponse(DateUtils.toString(accidentHistory.getDate()),
			accidentHistory.getAccidentDetail(), accidentHistory.getId());
	}
}
