package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private String name;
    private String phoneNumber;
    private String job;
    private int age;
    private String gender;
    private String residentRegistrationNumber;
    private String address;
    private String bankName;
    private String bankAccount;
    private int id;


    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getJob(),
                customer.getAge(),
                customer.getGender().getName(),
                customer.getResidentRegistrationNumber(),
                customer.getAddress(),
                customer.getBankName(),
                customer.getBankAccount(),
                customer.getId()
        );
    }
}
