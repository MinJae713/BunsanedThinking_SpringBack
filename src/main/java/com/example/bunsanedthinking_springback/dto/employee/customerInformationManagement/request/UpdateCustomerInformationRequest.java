package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateCustomerInformationRequest {
    private int index;
    private String input;
    private int id; // 고객 ID

    @Valid
    private List<UpdateAccidentHistoryRequest> accidentHistoryList; // 사고 이력 업데이트 시 사용

    @Valid
    private List<UpdateSurgeryHistoryRequest> surgeryHistoryList;  // 수술 이력 업데이트 시 사용

    @Valid
    private List<UpdateDiseaseHistoryRequest> diseaseHistoryList;  // 병력 업데이트 시 사용
}
