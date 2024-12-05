package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.global.constants.service.employee.productManagement.ProductManagementDTOConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiseaseInsuranceRequest {

	@NotNull(message = ProductManagementDTOConstants.AGE_RANGE_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.AGE_RANGE_MIN_MESSAGE)
	@Max(value = 120, message = ProductManagementDTOConstants.AGE_RANGE_MAX_MESSAGE)
	private Integer ageRange;

	@NotNull(message = ProductManagementDTOConstants.CONTRACT_PERIOD_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.CONTRACT_PERIOD_MIN_MESSAGE)
	private Integer contractPeriod;

	@NotNull(message = ProductManagementDTOConstants.COVERAGE_NOT_BLANK_MESSAGE)
	@Size(max = 120, message = ProductManagementDTOConstants.COVERAGE_SIZE_MESSAGE)
	private String coverage;

	@NotNull(message = ProductManagementDTOConstants.DISEASE_LIMIT_NOT_NULL)
	@Min(value = 1, message = ProductManagementDTOConstants.DISEASE_LIMIT_MIN_MESSAGE)
	private Integer diseaseLimit;

	@NotNull(message = ProductManagementDTOConstants.DISEASE_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = ProductManagementDTOConstants.DISEASE_NAME_SIZE_MESSAGE)
	@Pattern(regexp = ProductManagementDTOConstants.DISEASE_NAME_PATTERN_REGEXP,
		message = ProductManagementDTOConstants.DISEASE_NAME_PATTERN_MESSAGE)
	private String diseaseName;

	@NotNull(message = ProductManagementDTOConstants.ID_NOT_NULL)
	private Integer id;

	@NotNull(message = ProductManagementDTOConstants.INSURANCE_TYPE_NOT_NULL_MESSAGE)
	private InsuranceType insuranceType;

	@NotNull(message = ProductManagementDTOConstants.NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = ProductManagementDTOConstants.NAME_SIZE_MESSAGE)
	@Pattern(regexp = ProductManagementDTOConstants.NAME_PATTERN_REGEXP,
		message = ProductManagementDTOConstants.NAME_PATTERN_MESSAGE)
	private String name;

	@NotNull(message = ProductManagementDTOConstants.MONTHLY_PREMIUM_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MONTHLY_PREMIUM_MIN_MESSAGE)
	private Integer monthlyPremium;

	@NotNull(message = ProductManagementDTOConstants.SURGERIES_LIMIT_NOT_NULL)
	@Min(value = 1, message = ProductManagementDTOConstants.SURGERIES_LIMIT_MIN_MESSAGE)
	private Integer surgeriesLimit;

	@NotNull(message = ProductManagementDTOConstants.MAXIMUM_MONEY_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MAXIMUM_MONEY_MIN_MESSAGE)
	private Integer maximumMoney;

	public Disease toEntity() {
		Disease disease = new Disease(insuranceType, name, maximumMoney, ageRange, coverage, monthlyPremium,
			contractPeriod, diseaseName, diseaseLimit, surgeriesLimit);
		disease.setId(id);
		return disease;
	}
}
