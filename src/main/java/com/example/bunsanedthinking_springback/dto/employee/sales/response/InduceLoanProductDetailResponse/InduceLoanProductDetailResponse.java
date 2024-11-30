package com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class InduceLoanProductDetailResponse {
	protected Integer id;
	protected String name;
	protected String loanType;
	protected Integer interestRate;
	protected Integer maximumMoney;
	protected Integer minimumAsset;
}