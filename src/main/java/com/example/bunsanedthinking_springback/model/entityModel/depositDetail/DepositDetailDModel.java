package com.example.bunsanedthinking_springback.model.entityModel.depositDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;

@Service
public class DepositDetailDModel {
	@Autowired
	private DepositDetailMapper depositDetailMapper;

	public DepositDetail getById(int id) {
		return depositDetailMapper.getById(id)
			.map(DepositDetailVO::getEntity)
			.orElse(null);
	}

	public List<DepositDetail> getAll() {
		List<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
		depositDetailMapper.getAll()
			.forEach(e -> depositDetails.add(getById(e.getId())));
		return depositDetails;
	}

	public Integer getMaxId() {
		return depositDetailMapper.getMaxId();
	}

	public void add(DepositDetail depositDetail) {
		depositDetailMapper.insert(depositDetail.findVO());
	}

	public void update(DepositDetail depositDetail) {
		depositDetailMapper.update(depositDetail.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null)
			return;
		depositDetailMapper.deleteById(id);
	}
}
