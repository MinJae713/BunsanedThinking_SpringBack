package com.example.bunsanedthinking_springback.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyInsuranceDTO {
    private int insuranceId;
    private int customerId;
    private Integer employeeId;
}
