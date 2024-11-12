package com.example.bunsanedthinking_springback.model.entityModel.paymentDetail;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.entityModel.additionalAllowance.AdditionalAllowanceDModel;
import com.example.bunsanedthinking_springback.model.entityModel.benefit.BenefitDModel;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDetailEntityModel {
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private AdditionalAllowanceDModel additionalAllowanceDModel;
	@Autowired
	private BenefitDModel benefitDModel;

	public PaymentDetail getById(int id) {
		PaymentDetail paymentDetail = additionalAllowanceDModel.getById(id);
		if (paymentDetail != null)
			return paymentDetail;
		paymentDetail = benefitDModel.getById(id);
		if (paymentDetail != null)
			return paymentDetail;
		return paymentDetailMapper.findById_FinancialAccountant(id)
			.map(PaymentDetailVO::getEntity)
			.orElse(null);
	}

	public List<PaymentDetail> getAll() {
		List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		paymentDetailMapper.getAll_FinancialAccountant()
			.forEach(e -> paymentDetails.add(getById(e.getId())));
		return paymentDetails;
	}

	public Integer getMaxId() {
		return paymentDetailMapper.getLastId_Compensation();
	}

	public void add(PaymentDetail paymentDetail) {
		if (paymentDetail == null) return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceDModel.add((AdditionalAllowance) paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitDModel.add((Benefit) paymentDetail);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(paymentDetail.getId()).isPresent()) return;
			paymentDetailMapper.insert_LoanManagement(paymentDetail.findPaymentDetailVO());
		}
	}

	public void update(PaymentDetail paymentDetail) {
		if (paymentDetail == null) return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceDModel.update((AdditionalAllowance) paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitDModel.update((Benefit) paymentDetail);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(paymentDetail.getId()).isEmpty()) return;
			paymentDetailMapper.update_FinancialAccountant(paymentDetail.findPaymentDetailVO());
		}
	}

	public void delete(int id) {
		PaymentDetail paymentDetail = getById(id);
		if (paymentDetail == null) return;
		else if (paymentDetail instanceof AdditionalAllowance) additionalAllowanceDModel.delete(id);
		else if (paymentDetail instanceof Benefit) benefitDModel.delete(id);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(id).isEmpty()) return;
			paymentDetailMapper.deleteById(id);
		}
	}
}
