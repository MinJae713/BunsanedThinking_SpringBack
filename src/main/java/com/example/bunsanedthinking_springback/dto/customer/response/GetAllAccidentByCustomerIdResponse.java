package com.example.bunsanedthinking_springback.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAccidentByCustomerIdResponse {
    private int id;
    private String serviceType;
    private String date;
    private String customerName;
    private String customerPhoneNumber;
    private String processStatus;
}
