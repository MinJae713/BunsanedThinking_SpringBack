package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class UpdatePartnerCompanyRequest {
    @Range(min = 1, max = 4, message = "유효한 선택 옵션이 아닙니다")
    private int index;
    @NotBlank(message = "수정 값은 반드시 입력해주세요")
    private String input;
    @Min(value = 3000, message = "유효한 아이디가 아닙니다")
    private int partnerCompanyId;
}
