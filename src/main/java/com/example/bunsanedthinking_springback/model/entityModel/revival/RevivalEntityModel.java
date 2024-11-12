package com.example.bunsanedthinking_springback.model.entityModel.revival;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.RevivalMapper;

@Service
public class RevivalEntityModel {
	@Autowired
	private RevivalMapper revivalMapper;
	@Autowired
	private ContractDModel contractDModel;

	public Revival getById(int id) {
		Contract contract = contractDModel.getById(id);
		if (contract == null)
			return null;
		return revivalMapper.getById_Customer(id)
			.map(revivalVO -> revivalVO.getEntity(contract))
			.orElse(null);
	}

	public List<Revival> getAll() {
		List<Revival> revivals = new ArrayList<Revival>();
		revivalMapper.getAll_ContractManagement()
			.forEach(
				e -> revivals.add(getById(e.getContract_id()))
			);
		return revivals;
	}

	public Integer getMaxId() {
		return revivalMapper.getMaxId();
	}

	public void add(Revival revival) {
		if (revival == null) return;
		if (revivalMapper.getById_Customer(revival.getId()).isPresent()) return;
		contractDModel.add(revival);
		revivalMapper.insert(revival.findRevivalVO());
	}

	public void update(Revival revival) {
		if (revival == null) return;
		if (revivalMapper.getById_Customer(revival.getId()).isEmpty()) return;
		revivalMapper.update(revival.findRevivalVO());
		contractDModel.update(revival);
	}

	public void delete(int id) {
		if (revivalMapper.getById_Customer(id).isEmpty()) return;
		revivalMapper.deleteById(id);
		contractDModel.delete(id);
	}
}
