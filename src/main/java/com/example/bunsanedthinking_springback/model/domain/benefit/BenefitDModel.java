package com.example.bunsanedthinking_springback.model.domain.benefit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.repository.BenefitMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.BenefitVO;

@Service
public class BenefitDModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private BenefitMapper benefitMapper;

	public Benefit getById(int id) {
		return paymentDetailMapper.findById_FinancialAccountant(id)
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

	public int getMaxId() {
		return benefitMapper.getMaxId();
	}

	public void add(BenefitVO benefitVO) {
		benefitMapper.insert(benefitVO);
	}

	public void update(BenefitVO benefitVO) {
		benefitMapper.update(benefitVO);
	}

	public void delete(int id) {
		benefitMapper.deleteById(id);
	}
}
