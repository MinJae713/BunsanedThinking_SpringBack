package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSurgeryHistoryRequest {
    private String date;
    private String hospitalName;
    private String name;
}
