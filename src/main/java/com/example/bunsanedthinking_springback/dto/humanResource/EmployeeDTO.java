package com.example.bunsanedthinking_springback.dto.humanResource;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDTO {
	private int teamId;
	private String name;
	private int employeePosition;
	private String address;
	private String phoneNumber;
	private String bankName;
	private String bankAccount;
	private String residentRegistrationNumber;
	private int departmentID;
	private int salary;
	private String employmentDate;
	private List<FamilyDTO> tempFamilyList;
}
