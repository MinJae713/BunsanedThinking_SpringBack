package com.example.bunsanedthinking_springback.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllContractByCustomerIdResponse {
    private String name;
    private String type;
    private int insuranceId;
    private int ageRange;
    private int monthlyPremium;
    private String expirationDate;
    private String date;
    private int paymentDate;
    private String status;
}
