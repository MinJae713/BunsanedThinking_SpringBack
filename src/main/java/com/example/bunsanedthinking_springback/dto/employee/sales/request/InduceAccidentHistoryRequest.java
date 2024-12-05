package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.sales.SalesDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceAccidentHistoryRequest {

	@NotBlank(message = SalesDTOConstants.DATE_NOT_BLANK_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.DATE_PATTERN_REGEXP,
		message = SalesDTOConstants.DATE_PATTERN_MESSAGE)
	private String date;

	@NotBlank(message = SalesDTOConstants.ACCIDENT_DETAIL_NOT_BLANK_MESSAGE)
	@Size(max = 120,
		message = SalesDTOConstants.ACCIDENT_DETAIL_SIZE_MESSAGE)
	private String accidentDetail;
}
