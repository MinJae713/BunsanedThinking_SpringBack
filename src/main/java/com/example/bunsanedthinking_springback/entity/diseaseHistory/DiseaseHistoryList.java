package com.example.bunsanedthinking_springback.entity.diseaseHistory;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public interface DiseaseHistoryList {

	/**
	 * 
	 * @param diseaseHistory
	 */
	public void add(DiseaseHistory diseaseHistory);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public DiseaseHistory get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(DiseaseHistory diseaseHistory);

}