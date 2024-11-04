package com.example.bunsanedthinking_springback.model.compensation;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompensationModel {
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
	private ReportMapper reportMapper;
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;

	// report의 processStatus 확인 - reportVO 정보 받아오기
	// paymentDetailVO - 테이블에 추가
	// reportVO - processStatus를 Complete으로 테이블 수정
	// accidentVO - processStatus를 Complete으로 테이블 수정(어떤 accident인지는 report가 갖는 accident id 확인)
	public void requestCompensation(String accountHolder, String bank, String bankAccount, int money,
									int paymentType, int contractId, int reportId)
					throws NotExistException, AlreadyProcessedException{
		ReportVO reportVO = reportMapper.getById_Compensation(reportId).orElse(null);
		if (reportVO == null) throw new NotExistException();
		if (reportVO.getProcess_status() == ReportProcessStatus.Completed.ordinal())
			throw new AlreadyProcessedException();
		int paymentId = paymentDetailMapper.getCount_Compensation() == 0 ?
				9001 : paymentDetailMapper.getLastId_Compensation();
		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(
				paymentId, accountHolder, bank, bankAccount,
				money, paymentType, PaymentProcessStatus.Unprocessed.ordinal(),
				contractId, 6001101);
		// employeeId 임의 지정 - 이거 직원 아이디 어떻게 받죠...
		paymentDetailMapper.add_Compensation(paymentDetailVO);
		reportMapper.updateStatus_Compensation(ReportProcessStatus.Completed.ordinal(), reportId);
		int accidentId = reportVO.getAccident_id();
		accidentMapper.updateStatus_Compensation(AccidentProcessStatus.Completed.ordinal(), accidentId);
//		if (report.getProcessStatus() == ReportProcessStatus.Completed) {
//			throw new AlreadyProcessedException();
//		}
//		PaymentDetail payment = new PaymentDetail(accountHolder, bank, bankAccount, money, paymentType, contractId);
//		paymentDetailList.add(payment);
//		report.setProcessStatus(ReportProcessStatus.Completed);
//		reportList.update(report);
//		report.getAccident().complete(); - accident의 processStatus를 Complete 변경
//		accidentList.update(report.getAccident());
	}
	
	public void requestInsuranceMoney(Customer customer, int money, InsuranceMoney insuranceMoney, InsuranceMoneyList insuranceMoneyList,
									  PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException{
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		PaymentDetail payment = new PaymentDetail(customer.getName(), customer.getBankName(), customer.getBankAccount(), money, paymentType, contractId);
		paymentDetailList.add(payment);
		insuranceMoney.setProcessStatus(InsuranceMoneyStatus.Completed);
		insuranceMoney.handle();
		insuranceMoneyList.update(insuranceMoney);
	}



	// 아래부터 get
	public List<InsuranceMoney> getAllInsuranceMoney() {
		List<InsuranceMoneyVO> insuranceMoneyVOList = insuranceMoneyMapper.getAll_Compensation();
		List<InsuranceMoney> result = new ArrayList<>();
		insuranceMoneyVOList.stream().forEach(e -> result.add(e.getInsuranceMoneyDetail()));
		return result;
//		return insuranceMoneyList.getAll();
	}
	public List<InsuranceMoney> getAllUnprocessedInsuranceMoney() {
		return getAllInsuranceMoney().stream().filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed).toList();
	}
	public List<InsuranceMoney> getAllProcessedInsuranceMoney() {
		return getAllInsuranceMoney().stream().filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed).toList();
	}
	public InsuranceMoney getInsuranceMoneyById(int id) throws NotExistException {
		InsuranceMoneyVO insuranceMoneyVO = insuranceMoneyMapper.getById_Compensation(id).orElse(null);
		if (insuranceMoneyVO == null) throw new NotExistException();
		return insuranceMoneyVO.getInsuranceMoneyDetail();
	}


	// 여기부터 contract 하나 찾기
	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		// ContractVO
		Contract result = new Contract();
		ContractVO contractVO = contractMapper.getById_Customer(contractId).orElse(null);
		if (contractVO == null) throw new NotExistContractException();
		result.setId(contractVO.getId());
		result.setDate(Date.valueOf(contractVO.getDate()));
		result.setExpirationDate(Date.valueOf(contractVO.getExpiration_date()));
		result.setPaymentDate(contractVO.getPayment_date().getDayOfMonth());

		LocalDate terminationDate = contractVO.getTermination_date();
		if (terminationDate != null) result.setTerminationDate(Date.valueOf(terminationDate));
		result.setContractStatus(ContractStatus.values()[contractVO.getContract_status()]);
		result.setCustomerID(contractVO.getCustomer_id());
		result.setEmployeeID(contractVO.getEmployee_id());
		result.setLastPaidDate(Date.valueOf(contractVO.getLastpaid_date()));
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
			VehicleType vehicle_type = VehicleType.values()[autoMobileVO.getVehicle_type()];
			int accident_limit = autoMobileVO.getAccident_limit();
			return new Automobile(productVO, insuranceVO, accident_limit, vehicle_type, serviceTypeList);
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
	// =====================================
	// 여기부터(contract 찾는건 위에 구현됨)
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



	public List<Report> getAllReport() {
		List<Report> reports = new ArrayList<Report>();
		List<ReportVO> reportVOS = reportMapper.getAll_Compensation();
		for (ReportVO reportVO : reportVOS) {
			AccidentVO accidentVO = accidentMapper.getAccidentById_Compensation(reportVO.getAccident_id()).orElse(null);
			if (accidentVO == null) continue;
			CustomerVO customerVO = customerMapper.getById_Compensation(accidentVO.getCustomer_id()).orElse(null);
			if (customerVO == null) continue;
			reports.add(reportVO.getReport(new Accident(accidentVO, customerVO.getName(), customerVO.getPhone_number())));
		}
		return reports;
//		return reportList.getAll();
	}
	public Report getReportById(int id) throws NotExistException {
		ReportVO reportVO = reportMapper.getById_Compensation(id).orElse(null);
		if (reportVO == null) throw new NotExistException();
		AccidentVO accidentVO = accidentMapper.getAccidentById_Compensation(reportVO.getAccident_id()).orElse(null);
		if (accidentVO == null) throw new NotExistException();
		CustomerVO customerVO = customerMapper.getById_Compensation(accidentVO.getCustomer_id()).orElse(null);
		if (customerVO == null) throw new NotExistException();
		return reportVO.getReport(new Accident(accidentVO, customerVO.getName(), customerVO.getPhone_number()));
//		return reportList.get(id);
	}
	public List<Report> getAllUnprocessedReport() {
		return getAllReport().stream().filter(e -> e.getProcessStatus() == ReportProcessStatus.Unprocessed).toList();
//		return reportList.getAllUnprocessedReport();
	}
	public List<Report> getAllCompletedReport() {
		return getAllReport().stream().filter(e -> e.getProcessStatus() == ReportProcessStatus.Completed).toList();
//		return reportList.getAllCompletedReport();
	}
	public Contract getAutomobileByCustomerId(int customerID) throws NotExistContractException, NotExistException {
		List<ContractVO> contractVOS = contractMapper.getAllByCustomerId_Compensation(customerID);
		for (ContractVO contractVO : contractVOS) {
			Product product = getProductById(contractVO.getProduct_id());
			if (product instanceof Automobile)
				return getContractById(contractVO.getId());
		}
		throw new NotExistContractException();
//		return contractList.getContractByOneAutomobileId(customerID);
	}
}