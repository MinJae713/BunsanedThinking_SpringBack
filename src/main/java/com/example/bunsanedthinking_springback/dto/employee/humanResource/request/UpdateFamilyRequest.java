package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.sql.Date;
import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.HumanResourceDTOConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateFamilyRequest {
	private Integer id;

	@Pattern(regexp = HumanResourceDTOConstants.BIRTH_DATE_PATTERN_REGEXP, message = HumanResourceDTOConstants.BIRTH_DATE_PATTERN_MESSAGE)
	private LocalDate birthDate;

	@Pattern(regexp = CommonConstants.NAME_PATTERN_REGEXP, message = CommonConstants.NAME_PATTERN_MESSAGE)
	@Size(max = 20, message = HumanResourceDTOConstants.NAME_SIZE_MESSAGE)
	private String name;

	@NotNull(message = HumanResourceDTOConstants.RELATIONSHIP_NOT_NULL_MESSAGE)
	private RelationshipType relationship;
	private boolean survival;

	public Family toEntity(int employeeId) {
		return new Family(Date.valueOf(birthDate), employeeId, id, name, relationship, survival);
	}

	public CreateFamilyListRequest toCreateRequest() {
		return new CreateFamilyListRequest(birthDate, name, relationship, survival);
	}
}
