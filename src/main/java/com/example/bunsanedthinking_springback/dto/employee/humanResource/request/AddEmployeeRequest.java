package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddEmployeeRequest {
	private int departmentId;
	private String name;
	private int employeePosition;
	private String address;
	private String phoneNumber;
	private String bankName;
	private String bankAccount;
	private String residentRegistrationNumber;
	private int salary;
	private String employmentDate;
	private List<CreateFamilyListRequest> tempFamilyList;
}
