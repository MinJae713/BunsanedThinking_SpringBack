package com.example.bunsanedthinking_springback.dto.employee.compensation.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsuranceMoneyRequest {
//    private int customerId;
    private int money;
    private int insuranceMoneyId;
    private int paymentType;
//    private int contractId;
}
