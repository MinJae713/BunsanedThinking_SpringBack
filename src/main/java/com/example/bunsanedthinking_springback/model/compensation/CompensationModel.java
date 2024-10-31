package com.example.bunsanedthinking_springback.model.compensation;

import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompensationModel {

	public void requestCompensation(String accountHolder, String bank, String bankAccount, int money,
									PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList, Report report, ReportList reportList, AccidentList accidentList)
					throws NotExistException, AlreadyProcessedException{
		if (report.getProcessStatus() == ReportProcessStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		PaymentDetail payment = new PaymentDetail(accountHolder, bank, bankAccount, money, paymentType, contractId);
		paymentDetailList.add(payment);
		report.setProcessStatus(ReportProcessStatus.Completed);
		reportList.update(report);
		report.getAccident().complete();
		accidentList.update(report.getAccident());
	}
	
	public void requestInsuranceMoney(Customer customer, int money, InsuranceMoney insuranceMoney, InsuranceMoneyList insuranceMoneyList,
									  PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException{
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		PaymentDetail payment = new PaymentDetail(customer.getName(), customer.getBankName(), customer.getBankAccount(), money, paymentType, contractId);
		paymentDetailList.add(payment);
		insuranceMoney.setProcessStatus(InsuranceMoneyStatus.Completed);
		insuranceMoney.handle();
		insuranceMoneyList.update(insuranceMoney);
	}
	// 메소드는 아래에 적어주셔유! (MVC 적용)

	public ArrayList<InsuranceMoney> getAll(InsuranceMoneyList insuranceMoneyList) {
		return insuranceMoneyList.getAll();
	}

	public ArrayList<InsuranceMoney> getAllUnprocessed(InsuranceMoneyList insuranceMoneyList) {
		return insuranceMoneyList.getAllUnprocessed();
	}

	public ArrayList<InsuranceMoney> getAllProcessed(InsuranceMoneyList insuranceMoneyList) {
		return insuranceMoneyList.getAllProcessed();
	}

	public InsuranceMoney get(InsuranceMoneyList insuranceMoneyList, int id) throws NotExistException {
		return insuranceMoneyList.get(id);
	}

	public Contract get(ContractList contractList, int contractId) throws NotExistContractException {
		return contractList.get(contractId);
	}

	public Customer get(CustomerList customerList, int customerID) throws NotExistException {
		return customerList.get(customerID);
	}

	public ArrayList<Report> getAll(ReportList reportList) {
		return reportList.getAll();
	}

	public Report get(ReportList reportList, int id) throws NotExistException {
		return reportList.get(id);
	}

	public ArrayList<Report> getAllUnprocessedReport(ReportList reportList) {
		return reportList.getAllUnprocessedReport();
	}

	public ArrayList<Report> getAllCompletedReport(ReportList reportList) {
		return reportList.getAllCompletedReport();
	}

	public Contract getAutomobileByMember(ContractList contractList, int customerID) throws NotExistContractException {
		return contractList.getAutomobileByMember(customerID);
	}
}