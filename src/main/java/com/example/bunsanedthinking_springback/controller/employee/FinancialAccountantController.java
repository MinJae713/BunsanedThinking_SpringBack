package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.financialAccountant.FinancialAccountantSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/financialAccountant")
public class FinancialAccountantController {
	@Autowired
	private FinancialAccountantSModel financialAccountantSModel;

	@GetMapping("/getDepositDetail")
	public DepositDetail getDepositDetail(@RequestParam("depositDetailId") int depositDetailId) throws
		NotExistException {
		return financialAccountantSModel.getDepositDetail(depositDetailId);
	}

	public void getTaxPaymentDetail() {
		financialAccountantSModel.getTaxPaymentDetail();
	}

	@PatchMapping("/handlePayment")
	public void handlePayment(@RequestParam("paymentDetailId") int paymentDetailId) throws
		NotExistException,
		AlreadyProcessedException {
		financialAccountantSModel.handlePayment(paymentDetailId);
	}

	@GetMapping("/getAllPaymentDetail")
	public List<PaymentDetail> getAllPaymentDetail() {
		return financialAccountantSModel.getAllPaymentDetail();
	}

	@GetMapping("/getAllUnprocessedPaymentDetail")
	public List<PaymentDetail> getAllUnprocessedPaymentDetail() {
		return financialAccountantSModel.getAllUnprocessedPaymentDetail();
	}

	@GetMapping("/getAllCompletedPaymentDetail")
	public List<PaymentDetail> getAllCompletedPaymentDetail() {
		return financialAccountantSModel.getAllCompletedPaymentDetail();
	}

	@GetMapping("/getPaymentDetail")
	public PaymentDetail getPaymentDetail(@RequestParam("paymentDetailId") int paymentDetailId) throws
		NotExistException {
		return financialAccountantSModel.getPaymentDetail(paymentDetailId);
	}

	@GetMapping("/getContract")
	public Contract getContract(@RequestParam("contractId") int contractId) throws NotExistContractException {
		return financialAccountantSModel.getContract(contractId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerId) throws NotExistException {
		return financialAccountantSModel.getCustomer(customerId);
	}

	@GetMapping("/getAllDepositDetail")
	public List<DepositDetail> getAllDepositDetail() {
		return financialAccountantSModel.getAllDepositDetail();
	}
}
