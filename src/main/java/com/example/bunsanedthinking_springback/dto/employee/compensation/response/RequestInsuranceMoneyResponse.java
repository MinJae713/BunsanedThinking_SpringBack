package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInsuranceMoneyResponse {
    private int id;
    private String productType;
    private String date;
    private String customerName;
    private String status;

    public static RequestInsuranceMoneyResponse of(
            InsuranceMoney insuranceMoney, Product product, Customer customer) {
        int id = insuranceMoney.getId();
        String productType = product.getName();
        Date date = insuranceMoney.getApplyDate();
        String customerName = customer.getName();
        String status = insuranceMoney.getProcessStatus().getName();
        return new RequestInsuranceMoneyResponse(id, productType, DateUtils.toString(date), customerName, status);
    }
}
