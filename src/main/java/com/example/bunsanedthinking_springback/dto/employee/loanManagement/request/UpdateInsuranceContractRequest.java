package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateInsuranceContractRequest implements UpdateLoanRequest {
	private int id;

	@Positive(message = "대출 가능 최대 금액 0보다 작습니다.")
	private int maximumMoney;

	@Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;

	@Positive(message = "이자율이 0보다 작습니다.")
	private int interestRate;

	private LoanType loanType;

	@Positive(message = "최소 자산이 0보다 작습니다.")
	private int minimumAsset;

	@Positive(message = "월 수익이 0보다 작습니다.")
	private int monthlyIncome;

	@Positive(message = "잘못된 보험 상품 번호가 입력되었습니다.")
	private int insuranceId;

	public Loan toEntity() {
		return new InsuranceContract(id, loanType, name, interestRate, maximumMoney, minimumAsset, insuranceId,
			monthlyIncome);
	}
}
