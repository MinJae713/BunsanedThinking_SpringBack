package com.example.bunsanedthinking_springback.dto.employee.productManagement.request;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAutomobileInsuranceRequest {

	@NotNull(message = "나이 범위는 필수 값입니다.")
	@Min(value = 1, message = "나이의 값은 최소 1이어야 합니다.")
	@Max(value = 120, message = "나이의 값은 최대 120이어야 합니다.")
	private Integer ageRange;

	@NotNull(message = "계약 기간은 필수 값입니다.")
	@Min(value = 1, message = "계약 기간은 최소 1이어야 합니다.")
	private Integer contractPeriod;

	@NotNull(message = "보장 내용은 필수 값입니다.")
	@Size(max = 120, message = "보장 내용은 최대 120자까지 허용됩니다.")
	private String coverage;

	@NotNull(message = "ID는 필수 값입니다.")
	private Integer id;

	@NotNull(message = "보험 이름은 필수 값입니다.")
	@Size(max = 20, message = "보험 이름은 최대 20자까지 허용됩니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "보험 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;

	@NotNull(message = "보험 유형은 필수 값입니다.")
	private InsuranceType insuranceType;

	@NotNull(message = "월 보험료는 필수 값입니다.")
	@Min(value = 1, message = "월 보험료는 최소 1이어야 합니다.")
	private Integer monthlyPremium;

	private ArrayList<ServiceType> serviceTypes;

	@NotNull(message = "차량 유형은 필수 값입니다.")
	private VehicleType vehicleType;

	@NotNull(message = "한도는 필수 값입니다.")
	@Min(value = 1, message = "한도는 최소 1이어야 합니다.")
	private Integer maximumMoney;

	@NotNull(message = "사고 한도는 필수 값입니다.")
	@Min(value = 1, message = "사고 한도는 최소 1이어야 합니다.")
	private Integer accidentLimit;

	public Automobile toEntity() {
		Automobile automobile = new Automobile(insuranceType, name, maximumMoney, ageRange, coverage, monthlyPremium,
			contractPeriod, accidentLimit, vehicleType, serviceTypes);
		automobile.setId(id);
		return automobile;
	}
}
