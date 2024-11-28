package com.example.bunsanedthinking_springback.dto.employee.compensation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class InsuranceMoneyRequest {
    @Min(value = 9300, message = "유효한 아이디가 아닙니다")
    private int insuranceMoneyId;
    @Positive(message = "금액은 양수로 입력해주세요")
    private int money;
    @Range(min = 0, max = 1, message = "현금 혹은 계좌이체중에서 선택해주세요")
    private int paymentType;
}
