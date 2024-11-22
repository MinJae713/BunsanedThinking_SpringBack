package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.loan.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanRequestResponse {
	Loan loan;
	Customer customer;
	Contract contract;

	public static LoanRequestResponse of(Loan loan, Customer customer, Contract contract) {
		return new LoanRequestResponse(loan, customer, contract);
	}
}
