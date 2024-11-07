package com.example.bunsanedthinking_springback.model.domain.endorsement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.EndorsementMapper;
import com.example.bunsanedthinking_springback.vo.EndorsementVO;

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
		return endorsementMapper.getById_Customer(id)
			.map(endorsementVO -> endorsementVO.getEntity(contract))
			.orElse(null);
	}

	public List<Endorsement> getAll() {
		List<Endorsement> endorsements = new ArrayList<>();
		endorsementMapper.getAll_ContractManagement()
			.forEach(
				e -> endorsements.add(getById(e.getContract_id()))
			);
		return endorsements;
	}

	public Integer getMaxId() {
		return endorsementMapper.getMaxId();
	}

	public void add(EndorsementVO endorsementVO) {
		endorsementMapper.insert(endorsementVO);
	}

	public void update(EndorsementVO endorsementVO) {
		endorsementMapper.update(endorsementVO);
	}

	public void delete(int id) {
		endorsementMapper.deleteById(id);
	}
}
