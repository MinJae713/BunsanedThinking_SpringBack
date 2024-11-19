package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
	private int gender;
	private List<InduceDiseaseHistoryRequest> diseaseHistoryList;
	private List<InduceSurgeryHistoryRequest> surgeryHistoryList;
	private List<InduceAccidentHistoryRequest> accidentHistoryList;
	private int productId;
	private int employeeId;
}
