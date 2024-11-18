package com.example.bunsanedthinking_springback.dto.employee.compensation.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompensationRequest {
    private String accountHolder;
    private String bank;
    private String bankAccount;
    private int money;
    private int paymentType;
    private int contractId;
    private int reportId;
}
