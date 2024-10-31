package com.example.bunsanedthinking_springback.entity.revival;

import java.util.ArrayList;

public class RevivalListImpl implements RevivalList {

	private ArrayList<Revival> rvContractList;

	public RevivalListImpl() {
		this.rvContractList = new ArrayList<>();
	}

	@Override
	public void add(Revival contract) {
		this.rvContractList.add(contract);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Revival get(int id) {
		Revival contract = null;
		for (int i = 0; i < rvContractList.size(); i++) {
			if (rvContractList.get(i).getId() == id) {
				contract = rvContractList.get(i);
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
	public ArrayList<Revival> getAllRevivalContract() {
		ArrayList<Revival> contractList = new ArrayList<>();
		for (Revival contract : rvContractList) {
			contractList.add(contract);
		}
		return contractList;
	}

	@Override
	public ArrayList<Revival> getAllUnprocessedRevival() {
		ArrayList<Revival> contractList = new ArrayList<>();
		for (Revival contract : rvContractList) {
			if (contract.getRevivalStatus() == RevivalStatus.Unprocessed) {
				contractList.add(contract);
			}
		}
		return contractList;
	}

	@Override
	public ArrayList<Revival> getAllProcessedRevival() {
		ArrayList<Revival> contractList = new ArrayList<>();
		for (Revival contract : rvContractList) {
			if (contract.getRevivalStatus() == RevivalStatus.Completed) {
				contractList.add(contract);
			}
		}
		return contractList;
	}

	@Override
	public Revival getRevivalById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}