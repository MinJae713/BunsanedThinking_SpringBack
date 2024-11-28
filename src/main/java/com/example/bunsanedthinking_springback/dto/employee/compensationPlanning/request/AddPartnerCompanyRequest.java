package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPartnerCompanyRequest {
    @NotBlank(message = "이름은 공백 없이 입력해주세요")
    @Pattern(regexp = "\\D", message = "이름은 숫자가 들어갈 수 없습니다")
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;
    private int partnerCompanyType;
    @NotBlank(message = "이름은 공백 없이 입력해주세요")
    @Pattern(regexp = "\\D", message = "이름은 숫자가 들어갈 수 없습니다")
    private String headName;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String headPhoneNumber;
}
