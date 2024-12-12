package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
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
    private String medicalCertificate;
    private String receipt;
    private String residentRegistrationCard;

    public static RequestInsuranceMoneyDetailResponse of
            (InsuranceMoney insuranceMoney, Insurance insurance, Customer customer) throws IOException {
        int id = insuranceMoney.getId();
        String productType = insurance.getInsuranceType().getName();
        Date applyDate = insuranceMoney.getApplyDate();
        String customerName = customer.getName();
        String processStatus = insuranceMoney.getProcessStatus().getName();
        String medicalCertificate = insuranceMoney.getMedicalCertificate();
        String receipt = insuranceMoney.getReceipt();
        String residentRegistrationCard = insuranceMoney.getResidentRegistrationCard();
        return new RequestInsuranceMoneyDetailResponse(id, productType, DateUtils.toString(applyDate),
                customerName, processStatus, medicalCertificate, receipt, residentRegistrationCard);
    }
}
