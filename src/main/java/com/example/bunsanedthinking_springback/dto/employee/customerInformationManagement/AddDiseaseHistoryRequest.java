package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDiseaseHistoryRequest {
    private String dateOfDiagnosis;
    private String name;
}
