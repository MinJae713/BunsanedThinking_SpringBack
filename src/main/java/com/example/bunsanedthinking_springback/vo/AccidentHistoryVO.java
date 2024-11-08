package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentHistoryVO {
    private int id;
    private LocalDate date;
    private String details_of_accident;
    private int customer_id;

    public AccidentHistory findEntity() {
        AccidentHistory accidentHistory = new AccidentHistory();
        accidentHistory.setId(id);
        accidentHistory.setDate(Date.valueOf(date));
        accidentHistory.setAccidentDetail(details_of_accident);
        accidentHistory.setCustomerID(customer_id);
        return accidentHistory;
    }
}
