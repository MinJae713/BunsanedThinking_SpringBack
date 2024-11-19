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
public class GetAllCounselResponse {
	private String name;
	private String phoneNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date counselDate;
	private Gender gender;
	private Integer id;
	private CounselProcessStatus processStatus;

	public static GetAllCounselResponse of(Counsel counsel) {
		return GetAllCounselResponse.builder()
			.name(counsel.getName())
			.phoneNumber(counsel.getPhoneNumber())
			.counselDate(counsel.getCounselDate())
			.gender(counsel.getGender())
			.id(counsel.getId())
			.processStatus(counsel.getProcessStatus())
			.build();
	}
}
