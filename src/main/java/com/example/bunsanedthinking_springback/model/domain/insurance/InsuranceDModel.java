package com.example.bunsanedthinking_springback.model.domain.insurance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.model.domain.automobile.AutomobileDModel;
import com.example.bunsanedthinking_springback.model.domain.disease.DiseaseDModel;
import com.example.bunsanedthinking_springback.model.domain.injury.InjuryDModel;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;

@Service
public class InsuranceDModel {
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseDModel diseaseDModel;
	@Autowired
	private InjuryDModel injuryDModel;
	@Autowired
	private AutomobileDModel automobileDModel;

	public Insurance getById(int id) {
		Insurance insurance = diseaseDModel.getById(id);
		if (insurance != null)
			return insurance;
		insurance = injuryDModel.getById(id);
		if (insurance != null)
			return insurance;
		insurance = automobileDModel.getById(id);
		return insurance;
	}

	public List<Insurance> getAll() {
		List<Insurance> insurances = new ArrayList<Insurance>();
		insuranceMapper.getAll_Customer()
			.forEach(e -> insurances.add(getById(e.getProduct_id())));
		return insurances;
	}

	public Integer getMaxId() {
		return insuranceMapper.getMaxId_ProductManagementModel();
	}

	public void add(InsuranceVO insuranceVO) {
		insuranceMapper.insert_ProductManagement(insuranceVO);
	}

	public void update(InsuranceVO insuranceVO) {
		insuranceMapper.update_ProductManagementModel(insuranceVO);
	}

	public void delete(int id) {
		insuranceMapper.delete(id);
	}
}
