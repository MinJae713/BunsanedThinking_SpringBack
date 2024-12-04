package com.example.bunsanedthinking_springback.dto.employee.compensation.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.CompensationDTOConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class InsuranceMoneyRequest {
    @Min(value = 9300, message = CompensationDTOConstants.INSURANCE_MONEY_ID_MIN_MESSAGE)
    private int insuranceMoneyId;
    @Positive(message = CompensationDTOConstants.MONEY_POSITIVE_MESSAGE)
    private int money;
    @Range(min = 0, max = 1, message = CompensationDTOConstants.PAYMENT_TYPE_RANGE_MESSAGE)
    private int paymentType;
}
