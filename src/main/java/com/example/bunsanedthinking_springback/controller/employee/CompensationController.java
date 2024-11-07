package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.compensation.ReqCompensationDTO;
import com.example.bunsanedthinking_springback.dto.compensation.ReqInsuranceMoneyDTO;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.compensation.CompensationSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/compensation")
public class CompensationController {
	@Autowired
	private CompensationSModel compensationSModel;

	//	public void requestCompensation(String accountHolder, String bank, String bankAccount, int money,
	//									PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList, Report report, ReportList reportList, AccidentList accidentList)
	//					throws NotExistException, AlreadyProcessedException {
	//		compensationModel.requestCompensation(accountHolder, bank, bankAccount, money, paymentType, contractId, paymentDetailList, report, reportList, accidentList);
	//	}
	//	public void requestInsuranceMoney(Customer customer, int money, InsuranceMoney insuranceMoney, InsuranceMoneyList insuranceMoneyList,
	//									  PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException{
	//		compensationModel.requestInsuranceMoney(customer, money, insuranceMoney, insuranceMoneyList, paymentType, contractId, paymentDetailList);
	//	}
	//	public ArrayList<InsuranceMoney> getAllInsuranceMoney(InsuranceMoneyList insuranceMoneyList) {
	//		return compensationModel.getAllInsuranceMoney(insuranceMoneyList);
	//	}
	//	public ArrayList<InsuranceMoney> getAllUnprocessedInsuranceMoney(InsuranceMoneyList insuranceMoneyList) {
	//		return compensationModel.getAllUnprocessedInsuranceMoney(insuranceMoneyList);
	//	}
	//	public ArrayList<InsuranceMoney> getAllProcessedInsuranceMoney(InsuranceMoneyList insuranceMoneyList) {
	//		return compensationModel.getAllProcessedInsuranceMoney(insuranceMoneyList);
	//	}
	//	public InsuranceMoney getInsuranceMoneyById(InsuranceMoneyList insuranceMoneyList, int id) throws NotExistException {
	//		return compensationModel.getInsuranceMoneyById(insuranceMoneyList, id);
	//	}
	//	public Contract getContractById(ContractList contractList, int contractId) throws NotExistContractException {
	//		return compensationModel.getContractById(contractList, contractId);
	//	}
	//	public Customer getCustomerById(CustomerList customerList, int customerID) throws NotExistException {
	//		return compensationModel.getCustomerById(customerList, customerID);
	//	}
	//	public ArrayList<Report> getAllReport(ReportList reportList) {
	//		return compensationModel.getAllReport(reportList);
	//	}
	//	public Report getReportById(ReportList reportList, int id) throws NotExistException {
	//		return compensationModel.getReportById(reportList, id);
	//	}
	//	public ArrayList<Report> getAllUnprocessedReport(ReportList reportList) {
	//		return compensationModel.getAllUnprocessedReport(reportList);
	//	}
	//	public ArrayList<Report> getAllCompletedReport(ReportList reportList) {
	//		return compensationModel.getAllCompletedReport(reportList);
	//	}
	//	public Contract getAutomobileByCustomerId(ContractList contractList, int customerID) throws NotExistContractException, NotExistException {
	//		return compensationModel.getAutomobileByCustomerId(contractList, customerID);
	//	}
	@PatchMapping("/requestCompensation")
	public void requestCompensation(@RequestBody ReqCompensationDTO reqCompensationDTO)
		throws NotExistException, AlreadyProcessedException {
		/*
		{
			"accountHolder": "김찬",
			"bank": "모환",
			"bankAccount": "김찬어카운트",
			"money": 3000000,
			"contractId": 1001,
			"reportId": 4002,
			"paymentType": 0
		}
		 */
		compensationSModel.requestCompensation(reqCompensationDTO);
	}

	@PatchMapping("/requestInsuranceMoney")
	public void requestInsuranceMoney(@RequestBody ReqInsuranceMoneyDTO reqInsuranceMoneyDTO)
		throws NotExistException, AlreadyProcessedException {
		/*
		{
			"customerId": 2001,
			"money": 185000,
			"insuranceMoneyId": 2,
			"paymentType": 0,
			"contractId": 1001
		}
		 */
		compensationSModel.requestInsuranceMoney(reqInsuranceMoneyDTO);
	}

	@GetMapping("/getAllInsuranceMoney")
	public List<InsuranceMoney> getAllInsuranceMoney() {
		return compensationSModel.getAllInsuranceMoney();
	}

	@GetMapping("/getAllUnprocessedInsuranceMoney")
	public List<InsuranceMoney> getAllUnprocessedInsuranceMoney() {
		return compensationSModel.getAllUnprocessedInsuranceMoney();
	}

	@GetMapping("/getAllProcessedInsuranceMoney")
	public List<InsuranceMoney> getAllProcessedInsuranceMoney() {
		return compensationSModel.getAllProcessedInsuranceMoney();
	}

	@GetMapping("/getInsuranceMoneyById")
	public InsuranceMoney getInsuranceMoneyById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getInsuranceMoneyById(id);
	}

	@GetMapping("/getContractById")
	public Contract getContractById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return compensationSModel.getContractById(id);
	}

	@GetMapping("/getCustomerById")
	public Customer getCustomerById(@RequestParam int id) throws NotExistException, NotExistContractException {
		return compensationSModel.getCustomerById(id);
	}

	@GetMapping("/getAllReport")
	public List<Report> getAllReport() {
		return compensationSModel.getAllReport();
	}

	@GetMapping("/getReportById")
	public Report getReportById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getReportById(id);
	}

	@GetMapping("/getAllUnprocessedReport")
	public List<Report> getAllUnprocessedReport() {
		return compensationSModel.getAllUnprocessedReport();
	}

	@GetMapping("/getAllCompletedReport")
	public List<Report> getAllCompletedReport() {
		return compensationSModel.getAllCompletedReport();
	}

	@GetMapping("/getAutomobileByCustomerId")
	public Contract getAutomobileByCustomerId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return compensationSModel.getAutomobileByCustomerId(id);
	}
}
