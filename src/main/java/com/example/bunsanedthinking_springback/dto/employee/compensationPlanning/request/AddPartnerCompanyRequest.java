package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPartnerCompanyRequest {
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;
    private int partnerCompanyType;
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
    private String headName;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String headPhoneNumber;
}
