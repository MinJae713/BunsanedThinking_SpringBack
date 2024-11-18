package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponse {
    private String name;
    private String phoneNumber;
    private String gender;
    private String residentRegistrationNumber;
    private String address;
}
