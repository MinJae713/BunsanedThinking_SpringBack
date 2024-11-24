package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.customer.Gender;

@Data
@NoArgsConstructor
public class InduceInsuranceProductRequest {
	private String name;
	private String address;
	private String bankAccount;
	private String bankName;
	private String phoneNumber;
	private String job;
	private long property;
	private String residentRegistrationNumber;
	private int age;
	private Gender gender;
	private List<InduceDiseaseHistoryRequest> diseaseHistories;
	private List<InduceSurgeryHistoryRequest> surgeryHistories;
	private List<InduceAccidentHistoryRequest> accidentHistories;
	private int productId;
	private int employeeId;
}
