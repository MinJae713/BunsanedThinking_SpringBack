package com.example.bunsanedthinking_springback.model.entityModel.injury;

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
public class InjuryEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private InjuryMapper injuryMapper;

	public Injury getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		InjuryVO injuryVO = injuryMapper.getById(id).orElse(null);
		if (injuryVO == null)
			return null;
		return injuryVO.getEntity(productVO, insuranceVO);
	}

	public List<Injury> getAll() {
		List<Injury> injuries = new ArrayList<Injury>();
		injuryMapper.getAll()
			.forEach(e -> injuries.add(getById(e.getProduct_id())));
		return injuries;
	}

	public Integer getMaxId() {
		return injuryMapper.getMaxId();
	}

	public void add(Injury injury) {
		// 참조 무결성 제약조건 생각해서 product, insurance, injury 순서대로 데이터 추가함
		if (injury == null) return;
		if (injuryMapper.getById(injury.getId()).isPresent()) return;
		productMapper.insert(injury.findProductVO());
		insuranceMapper.insert(injury.findInsuranceVO());
		injuryMapper.insert(injury.findVO());
	}

	public void update(Injury injury) {
		if (injury == null) return;
		if (injuryMapper.getById(injury.getId()).isEmpty()) return;
		injuryMapper.update(injury.findVO());
		insuranceMapper.update(injury.findInsuranceVO());
		productMapper.update(injury.findProductVO());
	}

	public void delete(int id) {
		// 참조 무결성 제약조건 생각해서 injury, insurnace, product 순서대로 데이터 삭제
		if (injuryMapper.getById(id).isEmpty()) return;
		injuryMapper.delete(id);
		insuranceMapper.delete(id);
		productMapper.deleteById(id);
	}
}
