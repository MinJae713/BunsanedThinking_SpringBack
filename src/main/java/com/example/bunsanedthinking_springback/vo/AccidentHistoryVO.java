package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentHistoryVO {
    private int id;
    private LocalDate date;
    private String details_of_accident;
    private int customer_id;
}
