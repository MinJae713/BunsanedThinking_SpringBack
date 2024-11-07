package com.example.bunsanedthinking_springback.model.domain.benefit;

import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.repository.BenefitMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.BenefitVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
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
        BenefitVO benefitVO = benefitMapper.getById(id).orElse(null);
        PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(id).orElse(null);
        return benefitVO.getEntity(paymentDetailVO);
    }
    public List<Benefit> getAll() {
        List<Benefit> benefits = new ArrayList<Benefit>();
        List<BenefitVO> benefitVOS = benefitMapper.getAll();
        benefitVOS.stream().forEach(e -> benefits.add(getById(e.getPayment_detail_id())));
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
