package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddCustomerInformationRequest {
    private String name;
    private String phoneNumber;
    private String job;
    private int age;
    private int gender;
    private String residentRegistrationNumber;
    private String address;
    private long property;
    private List<AddAccidentHistoryDTO> accidentHistoryList;
    private List<AddSurgeryHistoryDTO> surgeryHistoryList;
    private List<AddDiseaseHistoryDTO> diseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
