package com.example.bunsanedthinking_springback.model.service.employee.contractManagement;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.*;
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
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
@Service
public class ContractManagementService {
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private TerminationEntityModel terminationEntityModel;
	@Autowired
	private EndorsementEntityModel endorsementEntityModel;
	@Autowired
	private RevivalEntityModel revivalEntityModel;
	@Autowired
	private RecontractEntityModel recontractEntityModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
	@Autowired
	private InsuranceEntityModel insuranceEntityModel;

	@Value("${serials.paymentDetail}")
	public Integer PAYMENT_DETAIL_SERIAL_NUMBER;

	public void requestTerminationFee(int tercontractId, int customerId)
		throws NotExistContractException, AlreadyProcessedException, NotExistException {
		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null)
			throw new NotExistException();
		Termination tercontract = terminationEntityModel.getById(tercontractId);
		if (tercontract == null) throw new NotExistContractException();
		if (tercontract.getTerminationStatus() == TerminationStatus.Completed)
			throw new AlreadyProcessedException();
		List<DepositDetail> depositDetailList = tercontract.getDepositDetailList();
		int totalMoney = 0;
		for (DepositDetail depositDetail : depositDetailList)
			totalMoney += depositDetail.getMoney();
		totalMoney = (int) (totalMoney * 0.3);
		int paymentId = NextIdGetter.getNextId(paymentDetailEntityModel.getMaxId(), PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail paymentDetail = new PaymentDetail(customer.getName(), customer.getBankName(),
			customer.getBankAccount(), totalMoney, PaymentType.AccountTransfer, tercontract.getId());
		paymentDetail.setId(paymentId);
		paymentDetailEntityModel.add(paymentDetail);
		Contract contract = tercontract.getOriginalContract();
		tercontract.setTerminationStatus(TerminationStatus.Completed);
		tercontract.setApplyDate(new Date());
		terminationEntityModel.update(tercontract);
		contract.setTerminationDate(tercontract.getApplyDate());
		contract.setContractStatus(ContractStatus.Terminating);
		contractEntityModel.update(contract);
	}

	public void reviewEndorsement(int endorsementId, int index) throws NotExistException {
		Endorsement encontract = endorsementEntityModel.getById(endorsementId);
		if (encontract == null)
			throw new NotExistException();
		if (index == 1) {
			encontract.setEndorsementStatus(EndorsementStatus.Completed);
			endorsementEntityModel.update(encontract);
		} else if (index == 2) {
			encontract.setEndorsementStatus(EndorsementStatus.Unprocessed);
			endorsementEntityModel.update(encontract);
		}
	}

	public void reviewRecontract(int recontractId, int index) throws NotExistContractException, NotExistException {
		Recontract recontract = recontractEntityModel.getById(recontractId);
		if (recontract == null) throw new NotExistContractException();
		if (index == 1) {
			Insurance product = insuranceEntityModel.getById(recontract.getProductId());
			if (product == null) throw new NotExistException();
			recontract.setContractStatus(ContractStatus.Maintaining);
			recontract.setDate(new Date());

			LocalDate currentDate = LocalDate.now();
			LocalDate expirationDate = currentDate.plusYears(product.getContractPeriod());
			recontract.setExpirationDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			//			contractDModel.update(contract);
			recontract.setRecontractStatus(RecontractStatus.Completed);
			recontractEntityModel.update(recontract);
		} else if (index == 2) {
			recontract.setRecontractStatus(RecontractStatus.Unprocessed);
			recontractEntityModel.update(recontract);
		}
	}

	public void reviewRevival(int revivalId, int index) throws NotExistContractException {
		Revival revival = revivalEntityModel.getById(revivalId);
		if (revival == null) throw new NotExistContractException();
		if (index == 1) {
			revival.setContractStatus(ContractStatus.Maintaining);
			revival.setDate(new Date());
			revival.setRevivalStatus(RevivalStatus.Completed);
			revivalEntityModel.update(revival);
		} else if (index == 2) {
			revival.setRevivalStatus(RevivalStatus.Unprocessed);
			revivalEntityModel.update(revival);
		}
	}

	public List<GetAllDefaultContractResponse> getAllDefaultContract()
			throws NotExistContractException, NotExistException {
		List<GetAllDefaultContractResponse> result = new ArrayList<GetAllDefaultContractResponse>();
		for (Contract contract : contractEntityModel.getAll()) {
			Customer customer = customerEntityModel.getById(contract.getCustomerID());
			String name = customer.getName();
			String phoneNumber = customer.getPhoneNumber();
			String gender = customer.getGender().getName();
			String residentRegistrationNumber = customer.getResidentRegistrationNumber();
			String address = customer.getAddress();
			CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(name,
					phoneNumber, gender, residentRegistrationNumber, address);
			int productId = contract.getProductId();
			String lastPaidDate = contract.getLastPaidDate();
			result.add(new GetAllDefaultContractResponse(
					customerInfoResponse, productId, lastPaidDate));
		}

		return result;
	}

	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerEntityModel.getById(id);
	}

	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractEntityModel.getById(contractId);
	}

	public Termination getTerminationById(int id) throws NotExistException, NotExistContractException {
        return terminationEntityModel.getById(id);
	}

	public List<GetAllTerminatingContractResponse> getAllTerminatingContract() throws NotExistContractException, NotExistException {
		List<GetAllTerminatingContractResponse> result = new ArrayList<GetAllTerminatingContractResponse>();
		for (Termination termination : terminationEntityModel.getAll()){
			Customer customer = customerEntityModel.getById(termination.getCustomerID());
			String name = customer.getName();
			String phoneNumber = customer.getPhoneNumber();
			String gender = customer.getGender().getName();
			String residentRegistrationNumber = customer.getResidentRegistrationNumber();
			String address = customer.getAddress();
			CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(name,
					phoneNumber, gender, residentRegistrationNumber, address);
			int productId = termination.getProductId();
			Date applyDate = termination.getApplyDate();
			String terminationStatus = termination.getTerminationStatus().getText();
			result.add(new GetAllTerminatingContractResponse(customerInfoResponse,
					productId, applyDate, terminationStatus));
		}
		return result;
	}

	public Termination getTerminatingContractById(int id) throws NotExistContractException, NotExistException {
		return getTerminationById(id);
	}

	public List<Termination> getAllUnprocessedTerminatingContract() throws
		NotExistContractException,
		NotExistException {
		return terminationEntityModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Unprocessed)
				.toList();
	}

	public List<Termination> getAllProcessedTerminatingContract() throws NotExistContractException, NotExistException {
		return terminationEntityModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Completed)
				.toList();
	}

	public List<GetAllEndorsementContractResponse> getAllEndorsementContract()
			throws NotExistContractException, NotExistException {
		List<GetAllEndorsementContractResponse> result = new ArrayList<GetAllEndorsementContractResponse>();
		for (Endorsement endorsement : endorsementEntityModel.getAll()) {
			Customer customer = customerEntityModel.getById(endorsement.getCustomerID());
			String name = customer.getName();
			String phoneNumber = customer.getPhoneNumber();
			String gender = customer.getGender().getName();
			String residentRegistrationNumber = customer.getResidentRegistrationNumber();
			String address = customer.getAddress();
			CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(name,
					phoneNumber, gender, residentRegistrationNumber, address);
			int productId = endorsement.getProductId();
			Date applyDate = endorsement.getApplyDate();
			String endorsementStatus = endorsement.getEndorsementStatus().getText();
			result.add(new GetAllEndorsementContractResponse(customerInfoResponse,
					productId, applyDate, endorsementStatus));
		}
		return result;
	}

	public List<Endorsement> getAllUnprocessedEndorsementContract() throws
		NotExistContractException,
		NotExistException {
		return endorsementEntityModel.getAll().stream()
			.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Unprocessed)
			.toList();
	}

	public List<Endorsement> getAllProcessedEndorsementContract() throws NotExistContractException, NotExistException {
		return endorsementEntityModel.getAll().stream()
			.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Completed)
			.toList();
	}

	public Endorsement getEndorsementById(int id) throws NotExistException, NotExistContractException {
		return endorsementEntityModel.getById(id);
		//		return endorsementList.get(id);
	}

	public List<GetAllReContractResponse> getAllReContract()
			throws NotExistContractException, NotExistException {
		List<GetAllReContractResponse> result = new ArrayList<GetAllReContractResponse>();
		for (Recontract recontract : recontractEntityModel.getAll()) {
			Customer customer = customerEntityModel.getById(recontract.getCustomerID());
			String name = customer.getName();
			String phoneNumber = customer.getPhoneNumber();
			String gender = customer.getGender().getName();
			String residentRegistrationNumber = customer.getResidentRegistrationNumber();
			String address = customer.getAddress();
			CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(name,
					phoneNumber, gender, residentRegistrationNumber, address);
			int productId = recontract.getProductId();
			String expirationDate = recontract.getExpirationDate();
			String reContractStatus = recontract.getRecontractStatus().getText();
			result.add(new GetAllReContractResponse(customerInfoResponse, productId,
					expirationDate, reContractStatus));
		}
		return result;
	}

	public List<Recontract> getAllUnprocessedReContract() throws NotExistContractException, NotExistException {
		return recontractEntityModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Unprocessed)
				.toList();
	}

	public List<Recontract> getAllProcessedReContract() throws NotExistContractException, NotExistException {
		return recontractEntityModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Completed)
				.toList();
	}

	public Recontract getReContractById(int id) throws NotExistException, NotExistContractException {
        return recontractEntityModel.getById(id);
	}

	public List<GetAllRevivalContractResponse> getAllRevivalContract()
			throws NotExistContractException, NotExistException {
		List<GetAllRevivalContractResponse> result = new ArrayList<GetAllRevivalContractResponse>();
		for (Revival revival : revivalEntityModel.getAll()) {
			Customer customer = customerEntityModel.getById(revival.getCustomerID());
			String name = customer.getName();
			String phoneNumber = customer.getPhoneNumber();
			String gender = customer.getGender().getName();
			String residentRegistrationNumber = customer.getResidentRegistrationNumber();
			String address = customer.getAddress();
			CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(name,
					phoneNumber, gender, residentRegistrationNumber, address);
			int productId = revival.getProductId();
			String terminationDate = revival.getTerminationDate() == null ?
					"" : revival.getTerminationDate();
			String revivalStatus = revival.getRevivalStatus().getText();
			result.add(new GetAllRevivalContractResponse(customerInfoResponse,
					productId, terminationDate, revivalStatus));
		}
		return result;
	}

	public Revival getRevivalById(int id) throws NotExistException, NotExistContractException {
        return revivalEntityModel.getById(id);
	}

	public List<Revival> getAllUnprocessedRevival() throws NotExistContractException, NotExistException {
		return revivalEntityModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Unprocessed)
				.toList();
	}

	public List<Revival> getAllProcessedRevival() throws NotExistContractException, NotExistException {
		return revivalEntityModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Completed)
				.toList();
	}
}
