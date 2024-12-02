package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDiseaseHistoryRequest {

	@NotBlank
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
		message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
	private String dateOfDiagnosis;

	@NotBlank
	@Size(max = 20,
		message = "질병 이름은 최대 20자까지 허용됩니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "질병 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;
}
