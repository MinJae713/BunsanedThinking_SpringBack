package com.example.bunsanedthinking_springback.entity.recontract;

import java.util.ArrayList;

public interface RecontractList {
	/**
	 * 
	 * @param contract
	 */
	public void add(Recontract contract);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 */
	public Recontract get(int id);

	/**
	 * 
	 * @param id
	 */
	public void update(int id);

	public ArrayList<Recontract> getAllReContract();
	public ArrayList<Recontract> getAllUnprocessedReContract();
	public ArrayList<Recontract> getAllProcessedReContract();
	public Recontract getReContractById(int id);
}
