package com.example.bunsanedthinking_springback.model.entityModel.family;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.FamilyMapper;
import com.example.bunsanedthinking_springback.vo.FamilyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyEntityModel {
	@Autowired
	private FamilyMapper familyMapper;
	@ReadOnly
	public Family getById(int id) {
		return familyMapper.getById(id)
			.map(FamilyVO::getEntity)
			.orElse(null);
	}
	@ReadOnly
	public List<Family> getAll() {
		List<Family> families = new ArrayList<Family>();
		familyMapper.getAll()
			.forEach(e -> families.add(e.getEntity()));
		return families;
	}
	@ReadOnly
	public Integer getMaxId() {
		return familyMapper.getMaxId();
	}

	public void add(Family family) {
		familyMapper.insert(family.findVO());
	}

	public void update(Family family) {
		familyMapper.update(family.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		familyMapper.deleteById(id);
	}
}
