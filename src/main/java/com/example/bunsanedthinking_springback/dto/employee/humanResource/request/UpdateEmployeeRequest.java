package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateEmployeeRequest {
	private int id;
	private int departmentId;
	private String name;
	private EmployeePosition employeePosition;
	private String address;
	private String phoneNumber;
	private String bankName;
	private String bankAccount;
	private int salary;
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
