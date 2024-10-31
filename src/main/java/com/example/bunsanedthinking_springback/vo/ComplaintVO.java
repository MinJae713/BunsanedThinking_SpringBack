package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ComplaintVO {
    private int id;
    private int complaint_type;
    private String content;
    private LocalDate date;
    private String employee_name;
    private LocalDate processing_date;
    private int process_status;
    private String result;
    private String title;
    private int customer_id;
}
