package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFamilyListRequest {
	private LocalDate birthDate;
	private String name;
	private RelationshipType relationship;
	private boolean survival;
}
