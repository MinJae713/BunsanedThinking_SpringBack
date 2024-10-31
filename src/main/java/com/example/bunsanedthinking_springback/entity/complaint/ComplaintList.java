package com.example.bunsanedthinking_springback.entity.complaint;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public interface ComplaintList {

	/**
	 * 
	 * @param complaint
	 */
	public void add(Complaint complaint);

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public void delete(int id) throws NotExistException;

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public Complaint get(int id) throws NotExistException;

	/**
	 * 
	 * @param id
	 */
	public void update(Complaint complaint) throws NotExistException;

	public ArrayList<Complaint> getAllByCustomerId(int id);

	public ArrayList<Complaint> getAll();

	public ArrayList<Complaint> getAllUnprocessedComplaint();

	public ArrayList<Complaint> getAllProcessedComplant();

}