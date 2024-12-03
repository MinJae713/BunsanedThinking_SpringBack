package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCollateralLoanProductRequest {
	@NotNull(message = "대출 종류는 필수 입력 항목입니다.")
	private LoanType loanType;

	@Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;

	@Positive(message = "이자율이 0보다 작습니다.")
	private int interestRate;

	@Positive(message = "대출 가능 최대 금액 0보다 작습니다.")
	private int maximumMoney;

	@Positive(message = "최소 자산이 0보다 작습니다.")
	private int minimumAsset;

	@NotNull(message = "담보 종류는 필수 입력 항목입니다.")
	private CollateralType collateralType;

	@Positive(message = "최소 가치가 0보다 작습니다.")
	private int minimumValue;

	@Positive(message = "월 수익이 0보다 작습니다.")
	private int monthlyIncome;
}
