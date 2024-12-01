package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDiseaseHistoryRequest {
    private int id; // 기존 데이터 식별용
    private String dateOfDiagnosis;
    private String name;
}
