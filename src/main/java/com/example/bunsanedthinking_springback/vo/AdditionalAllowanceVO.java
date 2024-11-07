package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.paymentDetail.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalAllowanceVO {
    private int payment_detail_id;
    private int type;

    public AdditionalAllowance getEntity(PaymentDetailVO paymentDetailVO) {
        AdditionalAllowance additionalAllowance = new AdditionalAllowance();
        additionalAllowance.setType(AdditionalAllowanceType.values()[type]);
        additionalAllowance.setId(paymentDetailVO.getId());
        additionalAllowance.setProcessStatus(PaymentProcessStatus.values()[paymentDetailVO.getProcess_status()]);
        additionalAllowance.setAccountHolder(paymentDetailVO.getAccount_holder());
        additionalAllowance.setBank(paymentDetailVO.getBank());
        additionalAllowance.setBankAccount(paymentDetailVO.getBank_account());
        additionalAllowance.setMoney(paymentDetailVO.getMoney());
        additionalAllowance.setPaymentType(PaymentType.values()[paymentDetailVO.getPayment_type()]);
        additionalAllowance.setContractId(paymentDetailVO.getContract_id());
        additionalAllowance.setEmployeeId(paymentDetailVO.getEmployee_id());
        return additionalAllowance;
    }
}
