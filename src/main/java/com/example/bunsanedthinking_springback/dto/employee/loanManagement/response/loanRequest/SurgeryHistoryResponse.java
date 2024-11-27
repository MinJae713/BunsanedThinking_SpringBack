package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurgeryHistoryResponse {
	private String date;
	private String hospitalName;
	private int id;
	private String name;

	public static SurgeryHistoryResponse from(SurgeryHistory surgeryHistory) {
		return new SurgeryHistoryResponse(DateUtils.toString(surgeryHistory.getDate()),
			surgeryHistory.getHospitalName(), surgeryHistory.getId(), surgeryHistory.getName());
	}
}
