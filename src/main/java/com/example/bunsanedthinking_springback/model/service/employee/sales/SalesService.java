package com.example.bunsanedthinking_springback.model.service.employee.sales;

import com.example.bunsanedthinking_springback.dto.employee.sales.request.*;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.*;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse.InduceInsuranceProductAutomobileDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse.InduceInsuranceProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse.InduceInsuranceProductDiseaseDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse.InduceInsuranceProductInjuryDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse.InduceLoanProductCollateralDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse.InduceLoanProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse.InduceLoanProductFixedDepositDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse.InduceLoanProductInsuranceContractDetailResponse;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.automobile.AutomobileEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.collateral.CollateralEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.disease.DiseaseEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit.FixedDepositEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.injury.InjuryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceContract.InsuranceContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.sales.SalesEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

	@Autowired
	private Serial serial;

	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private DiseaseHistoryEntityModel diseaseHistoryEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;

	@Autowired
	private EmployeeEntityModel employeeEntityModel;
	@Autowired
	private SalesEntityModel salesEntityModel;
	@Autowired
	private CounselEntityModel counselEntityModel;

	@Autowired
	private ProductEntityModel productEntityModel;
	@Autowired
	private InsuranceEntityModel insuranceEntityModel;
	@Autowired
	private LoanEntityModel loanEntityModel;
	@Autowired
	private CollateralEntityModel collateralEntityModel;
	@Autowired
	private FixedDepositEntityModel fixedDepositEntityModel;
	@Autowired
	private InsuranceContractEntityModel insuranceContractEntityModel;
	@Autowired
	private InjuryEntityModel injuryEntityModel;
	@Autowired
	private DiseaseEntityModel diseaseEntityModel;
	@Autowired
	private AutomobileEntityModel automobileEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public void evaluateSalesPerformance(int evaluate, int id) {
		Sales sales = salesEntityModel.getById(id);
		sales.setEvaluate(evaluate);
		salesEntityModel.update(sales);
	}

	public void handleInsuranceConsultation(int id) throws
		AlreadyProcessedException {
		Counsel counsel = counselEntityModel.getById(id);
		if (counsel.getProcessStatus() == CounselProcessStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		counsel.setProcessStatus(CounselProcessStatus.Completed);
		counselEntityModel.update(counsel);
	}

	public void induceInsuranceProduct(InduceInsuranceProductRequest induceInsuranceProductRequest) {

		Integer customerId = NextIdGetter.getNextId(customerEntityModel.getMaxId(), serial.getCustomer());

		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setAddress(induceInsuranceProductRequest.getAddress());
		customer.setAge(induceInsuranceProductRequest.getAge());
		customer.setBankAccount(induceInsuranceProductRequest.getBankAccount());
		customer.setBankName(induceInsuranceProductRequest.getBankName());
		customer.setGender(induceInsuranceProductRequest.getGender());
		customer.setJob(induceInsuranceProductRequest.getJob());
		customer.setName(induceInsuranceProductRequest.getName());
		customer.setPhoneNumber(induceInsuranceProductRequest.getPhoneNumber());
		customer.setProperty(induceInsuranceProductRequest.getProperty());
		customer.setResidentRegistrationNumber(induceInsuranceProductRequest.getResidentRegistrationNumber());
		customer.setAccidentHistoryList(new ArrayList<>());
		customer.setSurgeryHistoryList(new ArrayList<>());
		customer.setDiseaseHistoryList(new ArrayList<>());
		customer.setContractList(new ArrayList<>());

		if (induceInsuranceProductRequest.getAccidentHistories()!= null) {
			Integer accidentHistoryId = NextIdGetter.getNextId(accidentHistoryEntityModel.getMaxId(), serial.getAccidentHistory());
			for (InduceAccidentHistoryRequest e : induceInsuranceProductRequest.getAccidentHistories()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);
				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(customerId);
				customer.getAccidentHistoryList().add(accidentHistory);
				accidentHistoryId = NextIdGetter.getNextId(accidentHistoryId, serial.getAccidentHistory());
			}
		}

		if (induceInsuranceProductRequest.getSurgeryHistories() != null) {
			Integer surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryEntityModel.getMaxId(), serial.getSurgeryHistory());
			for (InduceSurgeryHistoryRequest e : induceInsuranceProductRequest.getSurgeryHistories()) {
				SurgeryHistory surgeryHistory = new SurgeryHistory();
				surgeryHistory.setId(surgeryHistoryId);
				surgeryHistory.setHospitalName(e.getHospitalName());
				surgeryHistory.setName(e.getName());
				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				surgeryHistory.setDate(date);
				surgeryHistory.setCustomerID(customerId);
				customer.getSurgeryHistoryList().add(surgeryHistory);
				surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryId, serial.getSurgeryHistory());
			}
		}
		if (induceInsuranceProductRequest.getDiseaseHistories() != null) {
			Integer diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryEntityModel.getMaxId(), serial.getDiseaseHistory());
			for (InduceDiseaseHistoryRequest e : induceInsuranceProductRequest.getDiseaseHistories()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDateOfDiagnosis(),
					DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);
				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(customerId);
				customer.getDiseaseHistoryList().add(diseaseHistory);
				diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryId, serial.getDiseaseHistory());
			}
		}

		int contractId = NextIdGetter.getNextId(contractEntityModel.getMaxId(), serial.getContract());
		Contract contract = new Contract();
		contract.setId(contractId);
		contract.setDate(new Date());
		contract.setExpirationDate(null);
		contract.setPaymentDate(null);
		contract.setTerminationDate(null);
		contract.setContractStatus(ContractStatus.ContractRequesting);
		contract.setCustomerID(customerId);
		contract.setEmployeeID(induceInsuranceProductRequest.getEmployeeId());
		contract.setProductId(induceInsuranceProductRequest.getProductId());
		contract.setLastPaidDate(null);
		customer.getContractList().add(contract);
		customerEntityModel.add(customer);

		Sales sales = salesEntityModel.getById(induceInsuranceProductRequest.getEmployeeId());
		int contractCount = sales.getContractCount();
		sales.setContractCount(++contractCount);
		salesEntityModel.update(sales);
	}

	public void induceLoanProduct(InduceInsuranceProductRequest induceInsuranceProductRequest) {
		induceInsuranceProduct(induceInsuranceProductRequest);
	}

	public InduceInsuranceProductResponse getInsuranceProduct(int id) {
		return InduceInsuranceProductResponse.from(insuranceEntityModel.getById(id));
	}

	public InduceLoanProductResponse getLoanProduct(int id) {
		return InduceLoanProductResponse.from(loanEntityModel.getById(id));
	}

	public List<EvaluateSalesPerformanceResponse> getAllSales() {
		List<Sales> sales = salesEntityModel.getAll();
		return sales.stream().map(EvaluateSalesPerformanceResponse::from).collect(Collectors.toList());
	}

	public Employee getEmployee(int id) {
		return employeeEntityModel.getById(id);
	}

	public EvaluateSalesPerformanceResponse getSales(int id) {
		return EvaluateSalesPerformanceResponse.from(salesEntityModel.getById(id));
	}

	public EvaluateSalesPerformanceDetailResponse getSalesDetail(int id) {
		return EvaluateSalesPerformanceDetailResponse.from(salesEntityModel.getById(id));
	}

	public List<HandleInsuranceConsultationResponse> getAllCounsel() {
		List<Counsel> counsels = counselEntityModel.getAll();
		return counsels.stream().map(HandleInsuranceConsultationResponse::from).collect(Collectors.toList());
	}

	public HandleInsuranceConsultationResponse getCounsel(int id) {
		Counsel counsel = counselEntityModel.getById(id);
		return HandleInsuranceConsultationResponse.from(counsel);
	}

	public HandleInsuranceConsultationDetailResponse getCounselDetail(int id) {
		Counsel counsel = counselEntityModel.getById(id);
		return HandleInsuranceConsultationDetailResponse.from(counsel);
	}

	public List<InduceLoanProductResponse> getAllLoanProduct() {
		List<Loan> loans = loanEntityModel.getAll();
		return loans.stream().map(InduceLoanProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceInsuranceProductResponse> getAllInsuranceProduct() {
		List<Insurance> insurance = insuranceEntityModel.getAll();
		return insurance.stream().map(InduceInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public DiseaseHistory addDiseaseHistory(AddDiseaseHistoryRequest addDiseaseHistoryRequest) {
		DiseaseHistory diseaseHistory = new DiseaseHistory();

		Integer diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryEntityModel.getMaxId(), serial.getDiseaseHistory());

		diseaseHistory.setId(diseaseHistoryId);
		diseaseHistory.setDate_of_diagnosis(Date.from(
			LocalDate.parse(addDiseaseHistoryRequest.getDateOfDiagnosis(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT))
				.atStartOfDay(ZoneId.systemDefault())
				.toInstant()
		));
		diseaseHistory.setName(addDiseaseHistoryRequest.getName());

		return diseaseHistory;
	}

	public void updateContractCount(int id, int contractCount) {
		Sales sales = salesEntityModel.getById(id);
		sales.setContractCount(contractCount);
		salesEntityModel.update(sales);
	}

	public List<InduceInsuranceProductResponse> getAllDiseaseInsurance() {
		List<Disease> diseases = diseaseEntityModel.getAll();
		return diseases.stream().map(InduceInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceInsuranceProductResponse> getAllInjuryInsurance() {
		List<Injury> injuries = injuryEntityModel.getAll();
		return injuries.stream().map(InduceInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceInsuranceProductResponse> getAllAutomobileInsurance() {
		List<Automobile> automobiles = automobileEntityModel.getAll();
		return automobiles.stream().map(InduceInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceLoanProductResponse> getAllCollateralLoan() {
		List<Collateral> collaterals = collateralEntityModel.getAll();
		return collaterals.stream().map(InduceLoanProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceLoanProductResponse> getAllFixedDepositLoan() {
		List<FixedDeposit> fixedDeposits = fixedDepositEntityModel.getAll();
		return fixedDeposits.stream().map(InduceLoanProductResponse::from).collect(Collectors.toList());
	}

	public List<InduceLoanProductResponse> getAllInsuranceContractLoan() {
		List<InsuranceContract> insuranceContracts = insuranceContractEntityModel.getAll();
		return insuranceContracts.stream().map(InduceLoanProductResponse::from).collect(Collectors.toList());
	}

	public int getSalesContractCount(int id) {
		return getSales(id).getContractCount();
	}

	public void setContractCount(int contractCount, int id) {
		Sales sales = salesEntityModel.getById(id);
		sales.setContractCount(++contractCount);
		salesEntityModel.update(sales);
	}

	public List<HandleInsuranceConsultationResponse> getAllCompletedCounsel() {
		List<Counsel> counsels =  counselEntityModel.getAllCompleted();
		return counsels.stream().map(HandleInsuranceConsultationResponse::from).collect(Collectors.toList());
	}

	public List<HandleInsuranceConsultationResponse> getAllUnprocessedCounsel() {
		List<Counsel> counsels =  counselEntityModel.getAllUnprocessed();
		return counsels.stream().map(HandleInsuranceConsultationResponse::from).collect(Collectors.toList());
	}

	public InduceInsuranceProductDetailResponse getInsuranceProductDetail(int id) throws NotExistException {
		Insurance insurance = insuranceEntityModel.getById(id);
		if(insurance instanceof Injury)
			return InduceInsuranceProductInjuryDetailResponse.from((Injury)insurance);
		else if (insurance instanceof Disease)
			return InduceInsuranceProductDiseaseDetailResponse.from((Disease)insurance);
		else if (insurance instanceof Automobile)
			return InduceInsuranceProductAutomobileDetailResponse.from((Automobile)insurance);
		throw new NotExistException();
	}

	public InduceLoanProductDetailResponse getLoanProductDetail(int id) throws NotExistException {
		Loan loan = loanEntityModel.getById(id);
		if(loan instanceof Collateral)
			return InduceLoanProductCollateralDetailResponse.from((Collateral)loan);
		else if (loan instanceof FixedDeposit)
			return InduceLoanProductFixedDepositDetailResponse.from((FixedDeposit)loan);
		else if (loan instanceof InsuranceContract)
			return InduceLoanProductInsuranceContractDetailResponse.from((InsuranceContract)loan);
		throw new NotExistException();
	}
}
