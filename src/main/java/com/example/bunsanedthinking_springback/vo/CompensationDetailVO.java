package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompensationDetailVO {
    private int id;
    private int money;
    private LocalDate payment_date;
    private int contract_id;

    public CompensationDetail getCompensationDetail() {
        CompensationDetail compensationDetail = new CompensationDetail();
        compensationDetail.setId(id);
        compensationDetail.setContractID(contract_id);
        compensationDetail.setMoney(money);
        compensationDetail.setPaymentDate(Date.valueOf(payment_date));
        return compensationDetail;
    }
}
