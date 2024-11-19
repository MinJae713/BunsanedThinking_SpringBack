package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInsuranceMoneyResponse {
    private int id;
    private String productType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String customerName;
    private String status;

    public static GetAllInsuranceMoneyResponse of(
            InsuranceMoney insuranceMoney, Product product, Customer customer) {
        int id = insuranceMoney.getId();
        String productType = product.getName();
        Date date = insuranceMoney.getApplyDate();
        String customerName = customer.getName();
        String status = insuranceMoney.getProcessStatus().getName();
        return new GetAllInsuranceMoneyResponse(id, productType, date, customerName, status);
    }
}
