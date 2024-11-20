package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanListReponse {
    private String name;
    private String loanType;
    private int id;
    private int interestRate;
    private int maximumMoney;

    public static LoanListReponse of(Loan loan) {
        String name = loan.getName();
        String loanType = loan.getLoanType().getName();
        int id = loan.getId();
        int interestRate = loan.getInterestRate();
        int maximumMoney = loan.getMaximumMoney();
        return new LoanListReponse(name, loanType,
                id, interestRate, maximumMoney);
    }
}
