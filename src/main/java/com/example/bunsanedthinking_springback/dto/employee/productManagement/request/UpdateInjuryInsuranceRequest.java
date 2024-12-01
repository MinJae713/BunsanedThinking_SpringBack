package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInjuryInsuranceRequest {

	@NotNull(message = "나이 범위는 필수 값입니다.")
	private Integer ageRange;

	@NotNull(message = "계약 기간은 필수 값입니다.")
	private Integer contractPeriod;

	@NotNull(message = "보장 내용은 필수 값입니다.")
	@Size(max = 120, message = "보장 내용은 최대 120자까지 허용됩니다.")
	private String coverage;

	@NotNull(message = "ID는 필수 값입니다.")
	private Integer id;

	@NotNull(message = "부상 유형은 필수 값입니다.")
	private InjuryType injuryType;

	@NotNull(message = "보험 이름은 필수 값입니다.")
	@Size(max = 20, message = "보험 이름은 최대 20자까지 허용됩니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "보험 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;

	@NotNull(message = "보험 유형은 필수 값입니다.")
	private InsuranceType insuranceType;

	@NotNull(message = "월 보험료는 필수 값입니다.")
	private Integer monthlyPremium;

	@NotNull(message = "수술 한도는 필수 값입니다.")
	private Integer surgeriesLimit;

	@NotNull(message = "최대 금액은 필수 값입니다.")
	private Integer maximumMoney;

	public Injury toEntity() {
		Injury injury = new Injury(insuranceType, name, maximumMoney, ageRange, coverage, monthlyPremium,
			contractPeriod, injuryType, surgeriesLimit);
		injury.setId(id);
		return injury;
	}
}
