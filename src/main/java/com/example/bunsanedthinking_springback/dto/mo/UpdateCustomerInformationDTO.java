package com.example.bunsanedthinking_springback.dto.mo;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateCustomerInformationDTO {
    private int id; // 고객 ID
    private String name;
    private String phoneNumber;
    private String job;
    private int age;
    private int gender;
    private String residentRegistrationNumber;
    private String address;
    private long property;
    private List<AccidentHistory> accidentHistoryList;
    private List<SurgeryHistory> surgeryHistoryList;
    private List<DiseaseHistory> diseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
