package com.example.bunsanedthinking_springback.dto.customerInformationManagement;

import com.example.bunsanedthinking_springback.dto.sales.AccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.sales.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.sales.SurgeryHistoryDTO;
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
    private List<AccidentHistoryDTO> accidentHistoryList;
    private List<SurgeryHistoryDTO> surgeryHistoryList;
    private List<DiseaseHistoryDTO> diseaseHistoryList;
    private String bankName;
    private String bankAccount;
}
