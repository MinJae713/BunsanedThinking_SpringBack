package com.example.bunsanedthinking_springback.dto.customer.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class DepositRequest {
    private int contractId;
    @Positive(message = "금액은 양수여야 합니다")
    private int money;
    @Range(min = 0, max = 2, message = "납입 경로를 다시 지정해주세요")
    private int depositPath;
}
