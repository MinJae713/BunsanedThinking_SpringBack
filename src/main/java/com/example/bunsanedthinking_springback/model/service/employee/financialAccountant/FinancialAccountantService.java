package com.example.bunsanedthinking_springback.model.service.employee.financialAccountant;

import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.handlePaymentDetail.HandlePaymentResponse;
import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.viewDepositDetail.ViewDepositResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.global.constants.service.employee.financialAccountant.FinancialAccountantConstants;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialAccountantService {
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private DepositDetailEntityModel depositDetailEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
	@Autowired
	private EmployeeEntityModel employeeEntityModel;

	public ViewDepositResponse getDepositDetail(int id) throws NotExistException {
		DepositDetail depositDetail = depositDetailEntityModel.getById(id);
		if (depositDetail == null)
			throw new NotExistException(FinancialAccountantConstants.DEPOSIT_DETAIL_NOT_FOUND);
		return ViewDepositResponse.from(depositDetail);
	}

	public void getTaxPaymentDetail() {
	}

	public void handlePayment(int paymentDetailId, int employeeId) throws NotExistException, AlreadyProcessedException {
		PaymentDetail paymentDetail = paymentDetailEntityModel.getById(paymentDetailId);
		if (paymentDetail == null) {
			throw new NotExistException(FinancialAccountantConstants.PAYMENT_DETAIL_NOT_FOUND);
		}
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException(FinancialAccountantConstants.PAYMENT_ALREADY_COMPLETED);
		}
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null) {
			throw new NotExistException(FinancialAccountantConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		}
		paymentDetail.setProcessStatus(PaymentProcessStatus.Completed);
		paymentDetail.setEmployeeId(employeeId);
		paymentDetailEntityModel.update(paymentDetail);
	}

	public List<HandlePaymentResponse> getAllPaymentDetail() {
		return paymentDetailEntityModel.getAll().stream()
			.map(HandlePaymentResponse::from)
			.collect(Collectors.toList());
	}

	public List<HandlePaymentResponse> getAllUnprocessedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Unprocessed);
	}

	public List<HandlePaymentResponse> getAllCompletedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Completed);
	}

	private List<HandlePaymentResponse> getAllPaymentDetailByProcessStatus(PaymentProcessStatus processStatus) {
		List<PaymentDetail> paymentDetailList = paymentDetailEntityModel.getAll();
		return paymentDetailList.stream()
			.filter(paymentDetail -> paymentDetail.getProcessStatus() == processStatus)
			.map(HandlePaymentResponse::from)
			.collect(Collectors.toList());
	}

	public HandlePaymentResponse getPaymentDetail(int id) throws NotExistException {
		PaymentDetail paymentDetail = paymentDetailEntityModel.getById(id);
		if (paymentDetail == null)
			throw new NotExistException(FinancialAccountantConstants.PAYMENT_DETAIL_NOT_FOUND);
		return HandlePaymentResponse.from(paymentDetail);
	}

	public Contract getContract(int id) throws NotExistContractException {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null) {
			throw new NotExistContractException();
		}
		return contract;
	}

	public Customer getCustomer(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
		if (customer == null)
			throw new NotExistException(FinancialAccountantConstants.CUSTOMER_INFORMATION_NOT_FOUND);
		return customer;
	}

	public List<ViewDepositResponse> getAllDepositDetail() {
		return depositDetailEntityModel.getAll().stream()
			.map(ViewDepositResponse::from)
			.collect(Collectors.toList());
	}

	public Employee getEmployee(int employeeId) throws NotExistException {
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null)
			throw new NotExistException(FinancialAccountantConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		return employee;
	}
}
