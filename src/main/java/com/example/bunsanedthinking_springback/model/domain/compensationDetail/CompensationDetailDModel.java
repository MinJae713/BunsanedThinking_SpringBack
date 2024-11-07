package com.example.bunsanedthinking_springback.model.domain.compensationDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.repository.CompensationDetailMapper;
import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;

@Service
public class CompensationDetailDModel {
	@Autowired
	private CompensationDetailMapper compensationDetailMapper;

	public CompensationDetail getById(int id) {
		return compensationDetailMapper.getById(id)
			.map(CompensationDetailVO::getEntity)
			.orElse(null);
	}

	public List<CompensationDetail> getAll() {
		List<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
		compensationDetailMapper.getAll_UnderwritingModel()
			.forEach(e -> compensationDetails.add(e.getEntity()));
		return compensationDetails;
	}

	public Integer getMaxId() {
		return compensationDetailMapper.getMaxId_LoanManagement();
	}

	public void add(CompensationDetailVO compensationDetailVO) {
		compensationDetailMapper.insert_LoanManagement(compensationDetailVO);
	}

	public void update(CompensationDetailVO compensationDetailVO) {
		compensationDetailMapper.update(compensationDetailVO);
	}

	public void delete(int id) {
		compensationDetailMapper.deleteById(id);
	}
}
