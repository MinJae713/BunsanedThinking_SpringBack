package com.example.bunsanedthinking_springback.dto.employee.humanResource.request;

import java.time.LocalDate;

import com.example.bunsanedthinking_springback.entity.family.RelationshipType;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFamilyListRequest {
	private LocalDate birthDate;

	@Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
	@Size(max = 20, message = "이름이 20글자를 초과하였습니다.")
	private String name;
	private RelationshipType relationship;
	private boolean survival;
}
