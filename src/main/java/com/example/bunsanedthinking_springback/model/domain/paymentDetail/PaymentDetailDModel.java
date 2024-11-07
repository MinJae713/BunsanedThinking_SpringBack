package com.example.bunsanedthinking_springback.model.domain.paymentDetail;

import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.domain.additionalAllowance.AdditionalAllowanceDModel;
import com.example.bunsanedthinking_springback.model.domain.benefit.BenefitDModel;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDetailDModel {
    @Autowired
    private PaymentDetailMapper paymentDetailMapper;
    @Autowired
    private AdditionalAllowanceDModel additionalAllowanceDModel;
    @Autowired
    private BenefitDModel benefitDModel;
    public PaymentDetail getById(int id) {
        PaymentDetail paymentDetail = additionalAllowanceDModel.getById(id);
        if (paymentDetail != null) return paymentDetail;
        paymentDetail = benefitDModel.getById(id);
        if (paymentDetail != null) return paymentDetail;
        return paymentDetailMapper.findById_FinancialAccountant(id).orElse(null).getEntity();
    }
    public List<PaymentDetail> getAll() {
        List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
        paymentDetailMapper.getAll_FinancialAccountant().stream().forEach(e -> paymentDetails.add(getById(e.getId())));
        return paymentDetails;
    }
    public int getMaxId() {
        return paymentDetailMapper.getLastId_Compensation();
    }
    public void add(PaymentDetailVO paymentDetailVO) {
        paymentDetailMapper.insert_LoanManagement(paymentDetailVO);
    }
    public void update(PaymentDetailVO paymentDetailVO) {
        paymentDetailMapper.update_FinancialAccountant(paymentDetailVO);
    }
    public void delete(int id) {
        paymentDetailMapper.deleteById(id);
    }
}
