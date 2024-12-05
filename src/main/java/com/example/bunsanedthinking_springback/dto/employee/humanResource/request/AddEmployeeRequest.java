package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.employee.humanResource.HumanResourceDTOConstants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddEmployeeRequest {
	@Positive(message = HumanResourceDTOConstants.DEPARMENT_ID_POSITIVE_MESSAGE)
	private int departmentId;

	@Pattern(regexp = CommonConstants.NAME_PATTERN_REGEXP, message = CommonConstants.NAME_PATTERN_MESSAGE)
	@Size(max = 20, message = HumanResourceDTOConstants.NAME_SIZE_MESSAGE)
	private String name;

	@NotNull(message = HumanResourceDTOConstants.EMPLOYEE_POSITION_NOT_NULL_MESSAGE)
	private EmployeePosition employeePosition;

	@NotBlank(message = HumanResourceDTOConstants.ADDRESS_NOT_BLANK_MESSAGE)
	@Size(max = 50, message = HumanResourceDTOConstants.ADDRESS_SIZE_MESSAGE)
	private String address;

	@Pattern(regexp = CommonConstants.PHONE_NUMBER_PATTERN_REGEXP, message = CommonConstants.PHONE_NUMBER_PATTERN_MESSAGE)
	private String phoneNumber;

	@NotBlank(message = HumanResourceDTOConstants.BANK_NAME_NOT_BLANK_MESSAGE)
	@Size(max = 10, message = HumanResourceDTOConstants.BANK_NAME_SIZE_MESSAGE)
	private String bankName;

	@NotBlank(message = HumanResourceDTOConstants.BANK_ACCOUNT_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = HumanResourceDTOConstants.BANK_ACCOUNT_SIZE_MESSAGE)
	private String bankAccount;

	@Pattern(regexp = HumanResourceDTOConstants.RESIDENT_REGISTRATION_NUMBER_PATTERN_REGEXP, message = HumanResourceDTOConstants.RESIDENT_REGISTRATION_NUMBER_PATTERN_MESSAGE)
	private String residentRegistrationNumber;

	@Positive(message = HumanResourceDTOConstants.SALARY_POSITIVE_MESSAGE)
	private int salary;

	@Pattern(regexp = HumanResourceDTOConstants.EMPLOYMENT_DATE_PATTERN_REGEXP, message = HumanResourceDTOConstants.EMPLOYMENT_DATE_PATTERN_MESSAGE)
	private String employmentDate;

	private List<CreateFamilyListRequest> tempFamilyList;
}
