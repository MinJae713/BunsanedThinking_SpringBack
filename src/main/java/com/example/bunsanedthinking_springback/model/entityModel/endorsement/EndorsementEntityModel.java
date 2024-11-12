package com.example.bunsanedthinking_springback.model.entityModel.endorsement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.repository.EndorsementMapper;

@Service
public class EndorsementEntityModel {
	@Autowired
	private EndorsementMapper endorsementMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public Endorsement getById(int id) {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null)
			return null;
		return endorsementMapper.getById(id)
			.map(endorsementVO -> endorsementVO.getEntity(contract))
			.orElse(null);
	}

	public List<Endorsement> getAll() {
		List<Endorsement> endorsements = new ArrayList<>();
		endorsementMapper.getAll()
			.forEach(
				e -> endorsements.add(getById(e.getContract_id()))
			);
		return endorsements;
	}

	public Integer getMaxId() {
		return endorsementMapper.getMaxId();
	}

	public void add(Endorsement endorsement) {
		if (endorsement == null)
			return;
		if (endorsementMapper.getById(endorsement.getId()).isPresent())
			return;
		contractEntityModel.add(endorsement);
		endorsementMapper.insert(endorsement.findEndorsementVO());
	}

	public void update(Endorsement endorsement) {
		if (endorsement == null)
			return;
		if (endorsementMapper.getById(endorsement.getId()).isEmpty())
			return;
		endorsementMapper.update(endorsement.findEndorsementVO());
		contractEntityModel.update(endorsement);
	}

	public void delete(int id) {
		if (endorsementMapper.getById(id).isEmpty())
			return;
		endorsementMapper.deleteById(id);
		contractEntityModel.delete(id);
	}
}
