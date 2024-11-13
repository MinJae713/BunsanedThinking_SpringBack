package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement;

import com.example.bunsanedthinking_springback.dto.employee.sales.InduceAccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.InduceDiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.InduceSurgeryHistoryDTO;
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
    private List<InduceAccidentHistoryDTO> accidentHistoryList;
    private List<InduceSurgeryHistoryDTO> surgeryHistoryList;
    private List<InduceDiseaseHistoryDTO> diseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
