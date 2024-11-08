package com.example.bunsanedthinking_springback.model.domain.depositDetail;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositDetailDModel {
	@Autowired
	private DepositDetailMapper depositDetailMapper;

	public DepositDetail getById(int id) {
		return depositDetailMapper.findById_FinancialAccountant(id)
			.map(DepositDetailVO::getEntity)
			.orElse(null);
	}

	public List<DepositDetail> getAll() {
		List<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
		depositDetailMapper.getAll_ContractManagement()
			.forEach(e -> depositDetails.add(getById(e.getId())));
		return depositDetails;
	}

	public Integer getMaxId() {
		return depositDetailMapper.getLastId_Customer();
	}

	public void add(DepositDetail depositDetail) {
		depositDetailMapper.add_Customer(depositDetail.findVO());
	}

	public void update(DepositDetail depositDetail) {
		depositDetailMapper.update(depositDetail.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		depositDetailMapper.deleteById(id);
	}
}
