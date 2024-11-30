package com.example.bunsanedthinking_springback.dto.employee.sales.response;

import java.util.Date;

import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HandleInsuranceConsultationDetailResponse {
	private Integer id;
	private String name;
	private String phoneNumber;
	private String counselDate;
	private String gender;
	private String processStatus;
	private String job;
	private Integer age;

	public static HandleInsuranceConsultationDetailResponse from(Counsel counsel) {
		return HandleInsuranceConsultationDetailResponse.builder()
			.id(counsel.getId())
			.name(counsel.getName())
			.phoneNumber(counsel.getPhoneNumber())
			.counselDate(DateUtils.toString(counsel.getCounselDate()))
			.gender(counsel.getGender().getName())
			.processStatus(counsel.getProcessStatus().getName())
			.job(counsel.getJob())
			.age(counsel.getAge())
			.build();
	}
}