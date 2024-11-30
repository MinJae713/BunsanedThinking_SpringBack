package com.example.bunsanedthinking_springback.dto.employee.underwriting.response;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAcquisitionResponse {
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

	public static ReviewAcquisitionResponse of(Customer customer, Contract contract) {
		return ReviewAcquisitionResponse.builder().id(contract.getId()).name(customer.getName()).phoneNumber(customer.getPhoneNumber()).job(customer.getJob())
			.age(customer.getAge()).gender(customer.getGender().getName()).residentRegistrationNumber(customer.getResidentRegistrationNumber()).address(customer.getAddress())
			.productId(contract.getProductId()).contractStatus(contract.getContractStatus().getText()).build();
	}
}
