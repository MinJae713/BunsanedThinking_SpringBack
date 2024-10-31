package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseVO {
    private int product_id;
    private String disease_name;
    private int disease_limit;
    private int surgeries_limit;
}
