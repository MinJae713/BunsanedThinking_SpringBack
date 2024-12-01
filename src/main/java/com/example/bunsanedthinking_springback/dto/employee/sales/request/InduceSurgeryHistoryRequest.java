package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceSurgeryHistoryRequest {

	@NotBlank(message = "날짜는 필수 입력 값입니다.")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
		message = "수술 날짜는 yyyy-MM-dd 형식이어야 합니다.")
	private String date;

	@NotBlank(message = "병원 이름은 필수 입력 값입니다.")
	@Size(max = 20, message = "병원 이름은 최대 20자 이내로 작성해야 합니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "병원 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String hospitalName;

	@NotBlank(message = "수술 이름은 필수 입력 값입니다.")
	@Size(max = 20, message = "수술 이름은 최대 20자 이내로 작성해야 합니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "수술 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;
}
