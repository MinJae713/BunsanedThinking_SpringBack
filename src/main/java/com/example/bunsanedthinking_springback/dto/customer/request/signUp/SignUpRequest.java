package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpRequest {
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
    private String name;
    @NotBlank(message = "전화번호는 반드시 입력해주세요")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;
    private String job;
    @Range(min = 1, max = 130, message = "나이는 1~130 이내에서 입력해주세요")
    private int age;
    private Gender gender;
    @Pattern(regexp = "^\\d{2}[0-1]\\d[0-3]\\d-?[1-6]\\d{6}$", message = "주민등록번호 형식과 일치하지 않습니다")
    private String residentRegistrationNumber;
    @NotBlank(message = "주소를 반드시 입력해주세요")
    private String address;
    @Positive(message = "재산은 양수여야 합니다")
    private long property;
    @NotBlank(message = "은행 이름을 반드시 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.")
    private String bankName;
    @NotBlank(message = "계좌번호를 반드시 입력해주세요")
    @Pattern(regexp = "^[\\d-]+$", message = "유효하지 않은 계좌번호입니다")
    private String bankAccount;
    private List<SignUpAccidentHistoryRequest> tempAccidentHistoryList;
    private List<SignUpSurgeryHistoryRequest> tempSurgeryHistoryList;
    private List<SignUpDiseaseHistoryRequest> tempDiseaseHistoryList;
}
