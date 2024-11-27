package com.example.bunsanedthinking_springback.dto.customer.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositRequest {
//    private String depositorName;
    private int contractId;
    @Positive(message = "금액은 양수여야 합니다")
    private int money;
    private int depositPath;
}
