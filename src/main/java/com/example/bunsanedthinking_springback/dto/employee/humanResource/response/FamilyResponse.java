package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import java.util.Date;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthDate;
	private int employeeId;
	private int id;
	private String name;
	private String relationship;
	private boolean survival;

	public static FamilyResponse from(Family family) {
		return new FamilyResponse(family.getBirthDate(), family.getEmployeeId(), family.getId(), family.getName(),
			family.getRelationship().getName(), family.isSurvival());
	}
}
