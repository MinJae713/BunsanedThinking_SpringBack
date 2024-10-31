package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.financialAccountant.FinancialAccountantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/financialAccountant")
public class FinancialAccountantController {
	@Autowired
	private FinancialAccountantModel financialAccountantModel;

	public DepositDetail getDepositDetail(DepositDetailList depositDetailList, int id) throws NotExistException{
		return financialAccountantModel.getDepositDetail(depositDetailList, id);
	}
	public void getTaxPaymentDetail(){
		financialAccountantModel.getTaxPaymentDetail();
	}
	public void handlePayment(PaymentDetail paymentDetail, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException {
		financialAccountantModel.handlePayment(paymentDetail, paymentDetailList);
	}
	public ArrayList<PaymentDetail> getAllPaymentDetail(PaymentDetailList paymentDetailList) {
		return financialAccountantModel.getAllPaymentDetail(paymentDetailList);
	}
	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail(PaymentDetailList paymentDetailList) {
		return financialAccountantModel.getAllUnprocessedPaymentDetail(paymentDetailList);
	}
	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail(PaymentDetailList paymentDetailList) {
		return financialAccountantModel.getAllCompletedPaymentDetail(paymentDetailList);
	}
	public PaymentDetail get(PaymentDetailList paymentDetailList, int index) throws NotExistException {
		return financialAccountantModel.get(paymentDetailList, index);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return financialAccountantModel.get(contractList, id);
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return financialAccountantModel.get(customerList, id);
	}
	public ArrayList<DepositDetail> getAllDepositDetail(DepositDetailList depositDetailList) {
		return financialAccountantModel.getAllDepositDetail(depositDetailList);
	}
}
