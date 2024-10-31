package com.example.bunsanedthinking_springback.entity.family;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public class Family implements Cloneable{

	public static int FAMILY_SERIAL_NUMBER = 710;
	
	private Date birthDate;
	private int employeeID;
	private int id;
	private String name;
	private RelationshipType relationship;
	private boolean survial;

	public Family(RelationshipType relationshipType, String familyName, boolean survial, Date date){
		this.setRelationship(relationshipType);
		this.setName(familyName);
		this.setSurvial(survial);
		this.setBirthDate(date);
		
	}

	public String getBirthDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.birthDate);
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RelationshipType getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationshipType relationship) {
		this.relationship = relationship;
	}

	public boolean isSurvial() {
		return survial;
	}

	public void setSurvial(boolean survial) {
		this.survial = survial;
	}
	
	public Family clone() {
		Family cloneFamily =  new Family(getRelationship(),getName(), isSurvial(), this.birthDate);
		cloneFamily.setId(getId());
		return cloneFamily;
	}

}