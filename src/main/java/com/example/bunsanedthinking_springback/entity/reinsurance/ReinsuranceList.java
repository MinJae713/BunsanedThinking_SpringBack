package com.example.bunsanedthinking_springback.entity.reinsurance;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public interface ReinsuranceList {

	/**
	 * 
	 * @param reinsurance
	 */
	public void add(Reinsurance reinsurance);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public Reinsurance get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(int id);

}