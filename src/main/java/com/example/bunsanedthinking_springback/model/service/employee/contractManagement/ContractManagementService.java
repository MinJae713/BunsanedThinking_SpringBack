package com.example.bunsanedthinking_springback.model.service.employee.contractManagement;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementStatus;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractStatus;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.revival.RevivalStatus;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementDModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalDModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationDModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
@Service
public class ContractManagementService {
    @Autowired
    private CustomerDModel customerDModel;
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private TerminationDModel terminationDModel;
	@Autowired
	private EndorsementDModel endorsementDModel;
	@Autowired
	private RevivalDModel revivalDModel;
	@Autowired
	private RecontractDModel recontractDModel;
	@Autowired
	private PaymentDetailDModel paymentDetailDModel;

    public void requestTerminationFee(int tercontractId, int customerId)
		throws NotExistContractException, AlreadyProcessedException, NotExistException {
		// TerminationStatus가 Complete인지 확인
		// totalMoney를 전체 DepositDetail 리스트를 받아와서 계산 - 계산 방법은 아래 참고
		// PaymentDetail 추가
		// termination의 terminationStatus, applyDate 수정
		// termination의 originContract 해당 Contract의 terminationDate, contractStatus 수정
		Customer customer = customerDModel.getById(customerId);
		if (customer == null) throw new NotExistException();
		Termination tercontract = terminationDModel.getById(tercontractId);
		if (tercontract == null) throw new NotExistContractException();
		if (tercontract.getTerminationStatus() == TerminationStatus.Completed)
			throw new AlreadyProcessedException();
		List<DepositDetail> depositDetailList = tercontract.getDepositDetailList();
		int totalMoney = 0;
		for (DepositDetail depositDetail : depositDetailList)
			totalMoney += depositDetail.getMoney();
		totalMoney = (int) (totalMoney * 0.3);
		int paymentId = paymentDetailDModel.getAll().isEmpty() ?
				Integer.parseInt(PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER+"1") :
				NextIdGetter.getNextId(paymentDetailDModel.getMaxId(), PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail paymentDetail = new PaymentDetail(customer.getName(), customer.getBankName(),
				customer.getBankAccount(), totalMoney, PaymentType.AccountTransfer, tercontract.getId());
		paymentDetail.setId(paymentId);
		paymentDetailDModel.add(paymentDetail);
		Contract contract = tercontract.getOriginalContract();
		tercontract.setTerminationStatus(TerminationStatus.Completed);
		tercontract.setApplyDate(new Date());
		terminationDModel.update(tercontract);
		contract.setTerminationDate(tercontract.getApplyDate());
		contract.setContractStatus(ContractStatus.Terminating);
		contractDModel.update(contract);

		//      if (tercontract.getTerminationStatus() == TerminationStatus.Completed) {
		//		    throw new AlreadyProcessedException();
		//		}
		//		ArrayList<DepositDetail> depositDetailList = tercontract.getDepositDetailList();
		//		int totalMoney = 0;
		//		for (DepositDetail depositDetail : depositDetailList) {
		//			totalMoney += depositDetail.getMoney();
		//		}
		//		totalMoney = (int) (totalMoney * 0.3);
		//		PaymentDetail paymentDetail = new PaymentDetail(customer.getName(), customer.getBankName(),
		//				customer.getBankAccount(), totalMoney, PaymentType.AccountTransfer, tercontract.getId());
		//		paymentDetailList.add(paymentDetail);
		//		Contract contract = tercontract.getOriginalContract();
		//		tercontract.setTerminationStatus(TerminationStatus.Completed);
		//		tercontract.setApplyDate(new Date());
		//		contract.setTerminationDate(tercontract.getApplyDate());
		//		contract.setContractStatus(ContractStatus.Terminating);
		//		contractList.update(contract);
	}

	public void reviewEndorsement(int endorsementId, int index) throws NotExistException {
		Endorsement encontract = endorsementDModel.getById(endorsementId);
		if (encontract == null) throw new NotExistException();
		if (index == 1) {
			encontract.setEndorsementStatus(EndorsementStatus.Completed);
			endorsementDModel.update(encontract);
		} else if (index == 2) {
			encontract.setEndorsementStatus(EndorsementStatus.Unprocessed);
			endorsementDModel.update(encontract);
		}

//		if (index == 1) {
//			encontract.setPaymentDate(encontract.getPaymentDate());
//			encontract.setEndorsementStatus(EndorsementStatus.Completed);
//		} else if (index == 2) {
//			encontract.setEndorsementStatus(EndorsementStatus.Unprocessed);
//		}
	}

	public void reviewRecontract(int recontractId, int index) throws NotExistContractException, NotExistException {
		Recontract recontract = recontractDModel.getById(recontractId);
		if (recontract == null) throw new NotExistContractException();
		if (index == 1) {
//			Contract contract = recontract.getOriginalContract();
			Insurance product = (Insurance) recontract.getProduct();
			recontract.setContractStatus(ContractStatus.Maintaining);
			recontract.setDate(new Date());

			LocalDate currentDate = LocalDate.now();
			LocalDate expirationDate = currentDate.plusYears(product.getContractPeriod());
			recontract.setExpirationDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//			contractDModel.update(contract);
			recontract.setRecontractStatus(RecontractStatus.Completed);
			recontractDModel.update(recontract);
		} else if (index == 2) {
			recontract.setRecontractStatus(RecontractStatus.Unprocessed);
			recontractDModel.update(recontract);
		}

//		if (index == 1) {
//			Contract contract = recontract.getOriginalContract().clone();
//			Insurance product = (Insurance) recontract.getOriginalContract().getProduct();
//			contract.setContractStatus(ContractStatus.Maintaining);
//			contract.setDate(new Date());
//
//			LocalDate currentDate = LocalDate.now();
//			LocalDate expirationDate = currentDate.plusYears(product.getContractPeriod());
//			contract.setExpirationDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//			contractList.update(contract);
//			recontract.setRecontractStatus(RecontractStatus.Completed);
//		} else if (index == 2) {
//			recontract.setRecontractStatus(RecontractStatus.Unprocessed);
//		}
	}

	public void reviewRevival(int revivalId, int index) throws NotExistContractException {
		Revival revival = revivalDModel.getById(revivalId);
		if (revival == null) throw new NotExistContractException();
		if (index == 1) {
//			Contract contract = revival.getOriginalContract();
			revival.setContractStatus(ContractStatus.Maintaining);
			revival.setDate(new Date());
//			contractDModel.update(contract);
			revival.setRevivalStatus(RevivalStatus.Completed);
			revivalDModel.update(revival);
		} else if (index == 2) {
			revival.setRevivalStatus(RevivalStatus.Unprocessed);
			revivalDModel.update(revival);
		}

//		if (index == 1) {
//			Contract contract = revivalcontract.getOriginalContract().clone();
//			contract.setContractStatus(ContractStatus.Maintaining);
//			contract.setDate(new Date());
//			contractList.update(contract);
//			revivalcontract.setRevivalStatus(RevivalStatus.Completed);
//		} else if (index == 2) {
//			revivalcontract.setRevivalStatus(RevivalStatus.Unprocessed);
//		}
	}

	// 여기부터 get - 아래는 구현 완
	public List<Contract> getAllDefaultContract() throws NotExistContractException, NotExistException {
		return contractDModel.getAll();
		//		return contractList.getAllDefaultContract();
	}
    // 여기부터 Customer 하나 찾기(contract 찾는건 아래에 구현됨)
    public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
        return customerDModel.getById(id);
    }
    public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
        return contractDModel.getById(contractId);
    }
	public Termination getTerminationById(int id) throws NotExistException, NotExistContractException {
        return terminationDModel.getById(id);
//		return terminationList.get(id);
	}

	public List<Termination> getAllTerminatingContract() throws NotExistContractException, NotExistException {
		return terminationDModel.getAll();
		//		return terminationList.getAllTerminatingContract();
	}

	public Termination getTerminatingContractById(int id) throws NotExistContractException, NotExistException {
		return getTerminationById(id);
		//      return terminationList.getTerminatingContractById(id);
	}

	public List<Termination> getAllUnprocessedTerminatingContract() throws
		NotExistContractException,
		NotExistException {
		return terminationDModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Unprocessed)
				.toList();
		//        return terminationList.getAllUnprocessedTerminatingContract();
	}

	public List<Termination> getAllProcessedTerminatingContract() throws NotExistContractException, NotExistException {
		return terminationDModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Completed)
				.toList();
		//		return terminationList.getAllProcessedTerminatingContract();
	}

	public List<Endorsement> getAllEndorsementContract() throws NotExistContractException, NotExistException {
        return endorsementDModel.getAll();
//		return endorsementList.getAllEndorsementContract();
	}

	public List<Endorsement> getAllUnprocessedEndorsementContract() throws
		NotExistContractException,
		NotExistException {
		return endorsementDModel.getAll().stream()
				.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Unprocessed)
				.toList();
	}

	public List<Endorsement> getAllProcessedEndorsementContract() throws NotExistContractException, NotExistException {
		return endorsementDModel.getAll().stream()
				.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Completed)
				.toList();
	}

	public Endorsement getEndorsementById(int id) throws NotExistException, NotExistContractException {
       return endorsementDModel.getById(id);
//		return endorsementList.get(id);
	}

	public List<Recontract> getAllReContract() throws NotExistContractException, NotExistException {
		return recontractDModel.getAll();
		//		return recontractList.getAllReContract();
	}

	public List<Recontract> getAllUnprocessedReContract() throws NotExistContractException, NotExistException {
		return recontractDModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Unprocessed)
				.toList();
	}

	public List<Recontract> getAllProcessedReContract() throws NotExistContractException, NotExistException {
		return recontractDModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Completed)
				.toList();
	}

	public Recontract getReContractById(int id) throws NotExistException, NotExistContractException {
        return recontractDModel.getById(id);
//		return recontractList.get(id);
	}

	public List<Revival> getAllRevivalContract() throws NotExistContractException, NotExistException {
		return revivalDModel.getAll();
		//		return revivalList.getAllRevivalContract();
	}

	public Revival getRevivalById(int id) throws NotExistException, NotExistContractException {
        return revivalDModel.getById(id);
//		return revivalList.get(id);
	}

	public List<Revival> getAllUnprocessedRevival() throws NotExistContractException, NotExistException {
		return revivalDModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Unprocessed)
				.toList();
	}

	public List<Revival> getAllProcessedRevival() throws NotExistContractException, NotExistException {
		return revivalDModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Completed)
				.toList();
	}

	//    public Recontract getRecontractById(RecontractList recontractList, int id) {
	//        return recontractList.get(id);
	//    } - getReContractById 대체

	//	public Revival get(RevivalList revivalList, int id) {
	//		return revivalList.get(id);
	//	} - getRevivalById 대체
}
