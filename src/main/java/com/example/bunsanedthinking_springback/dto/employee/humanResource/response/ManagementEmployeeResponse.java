package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import com.example.bunsanedthinking_springback.entity.department.Department;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagementEmployeeResponse {
	private int id;
	private String name;

	public static ManagementEmployeeResponse from(Department department) {
		return new ManagementEmployeeResponse(department.getId(), department.getName());
	}
}
