package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateEmployeeRequest {
	@Positive(message = "잘못된 직원 번호입니다.")
	private int id;

	@Positive(message = "잘못된 부서 번호입니다.")
	private int departmentId;

	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;
	private EmployeePosition employeePosition;

	@Size(max = 50, message = "주소가 50글자를 초과하였습니다.")
	private String address;

	@Pattern(regexp = "/01[016789]([0-9]{3,4})([0-9]{4})/", message = "잘못된 전화번호 형식입니다.")
	private String phoneNumber;

	@Size(max = 10, message = "은행명이 10글자를 초과하였습니다.")
	private String bankName;

	@Size(max = 20, message = "계좌번호가 20글자를 초과하였습니다.")
	private String bankAccount;

	@Positive(message = "급여가 0보다 작습니다.")
	private int salary;

	@Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])", message = "잘못된 날짜 형식입니다.")
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		copy.setEmploymentDate(formatter.parse(employmentDate));
		copy.setFamilyList(tempFamilyList.stream()
			.filter(updateFamilyRequest -> updateFamilyRequest.getId() != null)
			.map(familyRequest -> familyRequest.toEntity(id))
			.collect(Collectors.toList()));
		return copy;
	}
}
