package com.example.bunsanedthinking_springback.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInsuranceResponse {
    private String name;
    private String insuranceType;
    private int id;
    private int ageRange;
    private int monthlyPremium;
}
