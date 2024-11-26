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
	private String survival;

	public static FamilyResponse from(Family family) {
		String survival;
		if (family.isSurvival())
			survival = "생존";
		else
			survival = "사망";
		return new FamilyResponse(family.getBirthDate(), family.getEmployeeId(), family.getId(), family.getName(),
			family.getRelationship().getName(), survival);
	}
}
