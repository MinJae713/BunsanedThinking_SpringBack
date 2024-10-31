package com.example.bunsanedthinking_springback.entity.termination;

import java.util.ArrayList;

public class TerminationListImpl implements TerminationList {

//	static private int index = 0; - 일단 안써서 주석처리
	
	private ArrayList<Termination> tContractList;

	public TerminationListImpl(){
		this.tContractList = new ArrayList<>();
	}
	
	public void add(Termination contract) {
		this.tContractList.add(contract);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Termination get(int id) {
		Termination contract = null;
		for (int i=0; i<tContractList.size(); i++) {
			if (tContractList.get(i).getId() == id) {
				contract = tContractList.get(i);
				break;
			}
		}
		return contract;
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub
		
	}
	public Termination getTerminatingContractById(int id) {
		Termination result = null;
		for (Termination contract : tContractList) {
			if (contract.getId() == id) {
					result = (Termination) contract.clone();
				}
		}
		return result;
	}
	
	public ArrayList<Termination> getAllTerminatingContract() {
		ArrayList<Termination> terminatingcontractList = new ArrayList<>();
		for (Termination contract : tContractList) {
			terminatingcontractList.add(contract);
		}
		return terminatingcontractList;
	}
	
	public ArrayList<Termination> getAllProcessedTerminatingContract() {
		ArrayList<Termination> terminatingcontractList = new ArrayList<>();
		for (Termination contract : tContractList) {
			if (contract.getTerminationStatus() == TerminationStatus.Completed) {
				terminatingcontractList.add(contract);
			}
		}
		return terminatingcontractList;
	}

	@Override
	public ArrayList<Termination> getAllUnprocessedTerminatingContract() {
		ArrayList<Termination> terminatingcontractList = new ArrayList<>();
		for (Termination contract : tContractList) {
			if (contract.getTerminationStatus() == TerminationStatus.Unprocessed) {
				terminatingcontractList.add(contract);
			}
		}
		return terminatingcontractList;
	}

}
