package com.example.bunsanedthinking_springback.model.entityModel.revival;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
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
	@ReadOnly
	public Revival getById(int id) {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null)
			return null;
		return revivalMapper.getById(id)
			.map(revivalVO -> revivalVO.getEntity(contract))
			.orElse(null);
	}
	@ReadOnly
	public List<Revival> getAll() {
		List<Revival> revivals = new ArrayList<Revival>();
		revivalMapper.getAll()
			.forEach(
				e -> revivals.add(getById(e.getContract_id()))
			);
		return revivals;
	}
	@ReadOnly
	public Integer getMaxId() {
		return revivalMapper.getMaxId();
	}

	public void add(Revival revival) {
		if (revival == null) return;
		if (revivalMapper.getById(revival.getId()).isPresent()) return;
		contractEntityModel.add(revival);
		revivalMapper.insert(revival.findRevivalVO());
	}

	public void update(Revival revival) {
		if (revival == null) return;
		if (revivalMapper.getById(revival.getId()).isEmpty()) return;
		revivalMapper.update(revival.findRevivalVO());
		contractEntityModel.update(revival);
	}

	public void delete(int id) {
		if (revivalMapper.getById(id).isEmpty()) return;
		revivalMapper.deleteById(id);
		contractEntityModel.delete(id);
	}
}
