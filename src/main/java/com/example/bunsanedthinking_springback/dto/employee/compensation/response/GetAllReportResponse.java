package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllReportResponse {
    private int id;
    private String serviceType;
    private String date;
    private String location;
    private String customerName;
    private String customerPhoneNumber;
    private String accidentProcessStatus;
    private String processStatus;
    private int damageAssessmentMoney;
}
