package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompensationDetailVO {
    private int id;
    private int money;
    private LocalDate payment_date;
    private int contract_id;
}
