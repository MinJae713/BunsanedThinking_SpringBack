package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private long property;
    private int id;
    private List<AccidentHistoryDetailResponse> accidentHistoryList;
    private List<SurgeryHistoryDetailResponse> surgeryHistoryList;
    private List<DiseaseHistoryDetailResponse> diseaseHistoryList;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .job(customer.getJob())
                .age(customer.getAge())
                .gender(customer.getGender().getName())
                .residentRegistrationNumber(customer.getResidentRegistrationNumber())
                .address(customer.getAddress())
                .bankName(customer.getBankName())
                .bankAccount(customer.getBankAccount())
                .property(customer.getProperty())
                .id(customer.getId())
                .accidentHistoryList(customer.getAccidentHistoryList().stream()
                        .map(AccidentHistoryDetailResponse::from)
                        .collect(Collectors.toList()))
                .surgeryHistoryList(customer.getSurgeryHistoryList().stream()
                        .map(SurgeryHistoryDetailResponse::from)
                        .collect(Collectors.toList()))
                .diseaseHistoryList(customer.getDiseaseHistoryList().stream()
                        .map(DiseaseHistoryDetailResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
