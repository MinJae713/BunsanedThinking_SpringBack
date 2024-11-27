package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;

import lombok.Getter;

@Getter
public class LoanRequestInsuranceContractResponse extends LoanRequestResponse {
	private int insuranceId;

	public LoanRequestInsuranceContractResponse(int customerId, String customerName, String phoneNumber, String job,
		int age, String gender, String residentRegistrationNumber, long property,
		List<AccidentHistory> accidentHistoryList,
		List<DiseaseHistory> diseaseHistoryList,
		List<SurgeryHistory> surgeryHistoryList,
		String address, String bankName, String bankAccount, String loanName, String loanType, int loanId,
		int interestRate, int maximumMoney, int minimumAsset, int contractId, String contractStatus, int insuranceId) {
		super(customerId, customerName, phoneNumber, job, age, gender, residentRegistrationNumber, property,
			accidentHistoryList.stream()
				.map(AccidentHistoryResponse::from)
				.toList(),
			diseaseHistoryList.stream()
				.map(DiseaseHistoryResponse::from)
				.toList(),
			surgeryHistoryList.stream()
				.map(SurgeryHistoryResponse::from)
				.toList(),
			address, bankName, bankAccount, loanName,
			loanType,
			loanId, interestRate, maximumMoney, minimumAsset, contractId, contractStatus);
		this.insuranceId = insuranceId;
	}

	public static LoanRequestResponse of(InsuranceContract insuranceContract, Customer customer,
		Contract contract) {
		return new LoanRequestInsuranceContractResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
			customer.getJob(), customer.getAge(), customer.getGender().getName(),
			customer.getResidentRegistrationNumber(), customer.getProperty(),
			customer.getAccidentHistoryList(), customer.getDiseaseHistoryList(),
			customer.getSurgeryHistoryList(), customer.getAddress(), customer.getBankName(),
			customer.getBankAccount(), insuranceContract.getName(), insuranceContract.getLoanType().getName(),
			insuranceContract.getId(), insuranceContract.getInterestRate(), insuranceContract.getMaximumMoney(),
			insuranceContract.getMinimumAsset(), contract.getId(), contract.getContractStatus().getText(),
			insuranceContract.getInsuranceId());
	}
}
