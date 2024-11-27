package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@NoArgsConstructor
@Data
public class SurgeryHistoryInfoResponse {
    private int customerID;
    private String date;
    private String hospitalName;
    private int id;
    private String name;

    public static SurgeryHistoryInfoResponse from(SurgeryHistory surgeryHistory) {
        SurgeryHistoryInfoResponse response = new SurgeryHistoryInfoResponse();
        response.setCustomerID(surgeryHistory.getCustomerID());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        response.setDate(formatter.format(surgeryHistory.getDate()));
        response.setId(surgeryHistory.getId());
        response.setHospitalName(surgeryHistory.getHospitalName());
        response.setName(surgeryHistory.getName());
        return response;
    }
}
