package com.example.bunsanedthinking_springback.model.domain.insuranceMoney;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.repository.InsuranceMoneyMapper;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;

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

	public int getMaxId() {
		return insuranceMoneyMapper.getMaxId();
	}

	public void add(InsuranceMoneyVO insuranceMoneyVO) {
		insuranceMoneyMapper.insert(insuranceMoneyVO);
	}

	public void update(InsuranceMoneyVO insuranceMoneyVO) {
		insuranceMoneyMapper.update(insuranceMoneyVO);
	}

	public void delete(int id) {
		insuranceMoneyMapper.deleteById(id);
	}
}
