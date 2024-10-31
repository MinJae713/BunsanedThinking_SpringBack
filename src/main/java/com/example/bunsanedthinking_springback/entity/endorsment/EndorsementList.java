package com.example.bunsanedthinking_springback.entity.endorsment;

import java.util.ArrayList;

public interface EndorsementList {

	/**
	 * 
	 * @param contract
	 */
	public void add(Endorsement contract);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public Endorsement get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(int id);
	
	public ArrayList<Endorsement> getAllEndorsementContract();
	public ArrayList<Endorsement> getAllUnprocessedEndorsementContract();
	public ArrayList<Endorsement> getAllProcessedEndorsementContract();

}