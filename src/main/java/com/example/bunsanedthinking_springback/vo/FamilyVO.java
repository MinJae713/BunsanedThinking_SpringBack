package com.example.bunsanedthinking_springback.vo;

import java.sql.Date;
import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyVO {
	private int id;
	private LocalDate birth_date;
	private String name;
	private int relationship;
	private boolean survival;
	private int employee_id;

	public Family getEntity() {
		Family family = new Family();
		family.setId(id);
		family.setBirthDate(Date.valueOf(birth_date));
		family.setName(name);
		family.setRelationship(RelationshipType.values()[relationship]);
		family.setSurvival(survival);
		family.setEmployeeId(employee_id);
		return family;
	}
}
