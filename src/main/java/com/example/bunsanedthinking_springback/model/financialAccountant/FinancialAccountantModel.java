package com.example.bunsanedthinking_springback.model.financialAccountant;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FinancialAccountantModel {

	public DepositDetail getDepositDetail(DepositDetailList depositDetailList, int id) throws NotExistException{
		return depositDetailList.get(id);
	}

	public void getTaxPaymentDetail(){

	}
	public void handlePayment(PaymentDetail paymentDetail, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException {
		// if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		paymentDetail.handle();
		paymentDetailList.update(paymentDetail);
	}
	public ArrayList<PaymentDetail> getAllPaymentDetail(PaymentDetailList paymentDetailList) {
		return paymentDetailList.getAllPaymentDetail();
	}
	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail(PaymentDetailList paymentDetailList) {
		return paymentDetailList.getAllUnprocessedPaymentDetail();
	}
	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail(PaymentDetailList paymentDetailList) {
		return paymentDetailList.getAllCompletedPaymentDetail();
	}
	public PaymentDetail get(PaymentDetailList paymentDetailList, int index) throws NotExistException {
		return paymentDetailList.get(index);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return customerList.get(id);
	}
	public ArrayList<DepositDetail> getAllDepositDetail(DepositDetailList depositDetailList) {
		return depositDetailList.getAllDepositDetail();
	}
}