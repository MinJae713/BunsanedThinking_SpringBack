package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetailVO {
    private int id;
    private String depositor_name;
    private LocalDate date;
    private int money;
    private int path;
    private int contract_id;
}
