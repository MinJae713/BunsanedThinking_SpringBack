package com.example.bunsanedthinking_springback.dto.employee.sales.response;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InduceLoanProductResponse {
	private String name;
	private LoanType loanType;
	private Integer id;
	private Integer interestRate;
	private Integer maximumMoney;

	public static InduceLoanProductResponse from(Loan loan){
		return InduceLoanProductResponse.builder()
			.name(loan.getName())
			.loanType(loan.getLoanType())
			.id(loan.getId())
			.interestRate(loan.getInterestRate())
			.maximumMoney(loan.getMaximumMoney()).build();
	}

}
