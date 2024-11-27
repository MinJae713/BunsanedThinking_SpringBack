package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@NoArgsConstructor
@Data
public class AccidentHistoryInfoResponse {
    private int customerID;
    private String date;
    private String accidentDetail;
    private int id;

    public static AccidentHistoryInfoResponse from(AccidentHistory accidentHistory) {
        AccidentHistoryInfoResponse response = new AccidentHistoryInfoResponse();
        response.setCustomerID(accidentHistory.getCustomerID());
        response.setId(accidentHistory.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        response.setDate(formatter.format(accidentHistory.getDate()));
        response.setAccidentDetail(accidentHistory.getAccidentDetail());
        return response;
    }
}
