package com.example.bunsanedthinking_springback.dto.employee.loanManagement.request;

import com.example.bunsanedthinking_springback.entity.loan.Loan;

public interface UpdateLoanRequest {
	Loan toEntity();

	int getId();

	String getName();
}
