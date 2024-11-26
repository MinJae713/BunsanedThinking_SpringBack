package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ManagementEmployeeDetailResponse extends ManagementEmployeeResponse {
	private String address;
	private String bankAccount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date employmentDate;
	private String bankName;
	private List<FamilyResponse> familyList;
	private String phoneNumber;
	private String residentRegistrationNumber;

	public ManagementEmployeeDetailResponse(int id, String name, String position, int departmentId, int salary,
		String address, String bankAccount, Date employmentDate, String bankName, List<Family> familyList,
		String phoneNumber, String residentRegistrationNumber) {
		super(id, name, position, departmentId, salary);
		this.address = address;
		this.bankAccount = bankAccount;
		this.employmentDate = employmentDate;
		this.bankName = bankName;
		this.familyList = familyList.stream()
			.map(FamilyResponse::from)
			.collect(Collectors.toList());
		this.phoneNumber = phoneNumber;
		this.residentRegistrationNumber = residentRegistrationNumber;
	}

	public static ManagementEmployeeDetailResponse from(Employee employee) {
		return new ManagementEmployeeDetailResponse(employee.getId(), employee.getName(),
			employee.getPosition().getName(), employee.getDepartmentId(), employee.getSalary(), employee.getAddress(),
			employee.getBankAccount(), employee.getEmploymentDate(), employee.getBankName(), employee.getFamilyList(),
			employee.getPhoneNumber(), employee.getResidentRegistrationNumber());
	}
}
