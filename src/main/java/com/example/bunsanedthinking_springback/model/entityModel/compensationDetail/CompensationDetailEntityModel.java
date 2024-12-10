package com.example.bunsanedthinking_springback.model.entityModel.compensationDetail;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.CompensationDetailMapper;
import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompensationDetailEntityModel {
	@Autowired
	private CompensationDetailMapper compensationDetailMapper;
	@ReadOnly
	public CompensationDetail getById(int id) {
		return compensationDetailMapper.getById(id)
			.map(CompensationDetailVO::getEntity)
			.orElse(null);
	}
	@ReadOnly
	public List<CompensationDetail> getAll() {
		List<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
		compensationDetailMapper.getAll()
			.forEach(e -> compensationDetails.add(getById(e.getId())));
		return compensationDetails;
	}
	@ReadOnly
	public Integer getMaxId() {
		return compensationDetailMapper.getMaxId();
	}

	public void add(CompensationDetail compensationDetail) {
		compensationDetailMapper.insert(compensationDetail.findVO());
	}

	public void update(CompensationDetail compensationDetail) {
		compensationDetailMapper.update(compensationDetail.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		compensationDetailMapper.deleteById(id);
	}
}
