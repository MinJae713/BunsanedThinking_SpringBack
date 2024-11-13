package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollateralVO {
    private int product_id;
    private int collateral_type;
    private int minimum_value;

    public Collateral getEntity(ProductVO productVO, LoanVO loanVO, List<Contract> contracts, List<Counsel> counsels) {
        Collateral collateral = new Collateral();
        collateral.setCollateralType(CollateralType.values()[collateral_type]);
        collateral.setMinimumValue(minimum_value);

        collateral.setInterestRate(loanVO.getInterest_rate());
        collateral.setLoanType(LoanType.values()[loanVO.getLoan_type()]);
        collateral.setMinimumAsset(loanVO.getMinimum_asset());
        collateral.setMonthlyIncome(loanVO.getMonthly_income());

        collateral.setId(product_id);
        collateral.setMaximumMoney(productVO.getMaximum_money());
        collateral.setName(productVO.getName());

        collateral.setContractList(contracts);
        collateral.setCounselList(counsels);
        return collateral;
    }

	public static CollateralVO from(Collateral collateral) {
		return new CollateralVO(collateral.getId(), collateral.getCollateralType().ordinal(),
			collateral.getMinimumValue());
	}
}
