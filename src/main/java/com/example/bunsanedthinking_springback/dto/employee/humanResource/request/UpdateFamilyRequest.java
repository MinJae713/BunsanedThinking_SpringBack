package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.sql.Date;
import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateFamilyRequest {
	private Integer id;
	private LocalDate birthDate;
	private String name;
	private RelationshipType relationship;
	private boolean survival;

	public Family toEntity(int employeeId) {
		return new Family(Date.valueOf(birthDate), employeeId, id, name, relationship, survival);
	}

	public CreateFamilyListRequest toCreateRequest() {
		return new CreateFamilyListRequest(birthDate, name, relationship, survival);
	}
}
