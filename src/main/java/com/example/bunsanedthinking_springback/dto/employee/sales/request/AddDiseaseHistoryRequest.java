package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.SalesDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDiseaseHistoryRequest {

	@NotBlank(message = SalesDTOConstants.DATE_OF_DIAGNOSIS_NOT_BLANK_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.DATE_OF_DIAGNOSIS_PATTERN_REGEXP,
		message = SalesDTOConstants.DATE_OF_DIAGNOSIS_PATTERN_MESSAGE)
	private String dateOfDiagnosis;

	@NotBlank(message = SalesDTOConstants.NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20,
		message = SalesDTOConstants.NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.NAME_PATTERN_MESSAGE)
	private String name;
}
