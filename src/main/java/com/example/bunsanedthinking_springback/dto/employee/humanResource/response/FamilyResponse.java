package com.example.bunsanedthinking_springback.dto.employee.humanResource.response;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.HumanResourceDTOConstants;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyResponse {
	private String birthDate;
	private int employeeId;
	private int id;
	private String name;
	private String relationship;
	private String survival;

	public static FamilyResponse from(Family family) {
		String survival;
		if (family.isSurvival())
			survival = HumanResourceDTOConstants.SURVIVAL_LIVE;
		else
			survival = HumanResourceDTOConstants.SURVIVAL_DEATH;
		return new FamilyResponse(DateUtils.toString(family.getBirthDate()), family.getEmployeeId(), family.getId(),
			family.getName(), family.getRelationship().getName(), survival);
	}
}
