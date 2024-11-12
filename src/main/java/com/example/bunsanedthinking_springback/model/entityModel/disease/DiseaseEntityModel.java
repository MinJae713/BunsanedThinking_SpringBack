package com.example.bunsanedthinking_springback.model.entityModel.disease;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;

	public Disease getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		DiseaseVO diseaseVO = diseaseMapper.getById(id).orElse(null);
		if (diseaseVO == null)
			return null;
		String diseaseName = diseaseVO.getDisease_name();
		int diseaseLimit = diseaseVO.getDisease_limit();
		int surgeriesLimit = diseaseVO.getSurgeries_limit();
		return new Disease(productVO, insuranceVO, diseaseName, diseaseLimit, surgeriesLimit);
	}

	public List<Disease> getAll() {
		List<Disease> diseases = new ArrayList<Disease>();
		diseaseMapper.getAll()
			.forEach(e -> diseases.add(getById(e.getProduct_id())));
		return diseases;
	}

	public Integer getMaxId() {
		return diseaseMapper.getMaxId();
	}

	public void add(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById(disease.getId()).isPresent()) return;
		productMapper.insert(disease.findProductVO());
		insuranceMapper.insert(disease.findInsuranceVO());
		diseaseMapper.insert(disease.findVO());
	}

	public void update(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById(disease.getId()).isEmpty()) return;
		diseaseMapper.update(disease.findVO());
		insuranceMapper.update(disease.findInsuranceVO());
		productMapper.update(disease.findProductVO());
	}

	public void delete(int id) {
		if (diseaseMapper.getById(id).isEmpty()) return;
		diseaseMapper.deleteById(id);
		insuranceMapper.deleteById(id);
		productMapper.deleteById(id);
	}
}
