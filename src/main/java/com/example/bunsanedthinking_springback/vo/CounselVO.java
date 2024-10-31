package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounselVO {
    private int id;
    private LocalDate counsel_date;
    private int process_status;
    private int customer_id;
    private int product_id;
}
