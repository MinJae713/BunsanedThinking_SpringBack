package com.example.bunsanedthinking_springback.dto.customer.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.CustomerDTOConstants;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class DepositRequest {
    private int contractId;
    @Positive(message = CustomerDTOConstants.CONTRACT_ID_MESSAGE)
    private int money;
    @Range(min = 0, max = 2, message = CustomerDTOConstants.MONEY_MESSAGE)
    private int depositPath;
}
