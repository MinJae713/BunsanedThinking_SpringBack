package com.example.bunsanedthinking_springback.model.entityModel.termination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.TerminationMapper;

@Service
public class TerminationEntityModel {
	@Autowired
	private TerminationMapper terminationMapper;
	@Autowired
	private ContractDModel contractDModel;

	public Termination getById(int id) {
		Contract contract = contractDModel.getById(id);
		if (contract == null)
			return null;
		return terminationMapper.getById(id)
			.map(terminationVO -> terminationVO.getEntity(contract))
			.orElse(null);
	}

	public List<Termination> getAll() {
		List<Termination> terminations = new ArrayList<>();
		terminationMapper.getAll()
			.forEach(
				e -> terminations.add(getById(e.getContract_id()))
			);
		return terminations;
	}

	public Integer getMaxId() {
		return terminationMapper.getMaxId();
	}

	public void add(Termination termination) {
		if (termination == null) return;
		if (terminationMapper.getById(termination.getId()).isPresent()) return;
		contractDModel.add(termination);
		terminationMapper.insert(termination.findTerminationVO());
	}

	public void update(Termination termination) {
		if (termination == null) return;
		if (terminationMapper.getById(termination.getId()).isEmpty()) return;
		terminationMapper.update(termination.findTerminationVO());
		contractDModel.update(termination);
	}

	public void delete(int id) {
		if (terminationMapper.getById(id).isEmpty()) return;
		terminationMapper.deleteById(id);
		contractDModel.delete(id);
	}
}
