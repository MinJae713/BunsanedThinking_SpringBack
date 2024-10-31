package com.example.bunsanedthinking_springback.entity.endorsment;

import java.util.ArrayList;

public class EndorsementListImpl implements EndorsementList {

//	static private int index = 0; - 일단 안써서 주석처리

	private ArrayList<Endorsement> eContractList;

	public EndorsementListImpl() {
		this.eContractList = new ArrayList<>();
	}

	@Override
	public void add(Endorsement endorsement) {
		this.eContractList.add(endorsement);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Endorsement get(int id) {
		Endorsement contract = null;
		for (int i = 0; i < eContractList.size(); i++) {
			if (eContractList.get(i).getId() == id) {
				contract = eContractList.get(i);
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
	public ArrayList<Endorsement> getAllEndorsementContract() {
		ArrayList<Endorsement> endorsementcontractList = new ArrayList<>();
		for (Endorsement contract : eContractList) {
			endorsementcontractList.add(contract);
		}
		return endorsementcontractList;
	}

	@Override
	public ArrayList<Endorsement> getAllUnprocessedEndorsementContract() {
		ArrayList<Endorsement> endorsementcontractList = new ArrayList<>();
		for (Endorsement contract : eContractList) {
			if (contract.getEndorsementStatus() == EndorsementStatus.Completed) {
				endorsementcontractList.add(contract);
			}
		}
		return endorsementcontractList;
	}

	@Override
	public ArrayList<Endorsement> getAllProcessedEndorsementContract() {
		ArrayList<Endorsement> endorsementcontractList = new ArrayList<>();
		for (Endorsement contract : eContractList) {
			if (contract.getEndorsementStatus() == EndorsementStatus.Unprocessed) {
				endorsementcontractList.add(contract);
			}
		}
		return endorsementcontractList;
	}

}