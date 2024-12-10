package com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney;

import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.InsuranceMoneyMapper;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceMoneyEntityModel {
	@Autowired
	private InsuranceMoneyMapper insuranceMoneyMapper;
	@ReadOnly
	public InsuranceMoney getById(int id) {
		return insuranceMoneyMapper.getById(id)
			.map(InsuranceMoneyVO::getEntity)
			.orElse(null);
	}
	@ReadOnly
	public List<InsuranceMoney> getAll() {
		List<InsuranceMoney> insuranceMonies = new ArrayList<InsuranceMoney>();
		insuranceMoneyMapper.getAll()
			.forEach(e -> insuranceMonies.add(getById(e.getId())));
		return insuranceMonies;
	}
	@ReadOnly
	public Integer getMaxId() {
		return insuranceMoneyMapper.getMaxId();
	}

	public void add(InsuranceMoney insuranceMoney) {
		insuranceMoneyMapper.insert(insuranceMoney.findVO());
	}

	public void update(InsuranceMoney insuranceMoney) {
		insuranceMoneyMapper.update(insuranceMoney.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		insuranceMoneyMapper.deleteById(id);
	}
}
