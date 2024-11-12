package com.example.bunsanedthinking_springback.model.entityModel.recontract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.repository.RecontractMapper;

@Service
public class RecontractEntityModel {
	@Autowired
	private RecontractMapper recontractMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public Recontract getById(int id) {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null)
			return null;
		return recontractMapper.getById_Customer(id)
			.map(recontractVO -> recontractVO.getEntity(contract))
			.orElse(null);
	}

	public List<Recontract> getAll() {
		List<Recontract> recontracts = new ArrayList<Recontract>();
		recontractMapper.getAll_ContractManagement()
			.forEach(
				e -> recontracts.add(getById(e.getContract_id()))
			);
		return recontracts;
	}

	public Integer getMaxId() {
		return recontractMapper.getMaxId();
	}

	public void add(Recontract recontract) {
		if (recontract == null)
			return;
		if (recontractMapper.getById_Customer(recontract.getId()).isPresent())
			return;
		contractEntityModel.add(recontract);
		recontractMapper.insert(recontract.findRecontractVO());
	}

	public void update(Recontract recontract) {
		if (recontract == null)
			return;
		if (recontractMapper.getById_Customer(recontract.getId()).isEmpty())
			return;
		recontractMapper.update(recontract.findRecontractVO());
		contractEntityModel.update(recontract);
	}

	public void delete(int id) {
		if (recontractMapper.getById_Customer(id).isEmpty())
			return;
		recontractMapper.deleteById(id);
		contractEntityModel.delete(id);
	}
}
