package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.compensation.CompensationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/compensation")
public class CompensationController {
	@Autowired
	private CompensationModel compensationModel;

	public void requestCompensation(String accountHolder, String bank, String bankAccount, int money,
									PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList, Report report, ReportList reportList, AccidentList accidentList)
					throws NotExistException, AlreadyProcessedException {
		compensationModel.requestCompensation(accountHolder, bank, bankAccount, money, paymentType, contractId, paymentDetailList, report, reportList, accidentList);
	}
	public void requestInsuranceMoney(Customer customer, int money, InsuranceMoney insuranceMoney, InsuranceMoneyList insuranceMoneyList,
									  PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException{
		compensationModel.requestInsuranceMoney(customer, money, insuranceMoney, insuranceMoneyList, paymentType, contractId, paymentDetailList);
	}
	public ArrayList<InsuranceMoney> getAll(InsuranceMoneyList insuranceMoneyList) {
		return compensationModel.getAll(insuranceMoneyList);
	}
	public ArrayList<InsuranceMoney> getAllUnprocessed(InsuranceMoneyList insuranceMoneyList) {
		return compensationModel.getAllUnprocessed(insuranceMoneyList);
	}
	public ArrayList<InsuranceMoney> getAllProcessed(InsuranceMoneyList insuranceMoneyList) {
		return compensationModel.getAllProcessed(insuranceMoneyList);
	}
	public InsuranceMoney get(InsuranceMoneyList insuranceMoneyList, int id) throws NotExistException {
		return compensationModel.get(insuranceMoneyList, id);
	}
	public Contract get(ContractList contractList, int contractId) throws NotExistContractException {
		return compensationModel.get(contractList, contractId);
	}
	public Customer get(CustomerList customerList, int customerID) throws NotExistException {
		return compensationModel.get(customerList, customerID);
	}
	public ArrayList<Report> getAll(ReportList reportList) {
		return compensationModel.getAll(reportList);
	}
	public Report get(ReportList reportList, int id) throws NotExistException {
		return compensationModel.get(reportList, id);
	}
	public ArrayList<Report> getAllUnprocessedReport(ReportList reportList) {
		return compensationModel.getAllUnprocessedReport(reportList);
	}
	public ArrayList<Report> getAllCompletedReport(ReportList reportList) {
		return compensationModel.getAllCompletedReport(reportList);
	}
	public Contract getAutomobileByMember(ContractList contractList, int customerID) throws NotExistContractException {
		return compensationModel.getAutomobileByMember(contractList, customerID);
	}
}
