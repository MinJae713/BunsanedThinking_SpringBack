package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAutomobileInsuranceRequest {

	@NotBlank(message = "보험 이름은 필수 값입니다.")
	@Size(max = 20, message = "보험 이름은 최대 20자까지 허용됩니다.")
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

	@NotNull(message = "차량 유형은 필수 값입니다.")
	private VehicleType vehicleType;

	@NotNull(message = "사고 한도는 필수 값입니다.")
	private Integer accidentLimit;

	@Valid
	private List<ServiceType> serviceTypes;
}
