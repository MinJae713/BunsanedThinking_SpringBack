package com.example.bunsanedthinking_springback.model.entityModel.endorsement;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.repository.EndorsementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndorsementEntityModel {
	@Autowired
	private EndorsementMapper endorsementMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@ReadOnly
	public Endorsement getById(int id) {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null)
			return null;
		return endorsementMapper.getById(id)
			.map(endorsementVO -> endorsementVO.getEntity(contract))
			.orElse(null);
	}
	@ReadOnly
	public List<Endorsement> getAll() {
		List<Endorsement> endorsements = new ArrayList<>();
		endorsementMapper.getAll()
			.forEach(
				e -> endorsements.add(getById(e.getContract_id()))
			);
		return endorsements;
	}
	@ReadOnly
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
