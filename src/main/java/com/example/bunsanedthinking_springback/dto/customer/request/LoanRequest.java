package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanRequest {
    private int loanId;
    private int customerId;
//    private Integer employeeId;
}
