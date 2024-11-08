package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedDepositVO {
	private int product_id;
	private int minimum_amount;

	public static FixedDepositVO from(FixedDeposit fixedDeposit) {
		return new FixedDepositVO(fixedDeposit.getId(), fixedDeposit.getMinimumAmount());
	}
}
