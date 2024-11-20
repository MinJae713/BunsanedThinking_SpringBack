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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<DefaultContractResponse> getAllDefaultContract()
			throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().stream()
				.map(contract -> DefaultContractResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(contract.getCustomerID())),
						contract))
				.collect(Collectors.toList());
	}

	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerEntityModel.getById(id);
	}

	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractEntityModel.getById(contractId);
	}

	public DefaultContractResponse getContractRowById(int id) throws NotExistContractException, NotExistException {
		Contract contract = getContractById(id);
		if (contract == null) throw new NotExistContractException();
		Customer customer = getCustomerById(contract.getCustomerID());
		CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.from(customer);
		return DefaultContractResponse.of(customerInfoResponse, contract);
	}

	public Termination getTerminationById(int id) throws NotExistException, NotExistContractException {
        return terminationEntityModel.getById(id);
	}

	public TerminationResponse getTerminationRowById(int id) throws NotExistContractException, NotExistException {
		Termination termination = getTerminationById(id);
		if (termination == null) throw new NotExistContractException();
		Customer customer = getCustomerById(termination.getCustomerID());
		CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.from(customer);
		return TerminationResponse.of(customerInfoResponse, termination);
	}

	public Termination getTerminatingContractById(int id) throws NotExistContractException, NotExistException {
		return getTerminationById(id);
	}

	public List<TerminationResponse> getAllTerminatingContract()
			throws NotExistContractException, NotExistException {
		return terminationEntityModel.getAll().stream()
				.map(termination -> TerminationResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(termination.getCustomerID())),
						termination))
				.collect(Collectors.toList());
	}

	public List<TerminationResponse> getAllUnprocessedTerminatingContract() throws
		NotExistContractException,
		NotExistException {
		return terminationEntityModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Unprocessed)
				.map(termination -> TerminationResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(termination.getCustomerID())),
						termination))
				.collect(Collectors.toList());
	}

	public List<TerminationResponse> getAllProcessedTerminatingContract()
			throws NotExistContractException, NotExistException {
		return terminationEntityModel.getAll().stream()
				.filter(e -> e.getTerminationStatus() == TerminationStatus.Completed)
				.map(termination -> TerminationResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(termination.getCustomerID())),
						termination))
				.collect(Collectors.toList());
	}

	public Endorsement getEndorsementById(int id) throws NotExistException, NotExistContractException {
		return endorsementEntityModel.getById(id);
	}

	public EndorsementResponse getEndorsementRowById(int id) throws NotExistContractException, NotExistException {
		Endorsement endorsement = getEndorsementById(id);
		if (endorsement == null) throw new NotExistContractException();
		Customer customer = getCustomerById(endorsement.getCustomerID());
		CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.from(customer);
		return EndorsementResponse.of(customerInfoResponse, endorsement);
	}

	public List<EndorsementResponse> getAllEndorsementContract()
			throws NotExistContractException, NotExistException {
		return endorsementEntityModel.getAll().stream()
				.map(endorsement -> EndorsementResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(endorsement.getCustomerID())),
						endorsement))
				.collect(Collectors.toList());
	}

	public List<EndorsementResponse> getAllUnprocessedEndorsementContract() throws
		NotExistContractException,
		NotExistException {
		return endorsementEntityModel.getAll().stream()
				.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Unprocessed)
				.map(endorsement -> EndorsementResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(endorsement.getCustomerID())),
						endorsement))
				.collect(Collectors.toList());
	}

	public List<EndorsementResponse> getAllProcessedEndorsementContract()
			throws NotExistContractException, NotExistException {
		return endorsementEntityModel.getAll().stream()
				.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Completed)
				.map(endorsement -> EndorsementResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(endorsement.getCustomerID())),
						endorsement))
				.collect(Collectors.toList());
	}

	public Recontract getReContractById(int id) throws NotExistException, NotExistContractException {
		return recontractEntityModel.getById(id);
	}

	public ReContractResponse getReContractRowById(int id) throws NotExistContractException, NotExistException {
		Recontract recontract = getReContractById(id);
		if (recontract == null) throw new NotExistContractException();
		Customer customer = getCustomerById(recontract.getCustomerID());
		CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.from(customer);
		return ReContractResponse.of(customerInfoResponse, recontract);
	}

	public List<ReContractResponse> getAllReContract()
			throws NotExistContractException, NotExistException {
		return recontractEntityModel.getAll().stream()
				.map(recontract -> ReContractResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(recontract.getCustomerID())),
						recontract))
				.collect(Collectors.toList());
	}

	public List<ReContractResponse> getAllUnprocessedReContract()
			throws NotExistContractException, NotExistException {
		return recontractEntityModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Unprocessed)
				.map(recontract -> ReContractResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(recontract.getCustomerID())),
						recontract))
				.collect(Collectors.toList());
	}

	public List<ReContractResponse> getAllProcessedReContract()
			throws NotExistContractException, NotExistException {
		return recontractEntityModel.getAll().stream()
				.filter(e -> e.getRecontractStatus() == RecontractStatus.Completed)
				.map(recontract -> ReContractResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(recontract.getCustomerID())),
						recontract))
				.collect(Collectors.toList());
	}

	public Revival getRevivalById(int id) throws NotExistException, NotExistContractException {
		return revivalEntityModel.getById(id);
	}

	public RevivalResponse getRevivalRowById(int id) throws NotExistContractException, NotExistException {
		Revival revival = getRevivalById(id);
		if (revival == null) throw new NotExistContractException();
		Customer customer = getCustomerById(revival.getCustomerID());
		CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.from(customer);
		return RevivalResponse.of(customerInfoResponse, revival);
	}

	public List<RevivalResponse> getAllRevivalContract()
			throws NotExistContractException, NotExistException {
		return revivalEntityModel.getAll().stream()
				.map(revival -> RevivalResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(revival.getCustomerID())),
						revival))
				.collect(Collectors.toList());
	}

	public List<RevivalResponse> getAllUnprocessedRevival() throws NotExistContractException, NotExistException {
		return revivalEntityModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Unprocessed)
				.map(revival -> RevivalResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(revival.getCustomerID())),
						revival))
				.collect(Collectors.toList());
	}

	public List<RevivalResponse> getAllProcessedRevival() throws NotExistContractException, NotExistException {
		return revivalEntityModel.getAll().stream()
				.filter(e -> e.getRevivalStatus() == RevivalStatus.Completed)
				.map(revival -> RevivalResponse.of(
						CustomerInfoResponse.from(customerEntityModel.getById(revival.getCustomerID())),
						revival))
				.collect(Collectors.toList());
	}
}
