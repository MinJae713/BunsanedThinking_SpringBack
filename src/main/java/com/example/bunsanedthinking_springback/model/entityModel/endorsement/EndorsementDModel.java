package com.example.bunsanedthinking_springback.model.entityModel.endorsement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.EndorsementMapper;

@Service
public class EndorsementDModel {
	@Autowired
	private EndorsementMapper endorsementMapper;
	@Autowired
	private ContractDModel contractDModel;

	public Endorsement getById(int id) {
		Contract contract = contractDModel.getById(id);
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
		contractDModel.add(endorsement);
		endorsementMapper.insert(endorsement.findEndorsementVO());
	}

	public void update(Endorsement endorsement) {
		if (endorsement == null)
			return;
		if (endorsementMapper.getById(endorsement.getId()).isEmpty())
			return;
		endorsementMapper.update(endorsement.findEndorsementVO());
		contractDModel.update(endorsement);
	}

	public void delete(int id) {
		if (endorsementMapper.getById(id).isEmpty())
			return;
		endorsementMapper.deleteById(id);
		contractDModel.delete(id);
	}
}
