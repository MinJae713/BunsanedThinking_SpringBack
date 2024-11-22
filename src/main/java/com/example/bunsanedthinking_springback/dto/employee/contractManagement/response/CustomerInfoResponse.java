package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponse {
    private String name;
    private String phoneNumber;
    private String gender;
    private String residentRegistrationNumber;
    private String address;

    private String job;
    private int age;
    private long property;
    private List<AccidentHistory> accidentHistoryList;
    private List<SurgeryHistory> surgeryHistoryList;
    private String bankAccount;

    public static CustomerInfoResponse from(Customer customer) {
        String name = customer.getName();
        String phoneNumber = customer.getPhoneNumber();
        String gender = customer.getGender().getName();
        String residentRegistrationNumber = customer.getResidentRegistrationNumber();
        String address = customer.getAddress();

        String job = customer.getJob();
        int age = customer.getAge();
        long property = customer.getProperty();
        List<AccidentHistory> accidentHistoryList = customer.getAccidentHistoryList();
        List<SurgeryHistory> surgeryHistoryList = customer.getSurgeryHistoryList();
        String bankAccount = customer.getBankAccount();
        return new CustomerInfoResponse(name, phoneNumber, gender,
                residentRegistrationNumber, address, job, age, property,
                accidentHistoryList, surgeryHistoryList, bankAccount);
    }
}
