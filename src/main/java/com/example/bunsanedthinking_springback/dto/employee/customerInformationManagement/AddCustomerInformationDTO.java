package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement;

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
    private List<UpdateAccidentHistoryDTO> accidentHistoryList;
    private List<UpdateSurgeryHistoryDTO> surgeryHistoryList;
    private List<UpdateDiseaseHistoryDTO> diseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
