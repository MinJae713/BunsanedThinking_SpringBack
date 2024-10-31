package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CounselVO {
    private int id;
    private LocalDate counsel_date;
    private int process_status;
    private int customer_id;
    private int product_id;
}
