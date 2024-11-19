package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
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

    public static CustomerInfoResponse from(Customer customer) {
        return new CustomerInfoResponse(customer.getName(),
                customer.getPhoneNumber(), customer.getGender().getName(),
                customer.getResidentRegistrationNumber(), customer.getAddress());
    }
}
