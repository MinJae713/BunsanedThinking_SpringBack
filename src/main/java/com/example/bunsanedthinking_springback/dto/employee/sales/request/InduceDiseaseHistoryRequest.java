package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.sales.SalesDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceDiseaseHistoryRequest {

	@NotBlank(message = SalesDTOConstants.INDUCE_DISEASE_DATE_OF_DIAGNOSIS_NOT_BLANK_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_DISEASE_DATE_OF_DIAGNOSIS_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_DISEASE_DATE_OF_DIAGNOSIS_PATTERN_MESSAGE)
	private String dateOfDiagnosis;

	@NotBlank(message = SalesDTOConstants.INDUCE_DISEASE_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_DISEASE_NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_DISEASE_NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_DISEASE_NAME_PATTERN_MESSAGE)
	private String name;
}
