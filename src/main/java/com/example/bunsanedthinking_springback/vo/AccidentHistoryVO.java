package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccidentHistoryVO {
    private int id;
    private LocalDate date;
    private String details_of_accident;
    private int customer_id;
}
