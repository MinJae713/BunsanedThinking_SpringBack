package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSurgeryHistoryDTO {
    private String date;
    private String hospitalName;
    private String name;
}