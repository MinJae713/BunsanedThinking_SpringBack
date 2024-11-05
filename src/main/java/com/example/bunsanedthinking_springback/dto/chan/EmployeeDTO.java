package com.example.bunsanedthinking_springback.dto.chan;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

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