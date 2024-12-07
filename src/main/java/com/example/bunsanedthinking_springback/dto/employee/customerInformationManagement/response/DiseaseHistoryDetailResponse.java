package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseHistoryDetailResponse {
    private int id;
    private String dateOfDiagnosis;
    private String name;

    public static DiseaseHistoryDetailResponse from(DiseaseHistory diseaseHistory) {
        return DiseaseHistoryDetailResponse.builder()
                .id(diseaseHistory.getId())
                .dateOfDiagnosis(DateUtils.toString(diseaseHistory.getDate_of_diagnosis()))
                .name(diseaseHistory.getName())
                .build();
    }
}
