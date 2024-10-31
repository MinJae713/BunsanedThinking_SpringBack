package com.example.bunsanedthinking_springback.entity.surgeryHistory;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public interface SurgeryHistoryList {

	/**
	 * 
	 * @param surgeryHistory
	 */
	public void add(SurgeryHistory surgeryHistory);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public SurgeryHistory get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(SurgeryHistory surgeryHistory);

}