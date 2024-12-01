package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.customer.Gender;

@Data
@NoArgsConstructor
public class InduceInsuranceProductRequest {

	@NotBlank(message = "이름은 필수 입력 값입니다.")
	@Size(max = 20, message = "이름은 최대 20자 이내로 작성해야 합니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String name;

	@NotBlank(message = "주소는 필수 입력 값입니다.")
	@Size(max = 50, message = "주소는 최대 50자 이내로 작성해야 합니다.")
	private String address;

	@NotBlank(message = "계좌번호는 필수 입력 값입니다.")
	@Size(max = 20, message = "계좌번호는 최대 20자 이내로 작성해야 합니다.")
	private String bankAccount;

	@NotBlank(message = "은행 이름은 필수 입력 값입니다.")
	@Size(max = 10, message = "은행 이름은 최대 10자 이내로 작성해야 합니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "은행 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String bankName;

	@NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
	@Size(min = 13, max = 13, message = "핸드폰 번호는 반드시 13자리 형식이어야 합니다. (예: 010-1234-5678)")
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
		message = "핸드폰 번호의 형식이 올바르지 않습니다. 예: 010-1234-5678")
	private String phoneNumber;

	@NotBlank(message = "직업은 필수 입력 값입니다.")
	@Size(max = 20, message = "직업은 최대 20자 이내로 작성해야 합니다.")
	@Pattern(regexp = "^[a-zA-Z가-힣]+$",
		message = "직업은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	private String job;

	@NotNull(message = "재산은 필수 입력 값입니다.")
	@Min(value = 1, message = "재산의 값은 최소 1이어야 합니다.")
	private Long property;

	@NotBlank(message = "주민등록번호는 필수 입력 값입니다.")
	@Size(min = 14, max = 14, message = "주민등록번호는 반드시 14자리 형식이어야 합니다. (예: 123456-1234567)")
	@Pattern(regexp = "^[0-9]{6}-[1-4][0-9]{6}$",
		message = "주민등록번호 형식이 올바르지 않습니다. 예: 123456-1234567")
	private String residentRegistrationNumber;

	@NotNull(message = "나이는 필수 입력 값입니다.")
	@Min(value = 1, message = "나이의 값은 최소 1이어야 합니다.")
	@Max(value = 120, message = "나이의 값은 최대 120이어야 합니다.")
	private Integer age;

	@NotNull(message = "성별은 필수 입력 값입니다.")
	private Gender gender;

	@Valid
	private List<InduceDiseaseHistoryRequest> diseaseHistories;

	@Valid
	private List<InduceSurgeryHistoryRequest> surgeryHistories;

	@Valid
	private List<InduceAccidentHistoryRequest> accidentHistories;

	@NotNull(message = "상품 ID는 필수 입력 값입니다.")
	private Integer productId;

	@NotNull(message = "직원 ID는 필수 입력 값입니다.")
	private Integer employeeId;
}
