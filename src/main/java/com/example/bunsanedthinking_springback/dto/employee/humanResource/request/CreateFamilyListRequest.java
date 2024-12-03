package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFamilyListRequest {
	@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "잘못된 날짜 형식입니다.")
	private LocalDate birthDate;

	@Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;

	@NotNull(message = "가족관계는 필수 입력 항목입니다.")
	private RelationshipType relationship;
	private boolean survival;
}
