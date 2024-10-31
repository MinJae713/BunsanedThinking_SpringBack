package com.example.bunsanedthinking_springback.entity.family;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public interface FamilyList {

	/**
	 * 
	 * @param family
	 */
	public void add(Family family);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public Family get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(Family family);

}