package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private List<AccidentHistoryInfoResponse> accidentHistoryList;
    private List<SurgeryHistoryInfoResponse> surgeryHistoryList;
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
        List<AccidentHistoryInfoResponse> accidentHistoryInfoResponses = new ArrayList<AccidentHistoryInfoResponse>();
        List<SurgeryHistoryInfoResponse> surgeryHistoryInfoResponses = new ArrayList<SurgeryHistoryInfoResponse>();
        customer.getAccidentHistoryList().forEach(accidentHistory ->
                accidentHistoryInfoResponses.add(AccidentHistoryInfoResponse.from(accidentHistory)));
        customer.getSurgeryHistoryList().forEach(surgeryHistory ->
                surgeryHistoryInfoResponses.add(SurgeryHistoryInfoResponse.from(surgeryHistory)));
        String bankAccount = customer.getBankAccount();
        return new CustomerInfoResponse(name, phoneNumber, gender,
                residentRegistrationNumber, address, job, age, property,
                accidentHistoryInfoResponses, surgeryHistoryInfoResponses, bankAccount);
    }
}
