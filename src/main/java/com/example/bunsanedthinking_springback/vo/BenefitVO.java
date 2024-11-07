package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.paymentDetail.Benefit;
import com.example.bunsanedthinking_springback.entity.paymentDetail.BenefitType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitVO {
    private int payment_detail_id;
    private int benefit_type;

    public Benefit getEntity(PaymentDetailVO paymentDetailVO) {
        Benefit benefit = new Benefit();
        benefit.setType(BenefitType.values()[benefit_type]);
        benefit.setId(paymentDetailVO.getId());
        benefit.setProcessStatus(PaymentProcessStatus.values()[paymentDetailVO.getProcess_status()]);
        benefit.setAccountHolder(paymentDetailVO.getAccount_holder());
        benefit.setBank(paymentDetailVO.getBank());
        benefit.setBankAccount(paymentDetailVO.getBank_account());
        benefit.setMoney(paymentDetailVO.getMoney());
        benefit.setPaymentType(PaymentType.values()[paymentDetailVO.getPayment_type()]);
        benefit.setContractId(paymentDetailVO.getContract_id());
        benefit.setEmployeeId(paymentDetailVO.getEmployee_id());
        return benefit;
    }
}
