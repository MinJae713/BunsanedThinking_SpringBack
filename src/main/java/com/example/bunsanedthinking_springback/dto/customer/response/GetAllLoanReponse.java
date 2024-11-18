package com.example.bunsanedthinking_springback.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllLoanReponse {
    private String name;
    private String loanType;
    private int id;
    private int interestRate;
    private int maximumMoney;
}
