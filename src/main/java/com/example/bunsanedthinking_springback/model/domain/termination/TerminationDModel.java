package com.example.bunsanedthinking_springback.model.domain.termination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.TerminationMapper;
import com.example.bunsanedthinking_springback.vo.TerminationVO;

@Service
public class TerminationDModel {
	@Autowired
	private TerminationMapper terminationMapper;
	@Autowired
	private ContractDModel contractDModel;

	public Termination getById(int id) {
		Contract contract = contractDModel.getById(id);
		if (contract == null)
			return null;
		return terminationMapper.getById_Customer(id)
			.map(terminationVO -> terminationVO.getEntity(contract))
			.orElse(null);
	}

	public List<Termination> getAll() {
		List<Termination> terminations = new ArrayList<>();
		terminationMapper.getAll_ContractManagement()
			.forEach(
				e -> terminations.add(getById(e.getContract_id()))
			);
		return terminations;
	}

	public int getMaxId() {
		return terminationMapper.getMaxId();
	}

	public void add(TerminationVO terminationVO) {
		terminationMapper.insert(terminationVO);
	}

	public void update(TerminationVO terminationVO) {
		terminationMapper.update(terminationVO);
	}

	public void delete(int id) {
		terminationMapper.deleteById(id);
	}
}
