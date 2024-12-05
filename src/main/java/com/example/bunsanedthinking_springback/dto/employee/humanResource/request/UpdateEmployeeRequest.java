package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.employee.humanResource.HumanResourceDTOConstants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UpdateEmployeeRequest {
	@Positive(message = CommonConstants.ID_POSITIVE_MESSAGE)
	private int id;

	@Positive(message = CommonConstants.DEPARTMENT_ID_POSITIVE_MESSAGE)
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

	@NotBlank(message = HumanResourceDTOConstants.BANK_ACCOUNT_NOT_BLANK_MESSAGE)
	@Size(max = 10, message = HumanResourceDTOConstants.BANK_NAME_SIZE_MESSAGE)
	private String bankName;

	@NotBlank(message = HumanResourceDTOConstants.BANK_ACCOUNT_NOT_BLANK_MESSAGE)
	@Size(max = 20, message = HumanResourceDTOConstants.BANK_ACCOUNT_SIZE_MESSAGE)
	private String bankAccount;

	@Positive(message = HumanResourceDTOConstants.SALARY_POSITIVE_MESSAGE)
	private int salary;

	@Pattern(regexp = HumanResourceDTOConstants.EMPLOYMENT_DATE_PATTERN_REGEXP, message = HumanResourceDTOConstants.EMPLOYMENT_DATE_PATTERN_MESSAGE)
	private String employmentDate;

	private List<UpdateFamilyRequest> tempFamilyList;

	public Employee toEntity(Employee origin) throws ParseException {
		Employee copy = new Employee(origin);
		copy.setDepartmentId(this.departmentId);
		copy.setName(name);
		copy.setPosition(employeePosition);
		copy.setAddress(address);
		copy.setPhoneNumber(phoneNumber);
		copy.setBankName(bankName);
		copy.setBankAccount(bankAccount);
		copy.setSalary(salary);
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		copy.setEmploymentDate(formatter.parse(employmentDate));
		copy.setFamilyList(tempFamilyList.stream()
			.filter(updateFamilyRequest -> updateFamilyRequest.getId() != null)
			.map(familyRequest -> familyRequest.toEntity(id))
			.collect(Collectors.toList()));
		return copy;
	}
}
