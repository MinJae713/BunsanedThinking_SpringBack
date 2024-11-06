package com.example.bunsanedthinking_springback.dto.compensation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReqCompensationDTO {
    private String accountHolder;
    private String bank;
    private String bankAccount;
    private int money;
    private int paymentType;
    private int contractId;
    private int reportId;
}
