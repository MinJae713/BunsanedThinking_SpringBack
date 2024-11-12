package com.example.bunsanedthinking_springback.model.entityModel.benefit;

import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.repository.BenefitMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.BenefitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenefitDModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private BenefitMapper benefitMapper;

	public Benefit getById(int id) {
		return paymentDetailMapper.getById(id)
			.flatMap(detailVO -> benefitMapper.getById(id)
				.map(benefitVO -> benefitVO.getEntity(detailVO)))
			.orElse(null);
	}

	public List<Benefit> getAll() {
		List<Benefit> benefits = new ArrayList<Benefit>();
		List<BenefitVO> benefitVOS = benefitMapper.getAll();
		benefitVOS.forEach(e -> benefits.add(getById(e.getPayment_detail_id())));
		return benefits;
	}

	public Integer getMaxId() {
		return benefitMapper.getMaxId();
	}

	public void add(Benefit benefit) {
		if (benefit == null) return;
		if (benefitMapper.getById(benefit.getId()).isPresent()) return;
		paymentDetailMapper.insert(benefit.findPaymentDetailVO());
		benefitMapper.insert(benefit.findVO());
	}

	public void update(Benefit benefit) {
		if (benefit == null) return;
		if (benefitMapper.getById(benefit.getId()).isEmpty()) return;
		benefitMapper.update(benefit.findVO());
		paymentDetailMapper.update(benefit.findPaymentDetailVO());
	}

	public void delete(int id) {
		if (benefitMapper.getById(id).isEmpty()) return;
		benefitMapper.deleteById(id);
		paymentDetailMapper.deleteById(id);
	}
}
