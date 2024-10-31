package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class DiseaseVO {
    private int product_id;
    private String disease_name;
    private int disease_limit;
    private int surgeries_limit;
}
