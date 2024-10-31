package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseHistoryVO {
    private int id;
    private LocalDate date_of_diagnosis;
    private String name;
    private int customer_id;
}
