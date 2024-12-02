package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddEmployeeRequest {
	@Positive(message = "잘못된 부서 번호입니다.")
	private int departmentId;

	@Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;

	private EmployeePosition employeePosition;

	@Size(max = 50, message = "주소가 50글자를 초과하였습니다.")
	private String address;

	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
	private String phoneNumber;

	@Size(max = 10, message = "은행명이 10글자를 초과하였습니다.")
	private String bankName;

	@Size(max = 20, message = "계좌번호가 20글자를 초과하였습니다.")
	private String bankAccount;

	@Pattern(regexp = "^[0-9]{6}-[1-4][0-9]{6}$", message = "잘못된 주민번호 형식입니다.")
	private String residentRegistrationNumber;

	@Positive(message = "급여가 0보다 작습니다.")
	private int salary;

	@Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])", message = "잘못된 날짜 형식입니다.")
	private String employmentDate;
	private List<CreateFamilyListRequest> tempFamilyList;
}
