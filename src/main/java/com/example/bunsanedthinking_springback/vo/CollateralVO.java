package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;

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

	public static CollateralVO from(Collateral collateral) {
		return new CollateralVO(collateral.getId(), collateral.getCollateralType().ordinal(),
			collateral.getMinimumValue());
	}
}
