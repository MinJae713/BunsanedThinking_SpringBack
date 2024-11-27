package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanRequestResponse {
	int customerId;
	String customerName;
	String phoneNumber;
	String job;
	int age;
	String gender;
	String residentRegistrationNumber;
	long property;
	List<AccidentHistoryResponse> accidentHistoryList;
	List<DiseaseHistoryResponse> diseaseHistoryList;
	List<SurgeryHistoryResponse> surgeryHistoryList;
	String address;
	String bankName;
	String bankAccount;
	String loanName;
	String loanType;
	int loanId;
	int interestRate;
	int maximumMoney;
	int minimumAsset;
	int contractId;
	String contractStatus;

	public static LoanRequestResponse of(Loan loan, Customer customer, Contract contract) {
		return new LoanRequestResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
			customer.getJob(), customer.getAge(), customer.getGender().getName(),
			customer.getResidentRegistrationNumber(), customer.getProperty(),
			customer.getAccidentHistoryList().stream().map(AccidentHistoryResponse::from).toList(),
			customer.getDiseaseHistoryList().stream().map(DiseaseHistoryResponse::from).toList(),
			customer.getSurgeryHistoryList().stream().map(SurgeryHistoryResponse::from).toList(),
			customer.getAddress(), customer.getBankName(),
			customer.getBankAccount(), loan.getName(), loan.getLoanType().getName(), loan.getId(),
			loan.getInterestRate(), loan.getMaximumMoney(), loan.getMinimumAsset(), contract.getId(),
			contract.getContractStatus().getText());
	}

	public static LoanRequestResponse ofWithDetail(Loan loan, Customer customer, Contract contract) {
		return switch (loan.getLoanType()) {
			case InsuranceContract ->
				LoanRequestInsuranceContractResponse.of((InsuranceContract)loan, customer, contract);
			case FixedDeposit -> LoanRequestFixedDepositResponse.of((FixedDeposit)loan, customer, contract);
			case Collateral -> LoanRequestCollateralResponse.of((Collateral)loan, customer, contract);
		};
	}
}
