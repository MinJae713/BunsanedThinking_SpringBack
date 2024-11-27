package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private String name;
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
