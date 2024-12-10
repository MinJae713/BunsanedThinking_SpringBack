package com.example.bunsanedthinking_springback.model.entityModel.recontract;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.repository.RecontractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecontractEntityModel {
	@Autowired
	private RecontractMapper recontractMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@ReadOnly
	public Recontract getById(int id) {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null)
			return null;
		return recontractMapper.getById(id)
			.map(recontractVO -> recontractVO.getEntity(contract))
			.orElse(null);
	}
	@ReadOnly
	public List<Recontract> getAll() {
		List<Recontract> recontracts = new ArrayList<Recontract>();
		recontractMapper.getAll()
			.forEach(
				e -> recontracts.add(getById(e.getContract_id()))
			);
		return recontracts;
	}
	@ReadOnly
	public Integer getMaxId() {
		return recontractMapper.getMaxId();
	}

	public void add(Recontract recontract) {
		if (recontract == null) return;
		if (recontractMapper.getById(recontract.getId()).isPresent()) return;
		contractEntityModel.add(recontract);
		recontractMapper.insert(recontract.findRecontractVO());
	}

	public void update(Recontract recontract) {
		if (recontract == null) return;
		if (recontractMapper.getById(recontract.getId()).isEmpty()) return;
		recontractMapper.update(recontract.findRecontractVO());
		contractEntityModel.update(recontract);
	}

	public void delete(int id) {
		if (recontractMapper.getById(id).isEmpty()) return;
		recontractMapper.deleteById(id);
		contractEntityModel.delete(id);
	}
}
