package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentVO {
    private int id;
    private LocalDate date;
    private String location;
    private int process_status;
    private int service_type;
    private int customer_id;

    public Accident getEntity(String name, String phoneNumber) {
        Accident accident = new Accident();
        accident.setId(id);
        accident.setCustomerID(customer_id);
        accident.setCustomerName(name);
        accident.setCustomerPhoneNumber(phoneNumber);
        accident.setDate(Date.valueOf(date));
        accident.setLocation(location);
        accident.setProcessStatus(AccidentProcessStatus.values()[process_status]);
        accident.setServiceType(ServiceType.values()[service_type]);
        return accident;
    }
}
