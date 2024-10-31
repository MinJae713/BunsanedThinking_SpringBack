package com.example.bunsanedthinking_springback.entity.recontract;

import java.util.ArrayList;

public class RecontractListImpl implements RecontractList {

	private ArrayList<Recontract> RcontractList;

	@Override
	public void add(Recontract contract) {
		this.RcontractList.add(contract);
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public Recontract get(int id) {
		Recontract contract = null;
		for (int i = 0; i < RcontractList.size(); i++) {
			if (RcontractList.get(i).getId() == id) {
				contract = RcontractList.get(i);
				break;
			}
		}
		return contract;
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Recontract> getAllReContract() {
		ArrayList<Recontract> recontractList = new ArrayList<>();
		for (Recontract contract : RcontractList) {
			recontractList.add(contract);
		}
		return recontractList;
	}

	@Override
	public ArrayList<Recontract> getAllUnprocessedReContract() {
		ArrayList<Recontract> recontractList = new ArrayList<>();
		for (Recontract contract : RcontractList) {
			if (contract.getRecontractStatus() == RecontractStatus.Completed) {
				recontractList.add(contract);
			}
		}
		return recontractList;
	}

	@Override
	public ArrayList<Recontract> getAllProcessedReContract() {
		ArrayList<Recontract> recontractList = new ArrayList<>();
		for (Recontract contract : RcontractList) {
			if (contract.getRecontractStatus() == RecontractStatus.Unprocessed) {
				recontractList.add(contract);
			}
		}
		return recontractList;
	}

	@Override
	public Recontract getReContractById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}