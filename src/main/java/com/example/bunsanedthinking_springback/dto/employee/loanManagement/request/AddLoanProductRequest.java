package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddLoanProductRequest {
	private LoanType loanType;

	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;

	@Positive(message = "이자율이 0보다 작습니다.")
	private int interestRate;

	@Positive(message = "대출 가능 최대 금액 0보다 작습니다.")
	private int maximumMoney;

	@Positive(message = "최소 자산이 0보다 작습니다.")
	private int minimumAsset;

	private int parameter;

	@Positive(message = "월 수익이 0보다 작습니다.")
	private int monthlyIncome;
}
