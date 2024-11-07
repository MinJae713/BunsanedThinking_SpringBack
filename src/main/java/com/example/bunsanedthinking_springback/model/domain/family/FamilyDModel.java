package com.example.bunsanedthinking_springback.model.domain.family;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.repository.FamilyMapper;
import com.example.bunsanedthinking_springback.vo.FamilyVO;

@Service
public class FamilyDModel {
	@Autowired
	private FamilyMapper familyMapper;

	public Family getById(int id) {
		return familyMapper.getById(id)
			.map(FamilyVO::getEntity)
			.orElse(null);
	}

	public List<Family> getAll() {
		List<Family> families = new ArrayList<Family>();
		familyMapper.getAll()
			.forEach(e -> families.add(e.getEntity()));
		return families;
	}

	public int getMaxId() {
		return familyMapper.getMaxId_HumanResource();
	}

	public void add(FamilyVO familyVO) {
		familyMapper.insert_HumanResource(familyVO);
	}

	public void update(FamilyVO familyVO) {
		familyMapper.update(familyVO);
	}

	public void delete(int id) {
		familyMapper.delete(id);
	}
}
