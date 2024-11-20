package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositDTO {
    private String depositorName;
    private int contractId;
    private int money;
    private int depositPath;
}