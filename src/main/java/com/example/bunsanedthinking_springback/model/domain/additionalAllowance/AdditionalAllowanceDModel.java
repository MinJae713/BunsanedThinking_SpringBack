package com.example.bunsanedthinking_springback.model.domain.additionalAllowance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.repository.AdditionalAllowanceMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;

@Service
public class AdditionalAllowanceDModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private AdditionalAllowanceMapper additionalAllowanceMapper;

	public AdditionalAllowance getById(int id) {
		return paymentDetailMapper.findById_FinancialAccountant(id)
			.flatMap(detailVO ->
				additionalAllowanceMapper.getById(id)
					.map(additionalAllowanceVO -> additionalAllowanceVO.getEntity(detailVO)))
			.orElse(null);
	}

	public List<AdditionalAllowance> getAll() {
		List<AdditionalAllowance> additionalAllowances = new ArrayList<AdditionalAllowance>();
		List<AdditionalAllowanceVO> additionalAllowanceVOS = additionalAllowanceMapper.getAll();
		additionalAllowanceVOS.forEach(e -> additionalAllowances.add(getById(e.getPayment_detail_id())));
		return additionalAllowances;
	}

	public Integer getMaxId() {
		return additionalAllowanceMapper.getMaxId();
	}

	public void add(AdditionalAllowanceVO additionalAllowanceVO) {
		additionalAllowanceMapper.insert(additionalAllowanceVO);
	}

	public void update(AdditionalAllowanceVO additionalAllowanceVO) {
		additionalAllowanceMapper.update(additionalAllowanceVO);
	}

	public void delete(int id) {
		additionalAllowanceMapper.deleteById(id);
	}
}
