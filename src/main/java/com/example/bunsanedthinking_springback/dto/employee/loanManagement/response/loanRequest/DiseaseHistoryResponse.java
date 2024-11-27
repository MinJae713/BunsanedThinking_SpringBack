package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiseaseHistoryResponse {
	private String date_of_diagnosis;
	private int id;
	private String name;

	public static DiseaseHistoryResponse from(DiseaseHistory diseaseHistory) {
		return new DiseaseHistoryResponse(DateUtils.toString(diseaseHistory.getDate_of_diagnosis()),
			diseaseHistory.getId(), diseaseHistory.getName());
	}
}
