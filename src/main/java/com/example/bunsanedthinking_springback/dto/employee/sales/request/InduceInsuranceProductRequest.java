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
	private Long property;
	private String residentRegistrationNumber;
	private Integer age;
	private Gender gender;
	private List<InduceDiseaseHistoryRequest> diseaseHistories;
	private List<InduceSurgeryHistoryRequest> surgeryHistories;
	private List<InduceAccidentHistoryRequest> accidentHistories;
	private Integer productId;
	private Integer employeeId;
}
