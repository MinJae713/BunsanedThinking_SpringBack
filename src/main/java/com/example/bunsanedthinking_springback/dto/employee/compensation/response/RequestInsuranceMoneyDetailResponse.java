package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInsuranceMoneyDetailResponse {
    private int id;
    private String productType;
    private String applyDate;
    private String customerName;
    private String processStatus;
    private Image medicalCertificate;
    private Image receipt;
    private Image residentRegistrationCard;

    public static RequestInsuranceMoneyDetailResponse of
            (InsuranceMoney insuranceMoney, Insurance insurance, Customer customer) {
        int id = insuranceMoney.getId();
        String productType = insurance.getInsuranceType().getName();
        Date applyDate = insuranceMoney.getApplyDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String customerName = customer.getName();
        String processStatus = insuranceMoney.getProcessStatus().getName();
        Image medicalCertificate = insuranceMoney.getMedicalCertificate();
        Image receipt = insuranceMoney.getReceipt();
        Image residentRegistrationCard = insuranceMoney.getResidentRegistrationCard();
        return new RequestInsuranceMoneyDetailResponse(id, productType, formatter.format(applyDate),
                customerName, processStatus, medicalCertificate, receipt, residentRegistrationCard);
    }
}
