package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO {
    private int id;
    private String address;
    private int age;
    private String bank_account;
    private String bank_name;
    private int gender;
    private String job;
    private String name;
    private String phone_number;
    private Long property;
    private String resident_registration_number;
    }

