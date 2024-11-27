package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;
    private String job;
    private int age;
    private Gender gender;
    private String residentRegistrationNumber;
    private String address;
    private long property;
    private String bankName;
    private String bankAccount;
    private List<SignUpAccidentHistoryRequest> tempAccidentHistoryList;
    private List<SignUpSurgeryHistoryRequest> tempSurgeryHistoryList;
    private List<SignUpDiseaseHistoryRequest> tempDiseaseHistoryList;
}
