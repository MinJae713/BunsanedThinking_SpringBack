package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddEmployeeRequest {
	private int departmentId;
	private String name;
	private EmployeePosition employeePosition;
	private String address;
	private String phoneNumber;
	private String bankName;
	private String bankAccount;
	private String residentRegistrationNumber;
	private int salary;
	private String employmentDate;
	private List<CreateFamilyListRequest> tempFamilyList;
}
