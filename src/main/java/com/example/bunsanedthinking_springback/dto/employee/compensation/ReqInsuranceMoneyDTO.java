package com.example.bunsanedthinking_springback.dto.employee.compensation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReqInsuranceMoneyDTO {
    private int customerId;
    private int money;
    private int insuranceMoneyId;
    private int paymentType;
    private int contractId;
}
