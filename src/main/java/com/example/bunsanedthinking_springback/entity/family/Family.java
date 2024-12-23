package com.example.bunsanedthinking_springback.entity.family;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.vo.FamilyVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family implements Cloneable {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DATE_FORMAT)
	private Date birthDate;
	private int employeeId;
	private int id;
	private String name;
	private RelationshipType relationship;
	private boolean survival;

	public Family(RelationshipType relationshipType, String familyName, boolean survival, Date date) {
		this.setRelationship(relationshipType);
		this.setName(familyName);
		this.setSurvival(survival);
		this.setBirthDate(date);

	}

	public FamilyVO findVO() {
		LocalDate lBirthDate = new java.util.Date(birthDate.getTime()).toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		return new FamilyVO(id, lBirthDate, name,
			relationship.ordinal(), survival,
			employeeId);
	}

	public String getBirthDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		return dateFormat.format(this.birthDate);
	}

	public Family clone() {
		Family cloneFamily = new Family(getRelationship(), getName(), isSurvival(), this.birthDate);
		cloneFamily.setId(getId());
		return cloneFamily;
	}

}
