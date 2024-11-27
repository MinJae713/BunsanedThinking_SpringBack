package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import com.example.bunsanedthinking_springback.entity.employee.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagementEmployeeResponse {
	private int id;
	private String name;
	private String position;
	private int departmentId;
	private int salary;

	public static ManagementEmployeeResponse from(Employee employee) {
		return new ManagementEmployeeResponse(employee.getId(), employee.getName(), employee.getPosition().getName(),
			employee.getDepartmentId(), employee.getSalary());
	}

	public static ManagementEmployeeDetailResponse fromWithDetail(Employee employee) {
		return ManagementEmployeeDetailResponse.from(employee);
	}
}
