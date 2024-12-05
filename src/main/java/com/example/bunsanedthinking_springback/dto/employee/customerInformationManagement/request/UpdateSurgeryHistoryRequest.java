package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSurgeryHistoryRequest {
    private int id;
    private String date;
    private String hospitalName;
    private String name;
}
