package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.ProductManagementDTOConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDiseaseInsuranceRequest {

	@NotBlank(message = ProductManagementDTOConstants.NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = ProductManagementDTOConstants.NAME_SIZE_MESSAGE)
	@Pattern(regexp = ProductManagementDTOConstants.NAME_PATTERN_REGEXP,
		message = ProductManagementDTOConstants.NAME_PATTERN_MESSAGE)
	private String name;

	@NotNull(message = ProductManagementDTOConstants.MAXIMUM_MONEY_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MAXIMUM_MONEY_MIN_MESSAGE)
	private Integer maximumMoney;

	@NotNull(message = ProductManagementDTOConstants.INSURANCE_TYPE_NOT_NULL_MESSAGE)
	private InsuranceType insuranceType;

	@NotNull(message = ProductManagementDTOConstants.AGE_RANGE_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.AGE_RANGE_MIN_MESSAGE)
	@Max(value = 120, message = ProductManagementDTOConstants.AGE_RANGE_MAX_MESSAGE)
	private Integer ageRange;

	@NotNull(message = ProductManagementDTOConstants.MONTHLY_PREMIUM_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MONTHLY_PREMIUM_MIN_MESSAGE)
	private Integer monthlyPremium;

	@NotNull(message = ProductManagementDTOConstants.CONTRACT_PERIOD_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.CONTRACT_PERIOD_MIN_MESSAGE)
	private Integer contractPeriod;

	@NotBlank(message = ProductManagementDTOConstants.COVERAGE_NOT_BLANK_MESSAGE)
	@Size(max = 120, message = ProductManagementDTOConstants.COVERAGE_SIZE_MESSAGE)
	private String coverage;

	@NotBlank(message = ProductManagementDTOConstants.DISEASE_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = ProductManagementDTOConstants.DISEASE_NAME_SIZE_MESSAGE)
	@Pattern(regexp = ProductManagementDTOConstants.DISEASE_NAME_PATTERN_REGEXP,
		message = ProductManagementDTOConstants.DISEASE_NAME_PATTERN_MESSAGE)
	private String diseaseName;

	@NotNull(message = ProductManagementDTOConstants.DISEASE_LIMIT_NOT_NULL)
	@Min(value = 1, message = ProductManagementDTOConstants.DISEASE_LIMIT_MIN_MESSAGE)
	private Integer diseaseLimit;

	@NotNull(message = ProductManagementDTOConstants.SURGERIES_LIMIT_NOT_NULL)
	@Min(value = 1, message = ProductManagementDTOConstants.SURGERIES_LIMIT_MIN_MESSAGE)
	private Integer surgeriesLimit;
}
