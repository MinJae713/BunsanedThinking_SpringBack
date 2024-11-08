package com.example.bunsanedthinking_springback.model.domain.injury;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InjuryDModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private InjuryMapper injuryMapper;

	public Injury getById(int id) {
		ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(id);
		if (insuranceVO == null)
			return null;
		InjuryVO injuryVO = injuryMapper.getById_Customer(id).orElse(null);
		if (injuryVO == null)
			return null;
		return injuryVO.getEntity(productVO, insuranceVO);
	}

	public List<Injury> getAll() {
		List<Injury> injuries = new ArrayList<Injury>();
		injuryMapper.getAll_Customer()
			.forEach(e -> injuries.add(getById(e.getProduct_id())));
		return injuries;
	}

	public Integer getMaxId() {
		return injuryMapper.getMaxId();
	}

	public void add(Injury injury) {
		// 참조 무결성 제약조건 생각해서 product, insurance, injury 순서대로 데이터 추가함
		if (injury == null) return;
		if (injuryMapper.getById_Customer(injury.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(injury.getProductVO());
		insuranceMapper.insert_ProductManagement(injury.getInsuranceVO());
		injuryMapper.insert_ProductManagement(injury.getVO());
	}

	public void update(Injury injury) {
		if (injury == null) return;
		if (injuryMapper.getById_Customer(injury.getId()).isEmpty()) return;
		injuryMapper.update_ProductManagementModel(injury.getVO());
		insuranceMapper.update_ProductManagementModel(injury.getInsuranceVO());
		productMapper.update_LoanManagement(injury.getProductVO());
	}

	public void delete(int id) {
		// 참조 무결성 제약조건 생각해서 injury, insurnace, product 순서대로 데이터 삭제
		if (injuryMapper.getById_Customer(id).isEmpty()) return;
		injuryMapper.deleteById(id);
		insuranceMapper.delete(id);
		productMapper.delete_ProductManagementModel(id);
	}
}
