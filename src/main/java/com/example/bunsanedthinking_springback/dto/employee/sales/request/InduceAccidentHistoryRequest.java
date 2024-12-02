package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceAccidentHistoryRequest {

	@NotBlank(message = "날짜는 필수 값입니다.")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
		message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
	private String date;

	@NotBlank(message = "사고 세부사항은 필수 값입니다.")
	@Size(max = 120,
		message = "사고 세부사항은 최대 120자까지 허용됩니다.")
	private String accidentDetail;
}
