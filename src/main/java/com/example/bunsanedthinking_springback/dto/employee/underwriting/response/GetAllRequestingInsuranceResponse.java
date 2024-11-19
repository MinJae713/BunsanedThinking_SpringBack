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
public class GetAllRequestingInsuranceResponse {
	private String name;
	private String phoneNumber;
	private String job;
	private Integer age;
	private Gender gender;
	private String residentRegistrationNumber;
	private String address;
	private Integer productId;
	private ContractStatus contractStatus;

	public static GetAllRequestingInsuranceResponse from(Customer customer, Contract contract) {
		return GetAllRequestingInsuranceResponse.builder().name(customer.getName()).phoneNumber(customer.getPhoneNumber()).job(customer.getJob())
			.age(customer.getAge()).gender(customer.getGender()).residentRegistrationNumber(customer.getResidentRegistrationNumber()).address(customer.getAddress())
			.productId(contract.getProductId()).contractStatus(contract.getContractStatus()).build();
	}
}
