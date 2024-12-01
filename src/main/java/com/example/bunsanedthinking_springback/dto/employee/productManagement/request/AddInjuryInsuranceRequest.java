package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddInjuryInsuranceRequest {

	@NotBlank(message = "이름은 필수 값입니다.")
	@Size(max = 20, message = "이름은 최대 20자까지 허용됩니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "보험 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;

	@NotNull(message = "최대 금액은 필수 값입니다.")
	private Integer maximumMoney;

	@NotNull(message = "보험 유형은 필수 값입니다.")
	private InsuranceType insuranceType;

	@NotNull(message = "나이 범위는 필수 값입니다.")
	private Integer ageRange;

	@NotNull(message = "월 보험료는 필수 값입니다.")
	private Integer monthlyPremium;

	@NotNull(message = "계약 기간은 필수 값입니다.")
	private Integer contractPeriod;

	@NotBlank(message = "보장 내용은 필수 값입니다.")
	@Size(max = 120, message = "보장 내용은 최대 120자까지 허용됩니다.")
	private String coverage;

	@NotNull(message = "상해 유형은 필수 값입니다.")
	private InjuryType injuryType;

	@NotNull(message = "수술 횟수는 필수 값입니다.")
	private Integer surgeriesLimit;
}
