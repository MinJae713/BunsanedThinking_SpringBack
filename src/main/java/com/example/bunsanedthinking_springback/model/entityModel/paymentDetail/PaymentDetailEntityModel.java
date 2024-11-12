package com.example.bunsanedthinking_springback.model.entityModel.paymentDetail;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.entityModel.additionalAllowance.AdditionalAllowanceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.benefit.BenefitEntityModel;
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
	private AdditionalAllowanceEntityModel additionalAllowanceEntityModel;
	@Autowired
	private BenefitEntityModel benefitEntityModel;

	public PaymentDetail getById(int id) {
		PaymentDetail paymentDetail = additionalAllowanceEntityModel.getById(id);
		if (paymentDetail != null)
			return paymentDetail;
		paymentDetail = benefitEntityModel.getById(id);
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
			additionalAllowanceEntityModel.add((AdditionalAllowance) paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitEntityModel.add((Benefit) paymentDetail);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(paymentDetail.getId()).isPresent()) return;
			paymentDetailMapper.insert_LoanManagement(paymentDetail.findPaymentDetailVO());
		}
	}

	public void update(PaymentDetail paymentDetail) {
		if (paymentDetail == null) return;
		else if (paymentDetail instanceof AdditionalAllowance)
			additionalAllowanceEntityModel.update((AdditionalAllowance) paymentDetail);
		else if (paymentDetail instanceof Benefit)
			benefitEntityModel.update((Benefit) paymentDetail);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(paymentDetail.getId()).isEmpty()) return;
			paymentDetailMapper.update_FinancialAccountant(paymentDetail.findPaymentDetailVO());
		}
	}

	public void delete(int id) {
		PaymentDetail paymentDetail = getById(id);
		if (paymentDetail == null) return;
		else if (paymentDetail instanceof AdditionalAllowance) additionalAllowanceEntityModel.delete(id);
		else if (paymentDetail instanceof Benefit) benefitEntityModel.delete(id);
		else {
			if (paymentDetailMapper.findById_FinancialAccountant(id).isEmpty()) return;
			paymentDetailMapper.deleteById(id);
		}
	}
}
