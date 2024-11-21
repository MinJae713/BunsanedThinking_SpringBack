package com.example.bunsanedthinking_springback.dto.employee.sales.response;

import java.util.Date;

import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date counselDate;
	private Gender gender;
	private CounselProcessStatus processStatus;
	private String job;
	private Integer age;

	public static HandleInsuranceConsultationDetailResponse from(Counsel counsel) {
		return HandleInsuranceConsultationDetailResponse.builder()
			.id(counsel.getId())
			.name(counsel.getName())
			.phoneNumber(counsel.getPhoneNumber())
			.counselDate(counsel.getCounselDate())
			.gender(counsel.getGender())
			.processStatus(counsel.getProcessStatus())
			.job(counsel.getJob())
			.age(counsel.getAge())
			.build();
	}
}