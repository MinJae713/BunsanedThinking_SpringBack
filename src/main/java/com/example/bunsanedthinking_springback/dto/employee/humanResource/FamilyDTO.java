package com.example.bunsanedthinking_springback.dto.employee.humanResource;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyDTO {
	private LocalDate birthDate;
	private String name;
	private int relationship;
	private boolean survival;
}
