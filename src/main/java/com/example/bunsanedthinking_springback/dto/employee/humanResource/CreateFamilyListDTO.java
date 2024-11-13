package com.example.bunsanedthinking_springback.dto.employee.humanResource;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateFamilyListDTO {
	private LocalDate birthDate;
	private String name;
	private int relationship;
	private boolean survival;
}
