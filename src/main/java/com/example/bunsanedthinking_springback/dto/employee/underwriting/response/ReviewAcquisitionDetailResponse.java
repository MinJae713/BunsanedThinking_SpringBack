package com.example.bunsanedthinking_springback.dto.employee.underwriting.response;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAcquisitionDetailResponse {
	private Integer id;
	private String name;
	private String phoneNumber;
	private String job;
	private Integer age;
	private String gender;
	private String residentRegistrationNumber;
	private String address;
	private Integer productId;
	private String contractStatus;
	private String bankAccount;
	private Long property;
	private List<AccidentHistory> accidentHistories;
	private List<SurgeryHistory> surgeryHistories;
	private List<DiseaseHistory> diseaseHistories;

	public static ReviewAcquisitionDetailResponse of(Customer customer, Contract contract) {
		return ReviewAcquisitionDetailResponse.builder().id(contract.getId()).name(customer.getName()).phoneNumber(customer.getPhoneNumber()).job(customer.getJob())
			.age(customer.getAge()).gender(customer.getGender().getName()).residentRegistrationNumber(customer.getResidentRegistrationNumber()).address(customer.getAddress())
			.productId(contract.getProductId()).contractStatus(contract.getContractStatus().getText()).bankAccount(customer.getBankAccount())
			.property(customer.getProperty()).accidentHistories(customer.getAccidentHistoryList()).surgeryHistories(customer.getSurgeryHistoryList())
			.diseaseHistories(customer.getDiseaseHistoryList()).build();
	}
}
