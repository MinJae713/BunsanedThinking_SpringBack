package com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionAccidentHistoryDetailResponse;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAcquisitionAccidentHistoryDetailResponse {
	private Integer id;
	private String date;
	private String accidentDetail;

	public static ReviewAcquisitionAccidentHistoryDetailResponse from(AccidentHistory accidentHistory) {
		return ReviewAcquisitionAccidentHistoryDetailResponse.builder()
			.id(accidentHistory.getId())
			.date(DateUtils.toString(accidentHistory.getDate()))
			.accidentDetail(accidentHistory.getAccidentDetail())
			.build();
	}

}
