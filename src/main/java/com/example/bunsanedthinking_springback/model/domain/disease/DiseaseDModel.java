package com.example.bunsanedthinking_springback.model.domain.disease;

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
public class DiseaseDModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;

	public Disease getById(int id) {
		ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(id);
		if (insuranceVO == null)
			return null;
		DiseaseVO diseaseVO = diseaseMapper.getById_Customer(id).orElse(null);
		if (diseaseVO == null)
			return null;
		String diseaseName = diseaseVO.getDisease_name();
		int diseaseLimit = diseaseVO.getDisease_limit();
		int surgeriesLimit = diseaseVO.getSurgeries_limit();
		return new Disease(productVO, insuranceVO, diseaseName, diseaseLimit, surgeriesLimit);
	}

	public List<Disease> getAll() {
		List<Disease> diseases = new ArrayList<Disease>();
		diseaseMapper.getAll_Customer()
			.forEach(e -> diseases.add(getById(e.getProduct_id())));
		return diseases;
	}

	public Integer getMaxId() {
		return diseaseMapper.getMaxId();
	}

	public void add(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById_Customer(disease.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(disease.getProductVO());
		insuranceMapper.insert_ProductManagement(disease.getInsuranceVO());
		diseaseMapper.insert_ProductManagement(disease.getVO());
	}

	public void update(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById_Customer(disease.getId()).isEmpty()) return;
		diseaseMapper.update_ProductManagementModel(disease.getVO());
		insuranceMapper.update_ProductManagementModel(disease.getInsuranceVO());
		productMapper.update_LoanManagement(disease.getProductVO());
	}

	public void delete(int id) {
		if (diseaseMapper.getById_Customer(id).isEmpty()) return;
		diseaseMapper.deleteById(id);
		insuranceMapper.delete(id);
		productMapper.delete_ProductManagementModel(id);
	}
}
