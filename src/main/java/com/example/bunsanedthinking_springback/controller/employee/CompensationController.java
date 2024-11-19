package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.compensation.request.CompensationRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.request.InsuranceMoneyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.GetAllInsuranceMoneyResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.GetAllReportResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.compensation.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/compensation")
public class CompensationController {
	@Autowired
	private CompensationService compensationSModel;

	@PatchMapping("/requestCompensation")
	public void requestCompensation(@RequestBody CompensationRequest compensationRequest)
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
		compensationSModel.requestCompensation(compensationRequest);
	}

	@PatchMapping("/requestInsuranceMoney")
	public void requestInsuranceMoney(@RequestBody InsuranceMoneyRequest insuranceMoneyRequest)
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
		compensationSModel.requestInsuranceMoney(insuranceMoneyRequest);
	}
	@GetMapping("/getAllInsuranceMoney")
	public List<GetAllInsuranceMoneyResponse> getAllInsuranceMoney() {
		return compensationSModel.getAllInsuranceMoney();
	}

	@GetMapping("/getAllUnprocessedInsuranceMoney")
	public List<GetAllInsuranceMoneyResponse> getAllUnprocessedInsuranceMoney() {
		return compensationSModel.getAllUnprocessedInsuranceMoney();
	}

	@GetMapping("/getAllProcessedInsuranceMoney")
	public List<GetAllInsuranceMoneyResponse> getAllProcessedInsuranceMoney() {
		return compensationSModel.getAllProcessedInsuranceMoney();
	}

	@GetMapping("/getInsuranceMoneyById")
	public InsuranceMoney getInsuranceMoneyById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getInsuranceMoneyById(id);
	}

	@GetMapping("/getInsuranceMoneyRowById")
	public GetAllInsuranceMoneyResponse getInsuranceMoneyRowById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getInsuranceMoneyRowById(id);
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
	public List<GetAllReportResponse> getAllReport() {
		return compensationSModel.getAllReport();
	}

	@GetMapping("/getReportById")
	public Report getReportById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getReportById(id);
	}

	@GetMapping("/getReportRowById")
	public GetAllReportResponse getReportRowById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getReportRowById(id);
	}

	@GetMapping("/getAllUnprocessedReport")
	public List<GetAllReportResponse> getAllUnprocessedReport() {
		return compensationSModel.getAllUnprocessedReport();
	}

	@GetMapping("/getAllCompletedReport")
	public List<GetAllReportResponse> getAllCompletedReport() {
		return compensationSModel.getAllCompletedReport();
	}

	@GetMapping("/getAutomobileByCustomerId")
	public Contract getAutomobileByCustomerId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return compensationSModel.getAutomobileByCustomerId(id);
	}
}
