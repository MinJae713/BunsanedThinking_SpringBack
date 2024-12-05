package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.global.constants.service.employee.sales.SalesDTOConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InduceInsuranceProductRequest {

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_INSURANCE_NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_INSURANCE_NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_INSURANCE_NAME_PATTERN_MESSAGE)
	private String name;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_ADDRESS_NOT_BLANK_MESSAGE)
	@Size(max = 50, message = SalesDTOConstants.INDUCE_INSURANCE_ADDRESS_SIZE_MESSAGE)
	private String address;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_BANK_ACCOUNT_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_INSURANCE_BANK_ACCOUNT_SIZE_MESSAGE)
	private String bankAccount;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_BANK_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 10, message = SalesDTOConstants.INDUCE_INSURANCE_BANK_NAME_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_INSURANCE_BANK_NAME_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_INSURANCE_BANK_NAME_PATTERN_MESSAGE)
	private String bankName;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_PHONE_NUMBER_NOT_BLANK_MESSAGE)
	@Size(min = 13, max = 13, message = SalesDTOConstants.INDUCE_INSURANCE_PHONE_NUMBER_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_INSURANCE_PHONE_NUMBER_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_INSURANCE_PHONE_NUMBER_PATTERN_MESSAGE)
	private String phoneNumber;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_JOB_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = SalesDTOConstants.INDUCE_INSURANCE_JOB_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_INSURANCE_JOB_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_INSURANCE_JOB_PATTERN_MESSAGE)
	private String job;

	@NotNull(message = SalesDTOConstants.INDUCE_INSURANCE_PROPERTY_NOT_NULL_MESSAGE)
	@Min(value = 1, message = SalesDTOConstants.INDUCE_INSURANCE_PROPERTY_MIN_MESSAGE)
	private Long property;

	@NotBlank(message = SalesDTOConstants.INDUCE_INSURANCE_RESIDENT_REGISTRATION_NUMBER_NOT_BLANK_MESSAGE)
	@Size(min = 14, max = 14, message = SalesDTOConstants.INDUCE_INSURANCE_RESIDENT_REGISTRATION_NUMBER_SIZE_MESSAGE)
	@Pattern(regexp = SalesDTOConstants.INDUCE_INSURANCE_RESIDENT_REGISTRATION_NUMBER_PATTERN_REGEXP,
		message = SalesDTOConstants.INDUCE_INSURANCE_RESIDENT_REGISTRATION_NUMBER_PATTERN_MESSAGE)
	private String residentRegistrationNumber;

	@NotNull(message = SalesDTOConstants.INDUCE_INSURANCE_AGE_NOT_NULL_MESSAGE)
	@Min(value = 1, message = SalesDTOConstants.INDUCE_INSURANCE_AGE_MIN_MESSAGE)
	@Max(value = 120, message = SalesDTOConstants.INDUCE_INSURANCE_AGE_MAX_MESSAGE)
	private Integer age;

	@NotNull(message = SalesDTOConstants.INDUCE_INSURANCE_GENDER_NOT_NULL_MESSAGE)
	private Gender gender;

	@Valid
	private List<InduceDiseaseHistoryRequest> diseaseHistories;

	@Valid
	private List<InduceSurgeryHistoryRequest> surgeryHistories;

	@Valid
	private List<InduceAccidentHistoryRequest> accidentHistories;

	@NotNull(message = SalesDTOConstants.INDUCE_INSURANCE_PRODUCT_ID_NOT_NULL_MESSAGE)
	private Integer productId;

	@NotNull(message = SalesDTOConstants.INDUCE_INSURANCE_EMPLOYEE_ID_NOT_NULL_MESSAGE)
	private Integer employeeId;
}
