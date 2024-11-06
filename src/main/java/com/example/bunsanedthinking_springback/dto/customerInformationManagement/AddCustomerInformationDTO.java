package com.example.bunsanedthinking_springback.dto.customerInformationManagement;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddCustomerInformationDTO {
    private String name;
    private String phoneNumber;
    private String job;
    private int age;
    private int gender;
    private String residentRegistrationNumber;
    private String address;
    private long property;
    private List<AccidentHistory> tempAccidentHistoryList;
    private List<SurgeryHistory> tempSurgeryHistoryList;
    private List<DiseaseHistory> tempDiseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
