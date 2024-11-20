package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompensationResponse {
    private int id;
    private String serviceType;
    private String date;
    private String location;
    private String customerName;
    private String customerPhoneNumber;
    private String accidentProcessStatus;
    private String processStatus;
    private int damageAssessmentMoney;

    public static RequestCompensationResponse of(Report report) {
        int id = report.getAccident().getId();
        String serviceType = report.getAccident().getServiceType().getName();
        String date = report.getAccident().getDate();
        String location = report.getAccident().getLocation();
        String customerName = report.getAccident().getCustomerName();
        String customerPhoneNumber = report.getAccident().getCustomerPhoneNumber();
        String accidentProcessStatus = report.getAccident().getProcessStatus().getName();
        String processStatus = report.getProcessStatus().getName();
        int damageAssessmentMoney = report.getDamageAssessmentMoney();
        return new RequestCompensationResponse(id, serviceType, date, location, customerName,
                customerPhoneNumber, accidentProcessStatus,
                processStatus, damageAssessmentMoney);
    }
}
