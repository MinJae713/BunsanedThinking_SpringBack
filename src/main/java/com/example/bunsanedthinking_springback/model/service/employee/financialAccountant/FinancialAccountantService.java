package com.example.bunsanedthinking_springback.model.service.employee.financialAccountant;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailDModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;

@Service
public class FinancialAccountantService {
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private DepositDetailDModel depositDetailDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;

	public DepositDetail getDepositDetail(int id) throws NotExistException {
		DepositDetail depositDetail = depositDetailDModel.getById(id);
		if (depositDetail == null)
			throw new NotExistException("해당하는 입금 내역 정보가 존재하지 않습니다.");
		return depositDetail;
	}

	public void getTaxPaymentDetail() {
		//
	}

	public void handlePayment(int paymentDetailId) throws NotExistException, AlreadyProcessedException {
		// TODO if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		PaymentDetail paymentDetail = paymentDetailEntityModel.getById(paymentDetailId);
		if (paymentDetail == null) {
			throw new NotExistException("해당하는 지급 사항 정보가 존재하지 않습니다.");
		}
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		paymentDetail.setProcessStatus(PaymentProcessStatus.Completed);
		paymentDetailEntityModel.update(paymentDetail);
	}

	public List<PaymentDetail> getAllPaymentDetail() {
		return paymentDetailEntityModel.getAll();
	}

	public List<PaymentDetail> getAllUnprocessedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Unprocessed);
	}

	public List<PaymentDetail> getAllCompletedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Completed);
	}

	private List<PaymentDetail> getAllPaymentDetailByProcessStatus(PaymentProcessStatus processStatus) {
		List<PaymentDetail> paymentDetailList = paymentDetailEntityModel.getAll();
		return paymentDetailList.stream()
			.filter(paymentDetail -> paymentDetail.getProcessStatus() == processStatus)
			.collect(Collectors.toList());
	}

	public PaymentDetail getPaymentDetail(int id) throws NotExistException {
		PaymentDetail paymentDetail = paymentDetailEntityModel.getById(id);
		if (paymentDetail == null)
			throw new NotExistException("해당하는 지급 사항 정보가 존재하지 않습니다.");
		return paymentDetail;
	}

	public Contract getContract(int id) throws NotExistContractException {
		Contract contract = contractDModel.getById(id);
		if (contract == null) {
			throw new NotExistContractException();
		}
		return contract;
	}

	public Customer getCustomer(int id) throws NotExistException {
		Customer customer = customerDModel.getById(id);
		if (customer == null)
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return customer;
	}

	public List<DepositDetail> getAllDepositDetail() {
		return depositDetailDModel.getAll();
	}
}
