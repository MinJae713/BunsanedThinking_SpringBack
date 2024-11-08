package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollateralVO {
    private int product_id;
    private int collateral_type;
    private int minimum_value;

    public Collateral getEntity(ProductVO productVO, LoanVO loanVO) {
        Collateral collateral = new Collateral();
        collateral.setCollateralType(CollateralType.values()[collateral_type]);
        collateral.setMinimumValue(minimum_value);

        collateral.setInterestRate(loanVO.getInterest_rate());
        collateral.setLoanType(LoanType.values()[loanVO.getLoan_type()]);
        collateral.setMinimumAsset(loanVO.getMinimum_asset());
        collateral.setMonthlyIncome(loanVO.getMonthly_income());

        collateral.setId(productVO.getId());
        collateral.setMaximumMoney(productVO.getMaximum_money());
        collateral.setName(productVO.getName());
        return collateral;
    }
}
