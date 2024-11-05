package com.example.bunsanedthinking_springback.dto.chan;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyDTO {
	private LocalDate birthDate;
	private int employeeID;
	private String name;
	private int relationship;
	private boolean survival;
}
