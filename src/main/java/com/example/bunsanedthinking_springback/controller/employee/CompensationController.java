package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.compensation.request.CompensationRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.request.InsuranceMoneyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestCompensationResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestInsuranceMoneyDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestInsuranceMoneyResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.compensation.CompensationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/compensation")
public class CompensationController {
	@Autowired
	private CompensationService compensationSModel;

	@PatchMapping("/requestCompensation")
	public void requestCompensation(@RequestBody @Valid CompensationRequest compensationRequest)
            throws NotExistException, AlreadyProcessedException, NotExistContractException {
		/*
		{
			"money": 3000000,
			"reportId": 4002,
			"paymentType": 0
		}
		 */
		compensationSModel.requestCompensation(compensationRequest);
	}

	@PatchMapping("/requestInsuranceMoney")
	public void requestInsuranceMoney(@RequestBody @Valid InsuranceMoneyRequest insuranceMoneyRequest)
            throws NotExistException, AlreadyProcessedException, NotExistContractException {
		/*
		{
			"money": 185000,
			"insuranceMoneyId": 9302,
			"paymentType": 0
		}
		 */
		compensationSModel.requestInsuranceMoney(insuranceMoneyRequest);
	}
	@GetMapping("/getAllInsuranceMoney")
	public List<RequestInsuranceMoneyResponse> getAllInsuranceMoney() {
		return compensationSModel.getAllInsuranceMoney();
	}

	@GetMapping("/getAllUnprocessedInsuranceMoney")
	public List<RequestInsuranceMoneyResponse> getAllUnprocessedInsuranceMoney() {
		return compensationSModel.getAllUnprocessedInsuranceMoney();
	}

	@GetMapping("/getAllProcessedInsuranceMoney")
	public List<RequestInsuranceMoneyResponse> getAllProcessedInsuranceMoney() {
		return compensationSModel.getAllProcessedInsuranceMoney();
	}

	@GetMapping("/getInsuranceMoneyById")
	public RequestInsuranceMoneyDetailResponse getInsuranceMoneyById(@RequestParam int id)
			throws NotExistException, NotExistContractException {
		return compensationSModel.getInsuranceMoneyById(id);
	}

	@GetMapping("/getInsuranceMoneyRowById")
	public RequestInsuranceMoneyResponse getInsuranceMoneyRowById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getInsuranceMoneyRowById(id);
	}

	@GetMapping("/getContractById")
	public Contract getContractById(@RequestParam int id) throws NotExistContractException {
		return compensationSModel.getContractById(id);
	}

	@GetMapping("/getCustomerById")
	public Customer getCustomerById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getCustomerById(id);
	}

	@GetMapping("/getAllReport")
	public List<RequestCompensationResponse> getAllReport() {
		return compensationSModel.getAllReport();
	}

	@GetMapping("/getReportById")
	public Report getReportById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getReportById(id);
	}

	@GetMapping("/getReportRowById")
	public RequestCompensationResponse getReportRowById(@RequestParam int id) throws NotExistException {
		return compensationSModel.getReportRowById(id);
	}

	@GetMapping("/getAllUnprocessedReport")
	public List<RequestCompensationResponse> getAllUnprocessedReport() {
		return compensationSModel.getAllUnprocessedReport();
	}

	@GetMapping("/getAllCompletedReport")
	public List<RequestCompensationResponse> getAllCompletedReport() {
		return compensationSModel.getAllCompletedReport();
	}

	@GetMapping("/getAutomobileByCustomerId")
	public Contract getAutomobileByCustomerId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return compensationSModel.getAutomobileByCustomerId(id);
	}
}
