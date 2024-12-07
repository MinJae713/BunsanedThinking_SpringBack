package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurgeryHistoryDetailResponse {
    private int id;
    private String date;
    private String hospitalName;
    private String name;

    public static SurgeryHistoryDetailResponse from(SurgeryHistory surgeryHistory){
        return SurgeryHistoryDetailResponse.builder()
                .id(surgeryHistory.getId())
                .date(DateUtils.toString(surgeryHistory.getDate()))
                .hospitalName(surgeryHistory.getHospitalName())
                .name(surgeryHistory.getName())
                .build();
    }
}
