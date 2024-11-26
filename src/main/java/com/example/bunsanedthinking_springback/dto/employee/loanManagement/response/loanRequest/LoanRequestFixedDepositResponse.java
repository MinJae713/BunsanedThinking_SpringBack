package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;

import lombok.Getter;

@Getter
public class LoanRequestFixedDepositResponse extends LoanRequestResponse {
	private int minimumAmount;

	public LoanRequestFixedDepositResponse(int customerId, String customerName, String phoneNumber, String job, int age,
		String gender, String residentRegistrationNumber, long property,
		List<AccidentHistory> accidentHistoryList,
		List<DiseaseHistory> diseaseHistoryList,
		List<SurgeryHistory> surgeryHistoryList,
		String address, String bankName, String bankAccount, String loanName, String loanType, int loanId,
		int interestRate, int maximumMoney, int minimumAsset, int contractId, String contractStatus,
		int minimumAmount) {
		super(customerId, customerName, phoneNumber, job, age, gender, residentRegistrationNumber, property,
			accidentHistoryList, diseaseHistoryList, surgeryHistoryList, address, bankName, bankAccount, loanName,
			loanType,
			loanId, interestRate, maximumMoney, minimumAsset, contractId, contractStatus);
		this.minimumAmount = minimumAmount;
	}

	public static LoanRequestResponse of(FixedDeposit fixedDeposit, Customer customer,
		Contract contract) {
		return new LoanRequestFixedDepositResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
			customer.getJob(), customer.getAge(), customer.getGender().getName(),
			customer.getResidentRegistrationNumber(), customer.getProperty(),
			customer.getAccidentHistoryList(), customer.getDiseaseHistoryList(),
			customer.getSurgeryHistoryList(), customer.getAddress(), customer.getBankName(),
			customer.getBankAccount(), fixedDeposit.getName(), fixedDeposit.getLoanType().getName(),
			fixedDeposit.getId(), fixedDeposit.getInterestRate(), fixedDeposit.getMaximumMoney(),
			fixedDeposit.getMinimumAsset(), contract.getId(), contract.getContractStatus().getText(),
			fixedDeposit.getMinimumAmount());
	}
}
