package com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionAccidentHistoryDetailResponse;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAcquisitionSurgeryHistoryDetailResponse {
	private Integer id;
	private String date;
	private String hospitalName;
	private String name;

	public static ReviewAcquisitionSurgeryHistoryDetailResponse from(SurgeryHistory surgeryHistory) {
		return ReviewAcquisitionSurgeryHistoryDetailResponse.builder()
			.id(surgeryHistory.getId())
			.date(DateUtils.toString(surgeryHistory.getDate()))
			.hospitalName(surgeryHistory.getHospitalName())
			.name(surgeryHistory.getName())
			.build();
	}

}
