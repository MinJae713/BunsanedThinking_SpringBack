package com.example.bunsanedthinking_springback.model.domain.additionalAllowance;

import com.example.bunsanedthinking_springback.entity.paymentDetail.AdditionalAllowance;
import com.example.bunsanedthinking_springback.repository.AdditionalAllowanceMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalAllowanceDModel {
    @Autowired
    private PaymentDetailMapper paymentDetailMapper;
    @Autowired
    private AdditionalAllowanceMapper additionalAllowanceMapper;
    public AdditionalAllowance getById(int id) {
        AdditionalAllowanceVO additionalAllowanceVO = additionalAllowanceMapper.getById(id).orElse(null);
        PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(id).orElse(null);
        return additionalAllowanceVO.getEntity(paymentDetailVO);
    }
    public List<AdditionalAllowance> getAll() {
        List<AdditionalAllowance> additionalAllowances = new ArrayList<AdditionalAllowance>();
        List<AdditionalAllowanceVO> additionalAllowanceVOS = additionalAllowanceMapper.getAll();
        additionalAllowanceVOS.stream().forEach(e -> additionalAllowances.add(getById(e.getPayment_detail_id())));
        return additionalAllowances;
    }
    public int getMaxId() {
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
