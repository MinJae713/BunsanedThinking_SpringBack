package com.example.bunsanedthinking_springback.dto.dae;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceDTO {
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
	private List<DiseaseHistoryDTO> diseaseHistoryList;
	private List<SurgeryHistoryDTO> surgeryHistoryList;
	private List<AccidentHistoryDTO> accidentHistoryList;
	private int productId;
	private int employeeId;
}
