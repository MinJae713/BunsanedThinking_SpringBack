package com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionAccidentHistoryDetailResponse;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAcquisitionDiseaseHistoryDetailResponse {
	private Integer id;
	private String date_of_diagnosis;
	private String name;

	public static ReviewAcquisitionDiseaseHistoryDetailResponse from(DiseaseHistory diseaseHistory) {
		return ReviewAcquisitionDiseaseHistoryDetailResponse.builder()
			.id(diseaseHistory.getId())
			.date_of_diagnosis(DateUtils.toString(diseaseHistory.getDate_of_diagnosis()))
			.name(diseaseHistory.getName())
			.build();
	}


}
