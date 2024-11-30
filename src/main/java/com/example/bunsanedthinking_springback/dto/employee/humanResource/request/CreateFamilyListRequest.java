package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFamilyListRequest {
	private LocalDate birthDate;

	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;
	private RelationshipType relationship;
	private boolean survival;
}
