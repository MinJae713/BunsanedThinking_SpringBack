package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FamilyVO {
    private int id;
    private LocalDate birth_date;
    private String name;
    private int relationship;
    private boolean survival;
    private int employee_id;
}
