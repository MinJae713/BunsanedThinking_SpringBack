package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiseaseHistoryVO {
    private int id;
    private LocalDate date_of_diagnosis;
    private String name;
    private int customer_id;
}
