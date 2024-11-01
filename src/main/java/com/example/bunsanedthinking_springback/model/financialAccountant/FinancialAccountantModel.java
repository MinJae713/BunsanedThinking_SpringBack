package com.example.bunsanedthinking_springback.model.financialAccountant;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialAccountantModel {

	@Autowired
	private DepositDetailMapper depositDetailMapper;
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private ContractMapper contractMapper;

	public DepositDetail getDepositDetail(DepositDetailList depositDetailList, int id) throws NotExistException{
		DepositDetailVO depositDetailVO = depositDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 입금 내역 정보가 존재하지 않습니다."));
		return new DepositDetail(depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(),
			depositDetailVO.getMoney(), DepositPath.indexOf(depositDetailVO.getPath()));
	}

	public void getTaxPaymentDetail(){
		//
	}

	public void handlePayment(PaymentDetail paymentDetail, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException {
		// if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		PaymentDetailVO paymentDetailVO = PaymentDetailVO.from(paymentDetail);
		paymentDetailVO.setProcess_status(PaymentProcessStatus.Completed.ordinal());
		paymentDetailMapper.update_FinancialAccountant(paymentDetailVO);
	}
	public ArrayList<PaymentDetail> getAllPaymentDetail(PaymentDetailList paymentDetailList) {
		ArrayList<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.getAll_FinancialAccountant();
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}
	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail(PaymentDetailList paymentDetailList) {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Unprocessed);
	}
	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail(PaymentDetailList paymentDetailList) {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Completed);
	}
	private ArrayList<PaymentDetail> getAllPaymentDetailByProcessStatus(PaymentProcessStatus processStatus) {
		ArrayList<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.findByProcessStatus_FinancialAccountant(processStatus.ordinal());
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}
	public PaymentDetail get(PaymentDetailList paymentDetailList, int id) throws NotExistException {
		PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 지급사항 정보를 찾을 수 없습니다."));
		return new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
			paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
			PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
			paymentDetailVO.getEmployee_id());
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return customerList.get(id);
	}
	public ArrayList<DepositDetail> getAllDepositDetail(DepositDetailList depositDetailList) {
		ArrayList<DepositDetail> result = new ArrayList<>();
		List<DepositDetailVO> depositDetailVOList = depositDetailMapper.getAll_FinancialAccountant();
		for (DepositDetailVO depositDetailVO : depositDetailVOList) {
			result.add(new DepositDetail(depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(), depositDetailVO.getMoney(),
				DepositPath.indexOf(depositDetailVO.getPath())));
		}
		return result;
	}
}
