package com.example.bunsanedthinking_springback.model.service.employee.sales;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.sales.AccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.InduceDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.SurgeryHistoryDTO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
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
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
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

@Service
public class SalesService {

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

	@Value("${serials.customer}")
	private Integer CUSTOMER_SERIAL_NUMBER;
	@Value("${serials.diseaseHistory}")
	private Integer DISEASE_HISTORY_SERIAL_NUMBER;
	@Value("${serials.accidentHistory}")
	private Integer ACCIDENT_HISTORY_SERIAL_NUMBER;
	@Value("${serials.surgeryHistory}")
	private Integer SURGERY_HISTORY_SERIAL_NUMBER;
	@Value("${serials.contract}")
	private Integer CONTRACT_SERIAL_NUMBER;

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

	public Customer induceInsuranceProduct(InduceDTO induceDTO) {

		Integer customerId = NextIdGetter.getNextId(customerEntityModel.getMaxId(), CUSTOMER_SERIAL_NUMBER);

		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setAddress(induceDTO.getAddress());
		customer.setAge(induceDTO.getAge());
		customer.setBankAccount(induceDTO.getBankAccount());
		customer.setBankName(induceDTO.getBankName());
		customer.setGender(Gender.fromInt(induceDTO.getGender()));
		customer.setJob(induceDTO.getJob());
		customer.setName(induceDTO.getName());
		customer.setPhoneNumber(induceDTO.getPhoneNumber());
		customer.setProperty(induceDTO.getProperty());
		customer.setResidentRegistrationNumber(induceDTO.getResidentRegistrationNumber());
		customer.setAccidentHistoryList(new ArrayList<>());
		customer.setSurgeryHistoryList(new ArrayList<>());
		customer.setDiseaseHistoryList(new ArrayList<>());
		customer.setContractList(new ArrayList<>());

		if (induceDTO.getAccidentHistoryList() != null) {
			Integer accidentHistoryId = NextIdGetter.getNextId(accidentHistoryEntityModel.getMaxId(), ACCIDENT_HISTORY_SERIAL_NUMBER);
			for (AccidentHistoryDTO e : induceDTO.getAccidentHistoryList()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);
				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(customerId);
				customer.getAccidentHistoryList().add(accidentHistory);
				accidentHistoryId = NextIdGetter.getNextId(accidentHistoryId, ACCIDENT_HISTORY_SERIAL_NUMBER);
			}
		}

		if (induceDTO.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryEntityModel.getMaxId(), SURGERY_HISTORY_SERIAL_NUMBER);
			for (SurgeryHistoryDTO e : induceDTO.getSurgeryHistoryList()) {
				SurgeryHistory surgeryHistory = new SurgeryHistory();
				surgeryHistory.setId(surgeryHistoryId);
				surgeryHistory.setHospitalName(e.getHospitalName());
				surgeryHistory.setName(e.getName());
				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				surgeryHistory.setDate(date);
				surgeryHistory.setCustomerID(customerId);
				customer.getSurgeryHistoryList().add(surgeryHistory);
				surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryId, ACCIDENT_HISTORY_SERIAL_NUMBER);
			}
		}
		if (induceDTO.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryEntityModel.getMaxId(), DISEASE_HISTORY_SERIAL_NUMBER);
			for (DiseaseHistoryDTO e : induceDTO.getDiseaseHistoryList()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDateOfDiagnosis(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);
				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(customerId);
				customer.getDiseaseHistoryList().add(diseaseHistory);
				diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryId, ACCIDENT_HISTORY_SERIAL_NUMBER);
			}
		}

		int contractId = NextIdGetter.getNextId(contractEntityModel.getMaxId(), CONTRACT_SERIAL_NUMBER);
		Contract contract = new Contract();
		contract.setId(contractId);
		contract.setDate(new Date());
		contract.setExpirationDate(null);
		contract.setPaymentDate(null);
		contract.setTerminationDate(null);
		contract.setContractStatus(ContractStatus.ContractRequesting);
		contract.setCustomerID(customerId);
		contract.setEmployeeID(induceDTO.getEmployeeId());
		contract.setProductId(induceDTO.getProductId());
		contract.setLastPaidDate(null);
		customer.getContractList().add(contract);
		customerEntityModel.add(customer);

		return customer;
	}

	public Customer induceLoanProduct(InduceDTO induceDTO) {
		return induceInsuranceProduct(induceDTO);
	}

	public Insurance getInsuranceProduct(int id) {
		return insuranceEntityModel.getById(id);
	}

	public Loan getLoanProduct(int id) {
		return loanEntityModel.getById(id);
	}

	public List<Employee> getAllEmployee() {
		return employeeEntityModel.getAll();
	}

	public Employee getEmployee(int id) {
		return employeeEntityModel.getById(id);
	}

	public Sales getSales(int id) {
		return salesEntityModel.getById(id);
	}

	public List<Counsel> getAllCounsel() {
		return counselEntityModel.getAll();
	}

	public Counsel getCounsel(int id) {
		return counselEntityModel.getById(id);
	}

	public List<Product> getAllProduct() {
		return productEntityModel.getAll();
	}

	public DiseaseHistory addDiseaseHistory(DiseaseHistoryDTO diseaseHistoryDTO) {
		DiseaseHistory diseaseHistory = new DiseaseHistory();

		Integer diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryEntityModel.getMaxId(), DISEASE_HISTORY_SERIAL_NUMBER);

		diseaseHistory.setId(diseaseHistoryId);
		diseaseHistory.setDate_of_diagnosis(Date.from(
			LocalDate.parse(diseaseHistoryDTO.getDateOfDiagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				.atStartOfDay(ZoneId.systemDefault())
				.toInstant()
		));
		diseaseHistory.setName(diseaseHistoryDTO.getName());

		return diseaseHistory;
	}

	public void updateContractCount(int id, int contractCount) {
		Sales sales = salesEntityModel.getById(id);
		sales.setContractCount(contractCount);
		salesEntityModel.update(sales);
	}

	public List<Disease> getAllDiseaseInsurance() {
		return diseaseEntityModel.getAll();
	}

	public List<Injury> getAllInjuryInsurance() {
		return injuryEntityModel.getAll();
	}

	public List<Automobile> getAllAutomobileInsurance() {
		return automobileEntityModel.getAll();
	}

	public List<Collateral> getAllCollateralLoan() {
		return collateralEntityModel.getAll();
	}

	public List<FixedDeposit> getAllFixedDepositLoan() {
		return fixedDepositEntityModel.getAll();
	}

	public List<InsuranceContract> getAllInsuranceContractLoan() {
		return insuranceContractEntityModel.getAll();
	}

	public int getSalesContractCount(int id) {
		return getSales(id).getContractCount();
	}

	public void setContractCount(int contractCount, int id) {
		Sales sales = salesEntityModel.getById(id);
		sales.setContractCount(++contractCount);
		salesEntityModel.update(sales);
	}
}
