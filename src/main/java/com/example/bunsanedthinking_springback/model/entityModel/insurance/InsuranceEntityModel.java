package com.example.bunsanedthinking_springback.model.entityModel.insurance;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.model.entityModel.automobile.AutomobileEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.disease.DiseaseEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.injury.InjuryEntityModel;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceEntityModel {
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseEntityModel diseaseEntityModel;
	@Autowired
	private InjuryEntityModel injuryDModel;
	@Autowired
	private AutomobileEntityModel automobileEntityModel;

	public Insurance getById(int id) {
		Insurance insurance = diseaseEntityModel.getById(id);
		if (insurance != null)
			return insurance;
		insurance = injuryDModel.getById(id);
		if (insurance != null)
			return insurance;
		insurance = automobileEntityModel.getById(id);
		return insurance;
	}

	public List<Insurance> getAll() {
		List<Insurance> insurances = new ArrayList<Insurance>();
		insuranceMapper.getAll()
			.forEach(e -> insurances.add(getById(e.getProduct_id())));
		return insurances;
	}

	public Integer getMaxId() {
		return insuranceMapper.getMaxId();
	}

	public void add(Insurance insurance) {
		if (insurance == null) return;
		else if (insurance instanceof Injury) injuryDModel.add((Injury) insurance);
		else if (insurance instanceof Disease) diseaseEntityModel.add((Disease) insurance);
		else if (insurance instanceof Automobile) automobileEntityModel.add((Automobile) insurance);
	}

	public void update(Insurance insurance) {
		if (insurance == null) return;
		else if (insurance instanceof Injury) injuryDModel.update((Injury) insurance);
		else if (insurance instanceof Disease) diseaseEntityModel.update((Disease) insurance);
		else if (insurance instanceof Automobile) automobileEntityModel.update((Automobile) insurance);
	}

	public void delete(int id) {
		Insurance insurance = getById(id);
		if (insurance == null) return;
		else if (insurance instanceof Injury) injuryDModel.delete(id);
		else if (insurance instanceof Disease) diseaseEntityModel.delete(id);
		else if (insurance instanceof Automobile) automobileEntityModel.delete(id);
	}
}
