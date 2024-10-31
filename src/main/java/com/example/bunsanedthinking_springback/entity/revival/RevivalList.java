package com.example.bunsanedthinking_springback.entity.revival;

import java.util.ArrayList;

public interface RevivalList {
	/**
	 * 
	 * @param contract
	 */
	public void add(Revival contract);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public Revival get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(int id);

	public ArrayList<Revival> getAllRevivalContract();
	public ArrayList<Revival> getAllUnprocessedRevival();
	public ArrayList<Revival> getAllProcessedRevival();
	public Revival getRevivalById(int id);
}
