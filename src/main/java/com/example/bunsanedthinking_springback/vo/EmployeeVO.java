package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private int id;
    private String address;
    private String bank_account;
    private LocalDate employment_date;
    private String name;
    private String phone_number;
    private int position;
    private String resident_registration_number;
    private int salary;
    private int department_id;
}
