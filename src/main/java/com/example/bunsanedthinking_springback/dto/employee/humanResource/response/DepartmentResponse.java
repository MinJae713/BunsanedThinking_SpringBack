package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import com.example.bunsanedthinking_springback.entity.department.Department;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponse {
	private int id;
	private String name;

	public static DepartmentResponse from(Department department) {
		return new DepartmentResponse(department.getId(), department.getName());
	}
}
