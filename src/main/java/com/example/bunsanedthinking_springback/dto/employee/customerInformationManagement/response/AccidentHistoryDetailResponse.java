package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccidentHistoryDetailResponse {
    private int id;
    private String date;
    private String accidentDetail;

    public static AccidentHistoryDetailResponse from(AccidentHistory accidentHistory) {
        return AccidentHistoryDetailResponse.builder()
                .id(accidentHistory.getId())
                .date(DateUtils.toString(accidentHistory.getDate())) // 날짜를 String으로 변환
                .accidentDetail(accidentHistory.getAccidentDetail())
                .build();
    }
}
