package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.ProductManagementDTOConstants;
import jakarta.validation.Valid;
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
public class AddAutomobileInsuranceRequest {

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

	@NotNull(message = ProductManagementDTOConstants.VEHICLE_TYPE_NOT_NULL_MESSAGE)
	private VehicleType vehicleType;

	@NotNull(message = ProductManagementDTOConstants.ACCIDENT_LIMIT_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.ACCIDENT_LIMIT_MIN_MESSAGE)
	private Integer accidentLimit;

	private List<ServiceType> serviceTypes;
}
