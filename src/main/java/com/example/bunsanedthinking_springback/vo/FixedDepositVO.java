package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedDepositVO {
    private int product_id;
    private int minimum_amount;

    public FixedDeposit getEntity(ProductVO productVO, LoanVO loanVO, List<Contract> contracts) {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setMinimumAmount(minimum_amount);

        fixedDeposit.setInterestRate(loanVO.getInterest_rate());
        fixedDeposit.setLoanType(LoanType.values()[loanVO.getLoan_type()]);
        fixedDeposit.setMinimumAsset(loanVO.getMinimum_asset());
        fixedDeposit.setMonthlyIncome(loanVO.getMonthly_income());

        fixedDeposit.setId(productVO.getId());
        fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
        fixedDeposit.setName(productVO.getName());

        fixedDeposit.setContractList(contracts);
        return fixedDeposit;
    }
	

	public static FixedDepositVO from(FixedDeposit fixedDeposit) {
		return new FixedDepositVO(fixedDeposit.getId(), fixedDeposit.getMinimumAmount());
	}
}
