package com.example.bunsanedthinking_springback.model.domain.insuranceMoney;

import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.repository.InsuranceMoneyMapper;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceMoneyDModel {
	@Autowired
	private InsuranceMoneyMapper insuranceMoneyMapper;

	public InsuranceMoney getById(int id) {
		return insuranceMoneyMapper.getById_Compensation(id)
			.map(InsuranceMoneyVO::getEntity)
			.orElse(null);
	}

	public List<InsuranceMoney> getAll() {
		List<InsuranceMoney> insuranceMonies = new ArrayList<InsuranceMoney>();
		insuranceMoneyMapper.getAll_UnderwritingModel()
			.forEach(e -> insuranceMonies.add(e.getEntity()));
		return insuranceMonies;
	}

	public Integer getMaxId() {
		return insuranceMoneyMapper.getMaxId();
	}

	public void add(InsuranceMoney insuranceMoney) {
		insuranceMoneyMapper.insert(insuranceMoney.getVO());
	}

	public void update(InsuranceMoney insuranceMoney) {
		insuranceMoneyMapper.update(insuranceMoney.getVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		insuranceMoneyMapper.deleteById(id);
	}
}
