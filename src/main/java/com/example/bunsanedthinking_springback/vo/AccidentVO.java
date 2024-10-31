package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
