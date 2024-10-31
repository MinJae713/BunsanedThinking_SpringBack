package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EndorsementVO {
    private int contract_id;
    private LocalDate apply_date;
}
