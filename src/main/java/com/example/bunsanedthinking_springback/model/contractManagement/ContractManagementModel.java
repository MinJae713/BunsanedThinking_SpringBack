package com.example.bunsanedthinking_springback.model.contractManagement;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementStatus;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractStatus;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.revival.RevivalStatus;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContractManagementModel {
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
    private EndorsmentMapper endorsmentMapper;
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
				customer.getBankAccount(), totalMoney, PaymentType.AccountTransfer, tercontract.getId());
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



    // 여기부터 get
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
        // CustomerVO
        CustomerVO customerVO = customerMapper.getById_Customer(id).orElse(null);
        if (customerVO == null) throw new NotExistException();
        Customer result = new Customer(customerVO);
        // AccidentHistoryVO
        ArrayList<AccidentHistory> accidentHistories = new ArrayList<AccidentHistory>();
        List<AccidentHistoryVO> accidentHistoryVOS = accidentHistoryMapper.getAllByCustomerId_Customer(id);
        accidentHistoryVOS.stream().forEach(e -> accidentHistories.add(new AccidentHistory(e)));
        result.setAccidentHistoryList(accidentHistories);
        // AccidentVO
        result.setAccidentList(getAllAccidentByCustomerId(id));
        // ComplaintVO
        result.setComplaintList(getAllComplaintsByCustomerId(id));
        // ContractVO - 세부 정보 필요
        result.setContractList(getAllContractByCustomerId(id));
        // CounselVO
        ArrayList<Counsel> counsels = new ArrayList<Counsel>();
        List<CounselVO> counselVOS = counselMapper.getAllByCustomerId_Customer(id);
        counselVOS.stream().forEach(e -> counsels.add(new Counsel(
                e,
                customerVO.getName(),
                customerVO.getPhone_number(),
                customerVO.getJob(),
                customerVO.getAge(),
                Gender.values()[customerVO.getGender()]
        )));
        result.setCounsel(counsels);
        // DiseaseHistoryVO
        ArrayList<DiseaseHistory> diseaseHistories = new ArrayList<DiseaseHistory>();
        List<DiseaseHistoryVO> diseaseHistoryVOS = diseaseHistoryMapper.getAllByCustomerId_Customer(id);
        diseaseHistoryVOS.stream().forEach(e -> diseaseHistories.add(new DiseaseHistory(e)));
        result.setDiseaseHistoryList(diseaseHistories);
        // SurgeryHistoryVO
        ArrayList<SurgeryHistory> surgeryHistories = new ArrayList<SurgeryHistory>();
        List<SurgeryHistoryVO> surgeryHistoryVOS = surgeryHistoryMapper.getAllByCustomerId_Customer(id);
        surgeryHistoryVOS.stream().forEach(e -> surgeryHistories.add(new SurgeryHistory(e)));
        result.setSurgeryHistoryList(surgeryHistories);
        return result;
//		return customerList.get(customerID);
    }
    private ArrayList<Accident> getAllAccidentByCustomerId(int id) throws NotExistException {
        ArrayList<Accident> result = new ArrayList<Accident>();
        List<AccidentVO> accidentVOS = accidentMapper.getAllByCustomerId_Customer(id);
        for (AccidentVO accidentVO : accidentVOS)
            result.add(getAccidentById(accidentVO.getId()));
        return result;
    }
    private Accident getAccidentById(int id) throws NotExistException {
        AccidentVO accidentVO = accidentMapper.getAccidentById_Customer(id).orElse(null);
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
        // ContractVO
        Contract result = new Contract();
        ContractVO contractVO = contractMapper.getById_Customer(contractId).orElse(null);
        if (contractVO == null) throw new NotExistContractException();
        result.setId(contractVO.getId());
        result.setDate(java.sql.Date.valueOf(contractVO.getDate()));
        result.setExpirationDate(java.sql.Date.valueOf(contractVO.getExpiration_date()));
        result.setPaymentDate(contractVO.getPayment_date().getDayOfMonth());

        LocalDate terminationDate = contractVO.getTermination_date();
        if (terminationDate != null) result.setTerminationDate(java.sql.Date.valueOf(terminationDate));
        result.setContractStatus(ContractStatus.values()[contractVO.getContract_status()]);
        result.setCustomerID(contractVO.getCustomer_id());
        result.setEmployeeID(contractVO.getEmployee_id());
        result.setLastPaidDate(java.sql.Date.valueOf(contractVO.getLastpaid_date()));
        // ProductVO - 얜 Product 하나만 반환
        result.setProduct(getProductById(contractVO.getProduct_id()));

        // CompensationDetailVO
        ArrayList<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
        List<CompensationDetailVO> compensationDetailVOS = compensationDetailMapper.getAllCompensationByContractId_Customer(contractId);
        for (CompensationDetailVO compensationDetailVO : compensationDetailVOS)
            compensationDetails.add(compensationDetailVO.getCompensationDetail());
        result.setCompensationDetailList(compensationDetails);

        // DepositDetailVO
        ArrayList<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
        List<DepositDetailVO> depositDetailVOS = depositDetailMapper.getAllDepositByContractId_Customer(contractId);
        for (DepositDetailVO depositDetailVO : depositDetailVOS)
            depositDetails.add(depositDetailVO.getDepositDetail());
        result.setDepositDetailList(depositDetails);

        // InsuranceMoneyVO
        ArrayList<InsuranceMoney> insuranceMoneys = new ArrayList<InsuranceMoney>();
        List<InsuranceMoneyVO> insuranceMoneyVOS = insuranceMoneyMapper.getAllByContractId_Customer(contractId);
        for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyVOS)
            insuranceMoneys.add(insuranceMoneyVO.getInsuranceMoneyDetail());
        result.setInsuranceMoneyList(insuranceMoneys);
        return result;
//		return contractList.get(contractId);
    }
    private Product getProductById(int product_id) throws NotExistException {
        Product product = getInsuranceByProductId(product_id);
        if (product != null) return product;
        product = getLoanByProductId(product_id);
        if (product != null) return product;
        throw new NotExistException();
    }
    private Insurance getInsuranceByProductId(int id) throws NotExistException {
        // insurance 찾기 - Automobile, Disease, Injury 셋 중 하나 반환
        // orElse(param) - 반환 값이 null이면 param 반환

        // productVO
        ProductVO productVO = productMapper.getProductById_Customer(id).orElse(null);
        if (productVO == null) throw new NotExistException();

        // insuranceVO
        InsuranceVO insuranceVO = insuranceMapper.getInsuranceById_Customer(id).orElse(null);
        if (insuranceVO == null) throw new NotExistException();

        // DiseaseVO
        DiseaseVO diseaseVO = diseaseMapper.getDiseaseById_Customer(id).orElse(null);
        if (diseaseVO != null) {
            String disease_name = diseaseVO.getDisease_name();
            int disease_limit = diseaseVO.getDisease_limit();
            int surgeries_limit = diseaseVO.getSurgeries_limit();
            return new Disease(productVO, insuranceVO, disease_name, disease_limit, surgeries_limit);
        }

        // AutoMobileVO
        AutoMobileVO autoMobileVO = automobileMapper.getAutoMobileById_Customer(id).orElse(null);
        if (autoMobileVO != null) {
            List<ServiceVO> serviceVOS = serviceMapper.getAllByProductId_Customer(id);
            ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
            serviceVOS.stream().map(s -> s.getService()).forEach(s -> serviceTypeList.add(ServiceType.values()[s]));
            VehicleType verhicle_type = VehicleType.values()[autoMobileVO.getVehicle_type()];
            int accident_limit = autoMobileVO.getAccident_limit();
            return new Automobile(productVO, insuranceVO, accident_limit, verhicle_type, serviceTypeList);
        }

        // InjuryVO
        InjuryVO injuryVO = injuryMapper.getInjuryById_Customer(id).orElse(null);
        if (injuryVO != null) {
            InjuryType injury_type = InjuryType.values()[injuryVO.getInjury_type()];
            int surgeries_limit = injuryVO.getSurgeries_limit();
            return new Injury(productVO, insuranceVO, injury_type, surgeries_limit);
        }
        throw new NotExistException();
    }
    private Loan getLoanByProductId(int id) throws NotExistException {
        // productVO
        ProductVO productVO = productMapper.getProductById_Customer(id).orElse(null);
        if (productVO == null) throw new NotExistException();

        // loanVO
        LoanVO loanVO = loanMapper.getLoanById_Customer(id).orElse(null);
        if (loanVO == null) throw new NotExistException();

        // CollateralVO
        CollateralVO collateralVO = collateralMapper.getCollateralById_Customer(id).orElse(null);
        if (collateralVO != null)
            return new Collateral(productVO, loanVO,
                    CollateralType.values()[collateralVO.getCollateral_type()],
                    collateralVO.getMinimum_value());

        // FixedDepositVO
        FixedDepositVO fixedDepositVO = fixedDepositMapper.getFixedDepositById_Customer(id).orElse(null);
        if (fixedDepositVO != null) return new FixedDeposit(productVO, loanVO, fixedDepositVO.getMinimum_amount());

        // InsuranceContractVO
        InsuranceContractVO insuranceContractVO = insuranceContractMapper.getInsuranceContractById_Customer(id).orElse(null);
        if (insuranceContractVO != null) return new InsuranceContract(productVO, loanVO, id);
        throw new NotExistException();
    }
    // 여기까지가 contract 하나 찾기

	public Termination getTerminationById(int id) throws NotExistException, NotExistContractException {
        TerminationVO terminationVO = terminationMapper.getById_ContractManagement(id).orElse(null);
        if (terminationVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return terminationVO.getTermination(contract);
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
	public List<Termination> getAllUnprocessedTerminatingContract() throws NotExistContractException, NotExistException {
		return getAllTerminatingContract().stream().filter(e -> e.getTerminationStatus() == TerminationStatus.Unprocessed).toList();
//        return terminationList.getAllUnprocessedTerminatingContract();
	}
	public List<Termination> getAllProcessedTerminatingContract() throws NotExistContractException, NotExistException {
        return getAllTerminatingContract().stream().filter(e -> e.getTerminationStatus() == TerminationStatus.Completed).toList();
//		return terminationList.getAllProcessedTerminatingContract();
	}
	public List<Endorsement> getAllEndorsementContract() throws NotExistContractException, NotExistException {
        List<Endorsement> result = new ArrayList<Endorsement>();
        List<EndorsementVO> endorsementVOS = endorsmentMapper.getAll_ContractManagement();
        for (EndorsementVO endorsementVO : endorsementVOS)
            result.add(getEndorsementById(endorsementVO.getContract_id()));
        return result;
//		return endorsementList.getAllEndorsementContract();
	}
	public List<Endorsement> getAllUnprocessedEndorsementContract() throws NotExistContractException, NotExistException {
		return getAllEndorsementContract().stream().filter(e -> e.getEndorsementStatus() == EndorsementStatus.Unprocessed).toList();
	}
	public List<Endorsement> getAllProcessedEndorsementContract() throws NotExistContractException, NotExistException {
        return getAllEndorsementContract().stream().filter(e -> e.getEndorsementStatus() == EndorsementStatus.Completed).toList();
	}
	public Endorsement getEndorsementById(int id) throws NotExistException, NotExistContractException {
        EndorsementVO endorsementVO = endorsmentMapper.getById_ContractManagement(id).orElse(null);
        if (endorsementVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return endorsementVO.getEndorsement(contract);
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
		return getAllReContract().stream().filter(e -> e.getRecontractStatus() == RecontractStatus.Unprocessed).toList();
	}
	public List<Recontract> getAllProcessedReContract() throws NotExistContractException, NotExistException {
        return getAllReContract().stream().filter(e -> e.getRecontractStatus() == RecontractStatus.Completed).toList();
	}
	public Recontract getReContractById(int id) throws NotExistException, NotExistContractException {
        RecontractVO recontractVO = recontractMapper.getById_ContractManagement(id).orElse(null);
        if (recontractVO == null) throw new NotExistException();
        Contract contract = getContractById(id);
        return recontractVO.getRecontract(contract);
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
        return revivalVO.getRevival(contract);
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