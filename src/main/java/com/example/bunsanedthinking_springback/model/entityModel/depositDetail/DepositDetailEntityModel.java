package com.example.bunsanedthinking_springback.model.entityModel.depositDetail;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositDetailEntityModel {
	@Autowired
	private DepositDetailMapper depositDetailMapper;
	@ReadOnly
	public DepositDetail getById(int id) {
		return depositDetailMapper.getById(id)
			.map(DepositDetailVO::getEntity)
			.orElse(null);
	}
	@ReadOnly
	public List<DepositDetail> getAll() {
		List<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
		depositDetailMapper.getAll()
			.forEach(e -> depositDetails.add(getById(e.getId())));
		return depositDetails;
	}
	@ReadOnly
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
