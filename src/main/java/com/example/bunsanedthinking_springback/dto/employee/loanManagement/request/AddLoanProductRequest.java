package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.employee.loanManagement.LoanManagementDTOConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddLoanProductRequest {
	@NotNull(message = LoanManagementDTOConstants.LOAN_TYPE_NOT_NULL_MESSAGE)
	private LoanType loanType;

	@Pattern(regexp = CommonConstants.NAME_PATTERN_REGEXP, message = CommonConstants.NAME_PATTERN_MESSAGE)
	@Size(max = 20, message = CommonConstants.NAME_SIZE_MESSAGE)
	private String name;

	@Positive(message = LoanManagementDTOConstants.INTEREST_RATE_POSITIVE_MESSAGE)
	private int interestRate;

	@Positive(message = LoanManagementDTOConstants.MAXIMUM_MONEY_POSITIVE_MESSAGE)
	private int maximumMoney;

	@Positive(message = LoanManagementDTOConstants.MINIMUM_ASSET_POSITIVE_MESSAGE)
	private int minimumAsset;

	private int parameter;

	@Positive(message = LoanManagementDTOConstants.MONTHLY_INCOME_POSITIVE_MESSAGE)
	private int monthlyIncome;
}
