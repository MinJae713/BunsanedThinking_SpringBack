package com.example.bunsanedthinking_springback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.financialAccountant.FinancialAccountantModel;

@RestController
@RequestMapping("/employee/financialAccountant")
public class FinancialAccountantController {
	@Autowired
	private FinancialAccountantModel financialAccountantModel;

	@GetMapping("/getDepositDetail")
	public DepositDetail getDepositDetail(@RequestParam("depositDetailId") int depositDetailId) throws
		NotExistException {
		return financialAccountantModel.getDepositDetail(depositDetailId);
	}

	public void getTaxPaymentDetail() {
		financialAccountantModel.getTaxPaymentDetail();
	}

	@PatchMapping("/handlePayment")
	public void handlePayment(@RequestParam("paymentDetailId") int paymentDetailId) throws
		NotExistException,
		AlreadyProcessedException {
		financialAccountantModel.handlePayment(paymentDetailId);
	}

	@GetMapping("/getAllPaymentDetail")
	public List<PaymentDetail> getAllPaymentDetail() {
		return financialAccountantModel.getAllPaymentDetail();
	}

	@GetMapping("/getAllUnprocessedPaymentDetail")
	public List<PaymentDetail> getAllUnprocessedPaymentDetail() {
		return financialAccountantModel.getAllUnprocessedPaymentDetail();
	}

	@GetMapping("/getAllCompletedPaymentDetail")
	public List<PaymentDetail> getAllCompletedPaymentDetail() {
		return financialAccountantModel.getAllCompletedPaymentDetail();
	}

	@GetMapping("/getPaymentDetail")
	public PaymentDetail getPaymentDetail(@RequestParam("paymentDetailId") int paymentDetailId) throws
		NotExistException {
		return financialAccountantModel.getPaymentDetail(paymentDetailId);
	}

	@GetMapping("/getContract")
	public Contract getContract(@RequestParam("contractId") int contractId) throws NotExistContractException {
		return financialAccountantModel.getContract(contractId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerId) throws NotExistException {
		return financialAccountantModel.getCustomer(customerId);
	}

	@GetMapping("/getAllDepositDetail")
	public List<DepositDetail> getAllDepositDetail() {
		return financialAccountantModel.getAllDepositDetail();
	}
}
