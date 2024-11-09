package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanVO {
	private int product_id;
	private int loan_type;
	private int minimum_asset;
	private int monthly_income;
	private int interest_rate;

	// 추상타입이라 Entity 생성 불가
	public static LoanVO from(Loan loan) {
		return new LoanVO(loan.getId(), loan.getLoanType().ordinal(), loan.getMinimumAsset(),
			loan.getMonthlyIncome(), loan.getInterestRate());
	}
}
