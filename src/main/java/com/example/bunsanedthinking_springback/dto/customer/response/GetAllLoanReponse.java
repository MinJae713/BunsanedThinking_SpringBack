package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
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

    public static GetAllLoanReponse of(Loan loan) {
        String name = loan.getName();
        String loanType = loan.getLoanType().getName();
        int id = loan.getId();
        int interestRate = loan.getInterestRate();
        int maximumMoney = loan.getMaximumMoney();
        return new GetAllLoanReponse(name, loanType,
                id, interestRate, maximumMoney);
    }
}
