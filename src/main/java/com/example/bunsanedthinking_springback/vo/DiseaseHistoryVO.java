package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseHistoryVO {
    private int id;
    private LocalDate date_of_diagnosis;
    private String name;
    private int customer_id;

    public DiseaseHistory getEntity() {
        DiseaseHistory diseaseHistory = new DiseaseHistory();
        diseaseHistory.setId(id);
        diseaseHistory.setDate_of_diagnosis(Date.valueOf(date_of_diagnosis));
        diseaseHistory.setName(name);
        diseaseHistory.setCustomer_id(customer_id);
        return diseaseHistory;
    }
}
