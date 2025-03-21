package com.example.bunsanedthinking_springback.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.handlePaymentDetail.HandlePaymentResponse;
import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.viewDepositDetail.ViewDepositResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.financialAccountant.FinancialAccountantService;

@RestController
@RequestMapping("/employee/financialAccountant")
public class FinancialAccountantController {
	@Autowired
	private FinancialAccountantService financialAccountantService;

	@GetMapping("/getDepositDetail")
	public ViewDepositResponse getDepositDetail(@RequestParam("depositDetailId") int depositDetailId) throws
		NotExistException {
		return financialAccountantService.getDepositDetail(depositDetailId);
	}

	public void getTaxPaymentDetail() {
		financialAccountantService.getTaxPaymentDetail();
	}

	@PatchMapping("/handlePayment")
	public void handlePayment(@RequestParam("paymentDetailId") int paymentDetailId,
		@RequestParam("employeeId") int employeeId) throws
		NotExistException,
		AlreadyProcessedException {
		financialAccountantService.handlePayment(paymentDetailId, employeeId);
	}

	@GetMapping("/getAllPaymentDetail")
	public List<HandlePaymentResponse> getAllPaymentDetail() {
		return financialAccountantService.getAllPaymentDetail();
	}

	@GetMapping("/getAllUnprocessedPaymentDetail")
	public List<HandlePaymentResponse> getAllUnprocessedPaymentDetail() {
		return financialAccountantService.getAllUnprocessedPaymentDetail();
	}

	@GetMapping("/getAllCompletedPaymentDetail")
	public List<HandlePaymentResponse> getAllCompletedPaymentDetail() {
		return financialAccountantService.getAllCompletedPaymentDetail();
	}

	@GetMapping("/getPaymentDetail")
	public HandlePaymentResponse getPaymentDetail(@RequestParam("paymentDetailId") int paymentDetailId) throws
		NotExistException {
		return financialAccountantService.getPaymentDetail(paymentDetailId);
	}

	@GetMapping("/getContract")
	public Contract getContract(@RequestParam("contractId") int contractId) throws NotExistContractException {
		return financialAccountantService.getContract(contractId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerId) throws NotExistException {
		return financialAccountantService.getCustomer(customerId);
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		return financialAccountantService.getEmployee(employeeId);
	}

	@GetMapping("/getAllDepositDetail")
	public List<ViewDepositResponse> getAllDepositDetail() {
		return financialAccountantService.getAllDepositDetail();
	}
}
