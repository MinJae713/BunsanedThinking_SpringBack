package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.ProductManagementDTOConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAutomobileInsuranceRequest {

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

	@NotNull(message = ProductManagementDTOConstants.ID_NOT_NULL)
	private Integer id;

	@NotNull(message = ProductManagementDTOConstants.NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = ProductManagementDTOConstants.NAME_SIZE_MESSAGE)
	@Pattern(regexp = ProductManagementDTOConstants.NAME_PATTERN_REGEXP,
		message = ProductManagementDTOConstants.NAME_PATTERN_MESSAGE)
	private String name;

	@NotNull(message = ProductManagementDTOConstants.INSURANCE_TYPE_NOT_NULL_MESSAGE)
	private InsuranceType insuranceType;

	@NotNull(message = ProductManagementDTOConstants.MONTHLY_PREMIUM_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MONTHLY_PREMIUM_MIN_MESSAGE)
	private Integer monthlyPremium;

	private ArrayList<ServiceType> serviceTypes;

	@NotNull(message = ProductManagementDTOConstants.VEHICLE_TYPE_NOT_NULL_MESSAGE)
	private VehicleType vehicleType;

	@NotNull(message = ProductManagementDTOConstants.MAXIMUM_MONEY_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.MAXIMUM_MONEY_MIN_MESSAGE)
	private Integer maximumMoney;

	@NotNull(message = ProductManagementDTOConstants.ACCIDENT_LIMIT_NOT_NULL_MESSAGE)
	@Min(value = 1, message = ProductManagementDTOConstants.ACCIDENT_LIMIT_MIN_MESSAGE)
	private Integer accidentLimit;

	public Automobile toEntity() {
		Automobile automobile = new Automobile(insuranceType, name, maximumMoney, ageRange, coverage, monthlyPremium,
			contractPeriod, accidentLimit, vehicleType, serviceTypes);
		automobile.setId(id);
		return automobile;
	}
}
