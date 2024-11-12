package com.example.bunsanedthinking_springback.model.entityModel.revival;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.repository.RevivalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevivalEntityModel {
	@Autowired
	private RevivalMapper revivalMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public Revival getById(int id) {
		Contract contract = contractEntityModel.getById(id);
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
		if (revival == null)
			return;
		if (revivalMapper.getById_Customer(revival.getId()).isPresent())
			return;
		contractEntityModel.add(revival);
		revivalMapper.insert(revival.findRevivalVO());
	}

	public void update(Revival revival) {
		if (revival == null)
			return;
		if (revivalMapper.getById_Customer(revival.getId()).isEmpty())
			return;
		revivalMapper.update(revival.findRevivalVO());
		contractEntityModel.update(revival);
	}

	public void delete(int id) {
		if (revivalMapper.getById_Customer(id).isEmpty())
			return;
		revivalMapper.deleteById(id);
		contractEntityModel.delete(id);
	}
}
