package com.example.bunsanedthinking_springback.model.domain.recontract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.RecontractMapper;
import com.example.bunsanedthinking_springback.vo.RecontractVO;

@Service
public class RecontractDModel {
	@Autowired
	private RecontractMapper recontractMapper;
	@Autowired
	private ContractDModel contractDModel;

	public Recontract getById(int id) {
		Contract contract = contractDModel.getById(id);
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

	public void add(RecontractVO recontractVO) {
		recontractMapper.insert(recontractVO);
	}

	public void update(RecontractVO recontractVO) {
		recontractMapper.update(recontractVO);
	}

	public void delete(int id) {
		recontractMapper.deleteById(id);
	}
}
