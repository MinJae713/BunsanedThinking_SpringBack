package com.example.bunsanedthinking_springback.entity.jointAcquisition;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public interface JointAcquisitionList {

	/**
	 * 
	 * @param joinAcquisition
	 */
	public void add(JointAcquisition joinAcquisition);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public JointAcquisition get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(int id);

}