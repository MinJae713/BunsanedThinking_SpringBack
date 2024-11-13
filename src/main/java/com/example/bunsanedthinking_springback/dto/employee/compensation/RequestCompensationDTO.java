package com.example.bunsanedthinking_springback.dto.employee.compensation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCompensationDTO {
    private String accountHolder;
    private String bank;
    private String bankAccount;
    private int money;
    private int paymentType;
    private int contractId;
    private int reportId;
}
