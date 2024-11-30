package com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionAccidentHistoryDetailResponse;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;

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
	private List<ReviewAcquisitionAccidentHistoryDetailResponse> accidentHistories;
	private List<ReviewAcquisitionSurgeryHistoryDetailResponse> surgeryHistories;
	private List<ReviewAcquisitionDiseaseHistoryDetailResponse> diseaseHistories;

	public static ReviewAcquisitionDetailResponse of(Customer customer, Contract contract) {
		return ReviewAcquisitionDetailResponse.builder()
			.id(contract.getId())
			.name(customer.getName())
			.phoneNumber(customer.getPhoneNumber())
			.job(customer.getJob())
			.age(customer.getAge())
			.gender(customer.getGender().getName())
			.residentRegistrationNumber(customer.getResidentRegistrationNumber())
			.address(customer.getAddress())
			.productId(contract.getProductId())
			.contractStatus(contract.getContractStatus().getText())
			.bankAccount(customer.getBankAccount())
			.property(customer.getProperty())
			.accidentHistories(customer.getAccidentHistoryList().stream()
				.map(ReviewAcquisitionAccidentHistoryDetailResponse::from)
				.collect(Collectors.toList()))
			.surgeryHistories(customer.getSurgeryHistoryList().stream()
				.map(ReviewAcquisitionSurgeryHistoryDetailResponse::from)
				.collect(Collectors.toList()))
			.diseaseHistories(customer.getDiseaseHistoryList().stream()
				.map(ReviewAcquisitionDiseaseHistoryDetailResponse::from)
				.collect(Collectors.toList()))
			.build();
	}
}
