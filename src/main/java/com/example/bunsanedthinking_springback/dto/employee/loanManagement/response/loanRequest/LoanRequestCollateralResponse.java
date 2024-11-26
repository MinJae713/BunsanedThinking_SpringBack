package com.example.bunsanedthinking_springback.dto.employee.loanManagement.response.loanRequest;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;

import lombok.Getter;

@Getter
public class LoanRequestCollateralResponse extends LoanRequestResponse {
	private String collateralType;
	private int minimumValue;

	public LoanRequestCollateralResponse(int customerId, String customerName, String phoneNumber, String job, int age,
		String gender, String residentRegistrationNumber, long property,
		List<AccidentHistory> accidentHistoryList,
		List<DiseaseHistory> diseaseHistoryList,
		List<SurgeryHistory> surgeryHistoryList,
		String address, String bankName, String bankAccount, String loanName, String loanType, int loanId,
		int interestRate, int maximumMoney, int minimumAsset, int contractId, String contractStatus,
		String collateralType,
		int minimumValue) {
		super(customerId, customerName, phoneNumber, job, age, gender, residentRegistrationNumber, property,
			accidentHistoryList, diseaseHistoryList, surgeryHistoryList, address, bankName, bankAccount, loanName,
			loanType,
			loanId, interestRate, maximumMoney, minimumAsset, contractId, contractStatus);
		this.collateralType = collateralType;
		this.minimumValue = minimumValue;
	}

	public static LoanRequestResponse of(Collateral collateral, Customer customer, Contract contract) {
		return new LoanRequestCollateralResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
			customer.getJob(), customer.getAge(), customer.getGender().getName(),
			customer.getResidentRegistrationNumber(), customer.getProperty(),
			customer.getAccidentHistoryList(), customer.getDiseaseHistoryList(),
			customer.getSurgeryHistoryList(), customer.getAddress(), customer.getBankName(),
			customer.getBankAccount(), collateral.getName(), collateral.getLoanType().getName(), collateral.getId(),
			collateral.getInterestRate(), collateral.getMaximumMoney(), collateral.getMinimumAsset(), contract.getId(),
			contract.getContractStatus().getText(), collateral.getCollateralType().getName(),
			collateral.getMinimumValue());
	}
}
