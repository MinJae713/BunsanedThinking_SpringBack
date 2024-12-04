package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.SalesDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceSurgeryHistoryRequest {

	@NotBlank(message = SalesDTOConstants.INDUCE_SURGERY_DATE_NOT_BLANK_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_SURGERY_DATE_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_SURGERY_DATE_PATTERN_MESSAGE)
	private String date;

	@NotBlank(message = SalesDTOConstants.INDUCE_HOSPITAL_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_HOSPITAL_NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_HOSPITAL_NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_HOSPITAL_NAME_PATTERN_MESSAGE)
	private String hospitalName;

	@NotBlank(message = SalesDTOConstants.INDUCE_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_NAME_PATTERN_MESSAGE)
	private String name;
}
