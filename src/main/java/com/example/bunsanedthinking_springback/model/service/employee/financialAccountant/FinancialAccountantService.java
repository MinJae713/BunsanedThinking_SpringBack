package com.example.bunsanedthinking_springback.model.service.employee.financialAccountant;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.handlePaymentDetail.HandlePaymentResponse;
import com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.viewDepositDetail.ViewDepositResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;

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
			throw new NotExistException("해당하는 입금 내역 정보가 존재하지 않습니다.");
		return ViewDepositResponse.from(depositDetail);
	}

	public void getTaxPaymentDetail() {
		//
	}

	public void handlePayment(int paymentDetailId, int employeeId) throws NotExistException, AlreadyProcessedException {
		// TODO if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		PaymentDetail paymentDetail = paymentDetailEntityModel.getById(paymentDetailId);
		if (paymentDetail == null) {
			throw new NotExistException("해당하는 지급 사항 정보가 존재하지 않습니다.");
		}
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null) {
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
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
			throw new NotExistException("해당하는 지급 사항 정보가 존재하지 않습니다.");
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
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return customer;
	}

	public List<ViewDepositResponse> getAllDepositDetail() {
		return depositDetailEntityModel.getAll().stream()
			.map(ViewDepositResponse::from)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}

	public Employee getEmployee(int employeeId) throws NotExistException {
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		return employee;
	}
}
