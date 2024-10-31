package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyVO {
    private int id;
    private LocalDate birth_date;
    private String name;
    private int relationship;
    private boolean survival;
    private int employee_id;
}
