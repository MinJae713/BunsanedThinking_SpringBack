package com.example.bunsanedthinking_springback.dto.customer.request;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpDTO {
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
    private List<AccidentHistory> tempAccidentHistoryList;
    private List<SurgeryHistory> tempSurgeryHistoryList;
    private List<DiseaseHistory> tempDiseaseHistoryList;
}
