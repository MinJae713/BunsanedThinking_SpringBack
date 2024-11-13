package com.example.bunsanedthinking_springback.dto.employee.humanResource;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddEmployeeDTO {
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
	private List<CreateFamilyListDTO> tempFamilyList;
}
