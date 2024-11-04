package com.example.bunsanedthinking_springback.model.contractManagement;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementList;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementStatus;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractList;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractStatus;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.revival.RevivalList;
import com.example.bunsanedthinking_springback.entity.revival.RevivalStatus;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationList;
import com.example.bunsanedthinking_springback.entity.termination.TerminationStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */

@Service
public class ContractManagementModel {

	public boolean requestTerminationFee(Termination tercontract, Customer customer,
										 PaymentDetailList paymentDetailList, ContractList contractList) throws NotExistContractException, AlreadyProcessedException {
		if (tercontract.getTerminationStatus() == TerminationStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		ArrayList<DepositDetail> depositDetailList = tercontract.getDepositDetailList();
		int totalMoney = 0;
		for (DepositDetail depositDetail : depositDetailList) {
			totalMoney += depositDetail.getMoney();
		}
		totalMoney = (int) (totalMoney * 0.3);
		PaymentDetail paymentDetail = new PaymentDetail(customer.getName(), customer.getBankName(),
				customer.getBankAccount(), totalMoney, PaymentType.AccountTransfer, tercontract.getId(), null);
		paymentDetailList.add(paymentDetail);
		Contract contract = tercontract.getOriginalContract();
		tercontract.setTerminationStatus(TerminationStatus.Completed);
		tercontract.setApplyDate(new Date());
		contract.setTerminationDate(tercontract.getApplyDate());
		contract.setContractStatus(ContractStatus.Terminating);
		contractList.update(contract);
		return true;
	}

	public boolean reviewEndorsement(Endorsement encontract, Customer customer, int index) {
		if (index == 1) {
			encontract.setPaymentDate(encontract.getPaymentDate());
			encontract.setEndorsementStatus(EndorsementStatus.Completed);
		} else if (index == 2) {
			encontract.setEndorsementStatus(EndorsementStatus.Unprocessed);
		}
		return true;
	}

	public boolean reviewRecontract(ContractList contractList, Recontract recontract, Customer customer, int index) throws NotExistContractException {
		if (index == 1) { // �듅�씤
			Contract contract = recontract.getOriginalContract().clone();
			Insurance product = (Insurance) recontract.getOriginalContract().getProduct();
			contract.setContractStatus(ContractStatus.Maintaining);
			contract.setDate(new Date());

			LocalDate currentDate = LocalDate.now();
			LocalDate expirationDate = currentDate.plusYears(product.getContractPeriod());
			contract.setExpirationDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			contractList.update(contract);
			recontract.setRecontractStatus(RecontractStatus.Completed);
		} else if (index == 2) { // 嫄곗젅
			recontract.setRecontractStatus(RecontractStatus.Unprocessed);
		}
		return true;
	}

	public boolean reviewRevival(ContractList contractList, Revival revivalcontract, Customer customer, int index) {
		if (index == 1) {
			Contract contract = revivalcontract.getOriginalContract().clone();
			contract.setContractStatus(ContractStatus.Maintaining);
			contract.setDate(new Date());
			try {
				contractList.update(contract);
			} catch (NotExistContractException e) {
				e.printStackTrace();
			}
			revivalcontract.setRevivalStatus(RevivalStatus.Completed);
		} else if (index == 2) {
			revivalcontract.setRevivalStatus(RevivalStatus.Unprocessed);
		}
		return true;
	}
	public ArrayList<Contract> getAllDefaultContract(ContractList contractList) {
		return contractList.getAllDefaultContract();
	}

	public Customer get(CustomerList customerList, Contract contract) throws NotExistException {
		return customerList.get(contract.getCustomerID());
	}

	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}

	public Termination get(TerminationList terminationList, int id) {
		return terminationList.get(id);
	}

	public ArrayList<Termination> getAllTerminatingContract(TerminationList terminationList) {
		return terminationList.getAllTerminatingContract();
	}

	public Termination getTerminatingContractById(TerminationList terminationList,int id) {
		return terminationList.getTerminatingContractById(id);
	}

	public ArrayList<Termination> getAllUnprocessedTerminatingContract(TerminationList terminationList) {
		return terminationList.getAllUnprocessedTerminatingContract();
	}

	public ArrayList<Termination> getAllProcessedTerminatingContract(TerminationList terminationList) {
		return terminationList.getAllProcessedTerminatingContract();
	}

	public ArrayList<Endorsement> getAllEndorsementContract(EndorsementList endorsementList) {
		return endorsementList.getAllEndorsementContract();
	}

	public ArrayList<Endorsement> getAllUnprocessedEndorsementContract(EndorsementList endorsementList) {
		return endorsementList.getAllUnprocessedEndorsementContract();
	}

	public ArrayList<Endorsement> getAllProcessedEndorsementContract(EndorsementList endorsementList) {
		return endorsementList.getAllProcessedEndorsementContract();
	}

	public Endorsement get(EndorsementList endorsementList, int id) {
		return endorsementList.get(id);
	}

	public ArrayList<Recontract> getAllReContract(RecontractList recontractList) {
		return recontractList.getAllReContract();
	}

	public ArrayList<Recontract> getAllUnprocessedReContract(RecontractList recontractList) {
		return recontractList.getAllUnprocessedReContract();
	}

	public ArrayList<Recontract> getAllProcessedReContract(RecontractList recontractList) {
		return recontractList.getAllProcessedReContract();
	}

	public Recontract getReContractById(RecontractList recontractList, int id) {
		return recontractList.getReContractById(id);
	}

	public Recontract get(RecontractList recontractList, int id) {
		return recontractList.get(id);
	}

	public ArrayList<Revival> getAllRevivalContract(RevivalList revivalList) {
		return revivalList.getAllRevivalContract();
	}

	public Revival getRevivalById(RevivalList revivalList, int id) {
		return revivalList.getRevivalById(id);
	}

	public ArrayList<Revival> getAllUnprocessedRevival(RevivalList revivalList) {
		return revivalList.getAllUnprocessedRevival();
	}

	public ArrayList<Revival> getAllProcessedRevival(RevivalList revivalList) {
		return revivalList.getAllProcessedRevival();
	}

	public Revival get(RevivalList revivalList, int id) {
		return revivalList.get(id);
	}
}
