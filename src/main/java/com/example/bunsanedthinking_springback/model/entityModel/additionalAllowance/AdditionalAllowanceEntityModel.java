package com.example.bunsanedthinking_springback.model.entityModel.additionalAllowance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.AdditionalAllowanceMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;

@Service
public class AdditionalAllowanceEntityModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private AdditionalAllowanceMapper additionalAllowanceMapper;

	@ReadOnly
	public AdditionalAllowance getById(int id) {
		return paymentDetailMapper.getById(id)
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

	public void add(AdditionalAllowance additionalAllowance) {
		if (additionalAllowance == null)
			return;
		if (additionalAllowanceMapper.getById(additionalAllowance.getId()).isPresent())
			return;
		paymentDetailMapper.insert(additionalAllowance.findPaymentDetailVO());
		additionalAllowanceMapper.insert(additionalAllowance.findVO());
	}

	public void update(AdditionalAllowance additionalAllowance) {
		if (additionalAllowance == null)
			return;
		if (additionalAllowanceMapper.getById(additionalAllowance.getId()).isEmpty())
			return;
		additionalAllowanceMapper.update(additionalAllowance.findVO());
		paymentDetailMapper.update(additionalAllowance.findPaymentDetailVO());
	}

	public void delete(int id) {
		if (additionalAllowanceMapper.getById(id).isEmpty())
			return;
		additionalAllowanceMapper.deleteById(id);
		paymentDetailMapper.deleteById(id);
	}
}
