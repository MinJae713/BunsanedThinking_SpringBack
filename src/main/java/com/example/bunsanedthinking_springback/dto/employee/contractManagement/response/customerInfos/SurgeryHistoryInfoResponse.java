package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        response.setDate(DateUtils.toString(surgeryHistory.getDate()));
        response.setId(surgeryHistory.getId());
        response.setHospitalName(surgeryHistory.getHospitalName());
        response.setName(surgeryHistory.getName());
        return response;
    }
}
