package com.example.bunsanedthinking_springback.model.service.employee.contractManagement;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementStatus;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
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
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.domain.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
@Service
public class ContractManagementSModel {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AccidentMapper accidentMapper;
    @Autowired
    private AccidentHistoryMapper accidentHistoryMapper;
    @Autowired
    private ComplaintMapper complaintMapper;
    @Autowired
    private CounselMapper counselMapper;
    @Autowired
    private DiseaseHistoryMapper diseaseHistoryMapper;
    @Autowired
    private SurgeryHistoryMapper surgeryHistoryMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CompensationDetailMapper compensationDetailMapper;
    @Autowired
    private DepositDetailMapper depositDetailMapper;
    @Autowired
    private InsuranceMoneyMapper insuranceMoneyMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InsuranceMapper insuranceMapper;
    @Autowired
    private AutomobileMapper automobileMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private InjuryMapper injuryMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private CollateralMapper collateralMapper;
    @Autowired
    private FixedDepositMapper fixedDepositMapper;
    @Autowired
    private InsuranceContractMapper insuranceContractMapper;
    @Autowired
    private TerminationMapper terminationMapper;
    @Autowired
    private RecontractMapper recontractMapper;
    @Autowired
    private RevivalMapper revivalMapper;
    @Autowired
    private EndorsementMapper endorsementMapper;
    @Autowired
    private PaymentDetailMapper paymentDetailMapper;
    @Autowired
    private AccidentHistoryList accidentHistoryList;
    @Autowired
    private CustomerDModel customerDModel;
    @Autowired
    private ContractDModel contractDModel;

    public void requestTerminationFee(int tercontractId, int customerId)
		throws NotExistContractException, AlreadyProcessedException, NotExistException {
		// TerminationStatus가 Complete인지 확인
		// totalMoney를 전체 DepositDetail 리스트를 받아와서 계산 - 계산 방법은 아래 참고
		// PaymentDetail 추가
		// termination의 terminationStatus, applyDate 수정
		// termination의 originContract 해당 Contract의 terminationDate, contractStatus 수정
		TerminationVO terminationVO = terminationMapper.getById_ContractManagement(tercontractId).orElse(null);
		if (terminationVO == null)
			throw new NotExistContractException();
		if (terminationVO.getTermination_status() == TerminationStatus.Completed.ordinal())
			throw new AlreadyProcessedException();
		CustomerVO customerVO = customerMapper.getById_Customer(customerId).orElse(null);
		if (customerVO == null)
			throw new NotExistException();
		ContractVO contractVO = contractMapper.getById_Customer(tercontractId).orElse(null);
		if (contractVO.getCustomer_id() != customerId)
			throw new NotExistContractException();
		// 고객이 신청한 계약이 맞는지 확인
		List<DepositDetailVO> depositDetailVOS = depositDetailMapper.getAll_ContractManagement();
		int totalMoney = 0;
		for (DepositDetailVO depositDetailVO : depositDetailVOS)
			totalMoney += depositDetailVO.getMoney();
		totalMoney = (int)(totalMoney * 0.3);

		int paymentId = paymentDetailMapper.getCount_Compensation() == 0 ?
			9001 : paymentDetailMapper.getLastId_Compensation() + 1;
		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(paymentId,
			customerVO.getName(), customerVO.getBank_name(),
			customerVO.getBank_account(), totalMoney,
			PaymentType.AccountTransfer.ordinal(),
			PaymentProcessStatus.Unprocessed.ordinal(),
			terminationVO.getContract_id());
		paymentDetailMapper.add_Compensation(paymentDetailVO);

		ContractVO originContractVO = contractMapper.
			getById_Customer(terminationVO.getOrigin_contract_id()).
			orElse(null);
		terminationMapper.updateStatus_ContractManagement(TerminationStatus.Completed.ordinal(),
			terminationVO.getContract_id());
		terminationMapper.updateApplyDate_ContractManagement(LocalDateTime.now(), terminationVO.getContract_id());
		contractMapper.updateTerminationDate_ContractManagement(terminationVO.getApply_date().toLocalDate(),
			originContractVO.getId());
		contractMapper.updateStatus_Customer(ContractStatus.Terminating.ordinal(), originContractVO.getId());

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
        if (endorsementMapper.getById_ContractManagement(endorsementId).orElse(null) == null)
            throw new NotExistException();
        if (index == 1) endorsementMapper.updateStatus_ContractManagement(EndorsementStatus.Completed.ordinal(), endorsementId);
        else if (index == 2) endorsementMapper.updateStatus_ContractManagement(EndorsementStatus.Unprocessed.ordinal(), endorsementId);
//		if (index == 1) {
//			encontract.setPaymentDate(encontract.getPaymentDate());
//			encontract.setEndorsementStatus(EndorsementStatus.Completed);
//		} else if (index == 2) {
//			encontract.setEndorsementStatus(EndorsementStatus.Unprocessed);
//		}
	}

	public void reviewRecontract(int recontractId, int index) throws NotExistContractException, NotExistException {
		RecontractVO recontractVO = recontractMapper.getById_ContractManagement(recontractId).orElse(null);
		if (recontractVO == null)
			throw new NotExistContractException();
		if (index == 1) {
			int origin_contract_id = recontractVO.getOrigin_contract_id();
			Contract contract = getContractById(origin_contract_id);
			Insurance insurance = (Insurance)contract.getProduct();
			contractMapper.updateStatus_Customer(ContractStatus.Maintaining.ordinal(), origin_contract_id);
			contractMapper.updateDate_ContractManagement(LocalDate.now(), origin_contract_id);

			LocalDate expirationDate = LocalDate.now().plusYears(insurance.getContractPeriod());
			contractMapper.updateExpirationDate_ContractManagement(expirationDate, origin_contract_id);
			recontractMapper.updateStatus_ContractManagement(RecontractStatus.Completed.ordinal(), recontractId);

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
		} else if (index == 2) {
			recontractMapper.updateStatus_ContractManagement(RecontractStatus.Unprocessed.ordinal(), recontractId);
			//			recontract.setRecontractStatus(RecontractStatus.Unprocessed);
		}
	}

	public void reviewRevival(int revivalId, int index) throws NotExistContractException {
		RevivalVO revivalVO = revivalMapper.getById_ContractManagement(revivalId).orElse(null);
		if (revivalVO == null)
			throw new NotExistContractException();
		if (index == 1) {
			int origin_contract_id = revivalVO.getOrigin_contract_id();
			contractMapper.updateStatus_Customer(ContractStatus.Maintaining.ordinal(), origin_contract_id);
			contractMapper.updateDate_ContractManagement(LocalDate.now(), origin_contract_id);
			revivalMapper.updateStatus_ContractManagement(RevivalStatus.Completed.ordinal(), revivalId);
			//			Contract contract = revivalcontract.getOriginalContract().clone();
			//			contract.setContractStatus(ContractStatus.Maintaining);
			//			contract.setDate(new Date());
			//            contractList.update(contract);
			//			revivalcontract.setRevivalStatus(RevivalStatus.Completed);
		} else if (index == 2) {
			revivalMapper.updateStatus_ContractManagement(RevivalStatus.Unprocessed.ordinal(), revivalId);
			//			revivalcontract.setRevivalStatus(RevivalStatus.Unprocessed);
		}
	}

	// 여기부터 get - 아래는 구현 완
	public List<Contract> getAllDefaultContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		List<ContractVO> contractVOS = contractMapper.getAll_Customer();
		for (ContractVO contractVO : contractVOS)
			result.add(getContractById(contractVO.getId()));
		return result;
		//		return contractList.getAllDefaultContract();
	}
    // 여기부터 Customer 하나 찾기(contract 찾는건 아래에 구현됨)
    public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
        return customerDModel.getById(id);
    }
    private ArrayList<Accident> getAllAccidentByCustomerId(int id) throws NotExistException {
        ArrayList<Accident> result = new ArrayList<Accident>();
        List<AccidentVO> accidentVOS = accidentMapper.getAllByCustomerId_Customer(id);
        for (AccidentVO accidentVO : accidentVOS)
            result.add(getAccidentById(accidentVO.getId()));
        return result;
    }
    private Accident getAccidentById(int id) throws NotExistException {
        AccidentVO accidentVO = accidentMapper.getById_Customer(id).orElse(null);
        if (accidentVO == null) throw new NotExistException();
        int customer_id = accidentVO.getCustomer_id();
        String customer_name = customerMapper.getNameById_Customer(customer_id).orElse(null);
        if (customer_name == null) throw new NotExistException();
        String customer_phoneNumber = customerMapper.getPNById_Customer(customer_id).orElse(null);
        if (customer_phoneNumber == null) throw  new NotExistException();
        return new Accident(accidentVO, customer_name, customer_phoneNumber);
    }
    private ArrayList<Complaint> getAllComplaintsByCustomerId(int id) throws NotExistException {
        ArrayList<Complaint> result = new ArrayList<Complaint>();
        List<ComplaintVO> complainVOS = complaintMapper.getComplaintByCustomerId_Customer(id);
        for (ComplaintVO complaintVO : complainVOS)
            result.add(getComplaintById(complaintVO.getId()));
        return result;
    }
    private ArrayList<Contract> getAllContractByCustomerId(int id) throws NotExistContractException, NotExistException {
        // 계약들의 고객 번호를 비교 - 고객 번호가 같은 계약들만 추출 - 한 고객이 신청한 계약만 나옴
        ArrayList<Contract> result = new ArrayList<Contract>();
        List<ContractVO> contractVOS = contractMapper.getAllByCustomerId_Customer(id);
        for (ContractVO contractVO : contractVOS) result.add(getContractById(contractVO.getId()));
        return result;
    }
    private Complaint getComplaintById(int id) throws NotExistException {
        ComplaintVO complaintVO = complaintMapper.getComplaintById_Customer(id).orElse(null);
        if (complaintVO == null) throw new NotExistException();
        return new Complaint(complaintVO);
    }
    // 여기까지가 customer 하나 찾기
    // =====================================
    // 여기부터 contract 하나 찾기
    public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
        return contractDModel.getById(contractId);
    }
	public Termination getTerminationById(int id) throws NotExistException, NotExistContractException {
        TerminationVO terminationVO = terminationMapper.getById_ContractManagement(id).orElse(null);
        if (terminationVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return terminationVO.getEntity(contract);
//		return terminationList.get(id);
	}

	public List<Termination> getAllTerminatingContract() throws NotExistContractException, NotExistException {
		List<Termination> result = new ArrayList<Termination>();
		List<TerminationVO> terminationVOS = terminationMapper.getAll_ContractManagement();
		for (TerminationVO terminationVO : terminationVOS)
			result.add(getTerminationById(terminationVO.getContract_id()));
		return result;
		//		return terminationList.getAllTerminatingContract();
	}

	public Termination getTerminatingContractById(int id) throws NotExistContractException, NotExistException {
		return getTerminationById(id);
		//      return terminationList.getTerminatingContractById(id);
	}

	public List<Termination> getAllUnprocessedTerminatingContract() throws
		NotExistContractException,
		NotExistException {
		return getAllTerminatingContract().stream()
			.filter(e -> e.getTerminationStatus() == TerminationStatus.Unprocessed)
			.toList();
		//        return terminationList.getAllUnprocessedTerminatingContract();
	}

	public List<Termination> getAllProcessedTerminatingContract() throws NotExistContractException, NotExistException {
		return getAllTerminatingContract().stream()
			.filter(e -> e.getTerminationStatus() == TerminationStatus.Completed)
			.toList();
		//		return terminationList.getAllProcessedTerminatingContract();
	}

	public List<Endorsement> getAllEndorsementContract() throws NotExistContractException, NotExistException {
        List<Endorsement> result = new ArrayList<Endorsement>();
        List<EndorsementVO> endorsementVOS = endorsementMapper.getAll_ContractManagement();
        for (EndorsementVO endorsementVO : endorsementVOS)
            result.add(getEndorsementById(endorsementVO.getContract_id()));
        return result;
//		return endorsementList.getAllEndorsementContract();
	}

	public List<Endorsement> getAllUnprocessedEndorsementContract() throws
		NotExistContractException,
		NotExistException {
		return getAllEndorsementContract().stream()
			.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Unprocessed)
			.toList();
	}

	public List<Endorsement> getAllProcessedEndorsementContract() throws NotExistContractException, NotExistException {
		return getAllEndorsementContract().stream()
			.filter(e -> e.getEndorsementStatus() == EndorsementStatus.Completed)
			.toList();
	}

	public Endorsement getEndorsementById(int id) throws NotExistException, NotExistContractException {
        EndorsementVO endorsementVO = endorsementMapper.getById_ContractManagement(id).orElse(null);
        if (endorsementVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return endorsementVO.getEntity(contract);
//		return endorsementList.get(id);
	}

	public List<Recontract> getAllReContract() throws NotExistContractException, NotExistException {
		List<Recontract> result = new ArrayList<Recontract>();
		List<RecontractVO> recontractVOS = recontractMapper.getAll_ContractManagement();
		for (RecontractVO recontractVO : recontractVOS)
			result.add(getReContractById(recontractVO.getContract_id()));
		return result;
		//		return recontractList.getAllReContract();
	}

	public List<Recontract> getAllUnprocessedReContract() throws NotExistContractException, NotExistException {
		return getAllReContract().stream()
			.filter(e -> e.getRecontractStatus() == RecontractStatus.Unprocessed)
			.toList();
	}

	public List<Recontract> getAllProcessedReContract() throws NotExistContractException, NotExistException {
		return getAllReContract().stream().filter(e -> e.getRecontractStatus() == RecontractStatus.Completed).toList();
	}

	public Recontract getReContractById(int id) throws NotExistException, NotExistContractException {
        RecontractVO recontractVO = recontractMapper.getById_ContractManagement(id).orElse(null);
        if (recontractVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return recontractVO.getEntity(contract);
//		return recontractList.get(id);
	}

	public List<Revival> getAllRevivalContract() throws NotExistContractException, NotExistException {
		List<Revival> result = new ArrayList<Revival>();
		List<RevivalVO> revivalVOS = revivalMapper.getAll_ContractManagement();
		for (RevivalVO revivalVO : revivalVOS)
			result.add(getRevivalById(revivalVO.getContract_id()));
		return result;
		//		return revivalList.getAllRevivalContract();
	}

	public Revival getRevivalById(int id) throws NotExistException, NotExistContractException {
        RevivalVO revivalVO = revivalMapper.getById_ContractManagement(id).orElse(null);
        if (revivalVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return revivalVO.getEntity(contract);
//		return revivalList.get(id);
	}

	public List<Revival> getAllUnprocessedRevival() throws NotExistContractException, NotExistException {
		return getAllRevivalContract().stream().filter(e -> e.getRevivalStatus() == RevivalStatus.Unprocessed).toList();
	}

	public List<Revival> getAllProcessedRevival() throws NotExistContractException, NotExistException {
		return getAllRevivalContract().stream().filter(e -> e.getRevivalStatus() == RevivalStatus.Completed).toList();
	}

	//    public Recontract getRecontractById(RecontractList recontractList, int id) {
	//        return recontractList.get(id);
	//    } - getReContractById 대체

	//	public Revival get(RevivalList revivalList, int id) {
	//		return revivalList.get(id);
	//	} - getRevivalById 대체
}
