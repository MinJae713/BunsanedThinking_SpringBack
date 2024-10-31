package com.example.bunsanedthinking_springback.entity.family;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public class FamilyListImpl implements FamilyList {

	private ArrayList<Family> familyList;
	private int index = 0;

	public FamilyListImpl(){
		familyList = new ArrayList<Family>();
	}

	/**
	 * 
	 * @param family
	 */
	public void add(Family family){
		index++;
		String compound = Family.FAMILY_SERIAL_NUMBER + "" +index;
		family.setId(Integer.parseInt(compound));
		this.familyList.add(family);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id){
		for (Family e : familyList) {
			if (e != null && e.getId() == id) {
				familyList.remove(e);
				break;
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public Family get(int id){
		for (Family e : familyList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public void update(Family family){
		for (int i = 0; i < familyList.size(); i++) {
			if (familyList.get(i).getId() == family.getId()) {
				familyList.set(i, family);
			}
		}
	}

}