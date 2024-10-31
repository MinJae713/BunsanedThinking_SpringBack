package com.example.bunsanedthinking_springback.entity.accidentHistory;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public interface AccidentHistoryList {

	/**
	 * 
	 * @param accidentHistory
	 */
	public void add(AccidentHistory accidentHistory);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public AccidentHistory get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(AccidentHistory accidentHistory);

}