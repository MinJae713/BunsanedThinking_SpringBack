package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewAccidentResponse {
    private int id;
    private String serviceType;
    private String date;
    private String customerName;
    private String customerPhoneNumber;
    private String processStatus;

    public static ViewAccidentResponse of(Accident accident) {
        int accidentId  = accident.getId();
        String serviceType = accident.getServiceType().getName();
        String date = accident.getDate();
        String customerName = accident.getCustomerName();
        String customerPhoneNumber = accident.getCustomerPhoneNumber();
        String processStatus = accident.getProcessStatus().getName();
        return new ViewAccidentResponse(accidentId, serviceType, date,
                customerName, customerPhoneNumber, processStatus);
    }
}
