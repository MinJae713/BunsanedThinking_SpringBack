package com.example.bunsanedthinking_springback.model.domain.revival;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.RevivalMapper;
import com.example.bunsanedthinking_springback.vo.RevivalVO;

@Service
public class RevivalDModel {
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

	public void add(RevivalVO revivalVO) {
		revivalMapper.insert(revivalVO);
	}

	public void update(RevivalVO revivalVO) {
		revivalMapper.update(revivalVO);
	}

	public void delete(int id) {
		revivalMapper.deleteById(id);
	}
}
