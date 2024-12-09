package com.example.bunsanedthinking_springback.model.entityModel.paymentDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.additionalAllowance.AdditionalAllowanceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.benefit.BenefitEntityModel;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

@Service
public class PaymentDetailEntityModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private AdditionalAllowanceEntityModel additionalAllowanceEntityModel;
	@Autowired
	private BenefitEntityModel benefitEntityModel;

	@ReadOnly
	public PaymentDetail getById(int id) {
		PaymentDetail paymentDetail = additionalAllowanceEntityModel.getById(id);
		if (paymentDetail != null)
			return paymentDetail;
		paymentDetail = benefitEntityModel.getById(id);
		if (paymentDetail != null)
			return paymentDetail;
		return paymentDetailMapper.getById(id)
			.map(PaymentDetailVO::getEntity)
			.orElse(null);
	}

	public List<PaymentDetail> getAll() {
		List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		paymentDetailMapper.getAll()
			.forEach(e -> paymentDetails.add(getById(e.getId())));
		return paymentDetails;
	}

	public Integer getMaxId() {
		return paymentDetailMapper.getMaxId();
	}

	public void add(PaymentDetail paymentDetail) {
		if (paymentDetail == null)
			return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceEntityModel.add((AdditionalAllowance)paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitEntityModel.add((Benefit)paymentDetail);
		else {
			if (paymentDetailMapper.getById(paymentDetail.getId()).isPresent())
				return;
			paymentDetailMapper.insert(paymentDetail.findPaymentDetailVO());
		}
	}

	public void update(PaymentDetail paymentDetail) {
		if (paymentDetail == null)
			return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceEntityModel.update((AdditionalAllowance)paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitEntityModel.update((Benefit)paymentDetail);
		else {
			if (paymentDetailMapper.getById(paymentDetail.getId()).isEmpty())
				return;
			paymentDetailMapper.update(paymentDetail.findPaymentDetailVO());
		}
	}

	public void delete(int id) {
		PaymentDetail paymentDetail = getById(id);
		if (paymentDetail == null)
			return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceEntityModel.delete(id);
		else if (paymentDetail instanceof Benefit)
			benefitEntityModel.delete(id);
		else {
			if (paymentDetailMapper.getById(id).isEmpty())
				return;
			paymentDetailMapper.deleteById(id);
		}
	}
}
