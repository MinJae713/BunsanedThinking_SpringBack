package com.example.bunsanedthinking_springback.model.service.employee.sales;

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
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
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

		Integer maxId = customerEntityModel.getMaxId();
		int customerId;
		if (maxId == null) {
			customerId = Integer.parseInt(Customer.CUSTOMER_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((Customer.CUSTOMER_SERIAL_NUMBER + "").length());
			customerId = Integer.parseInt((Customer.CUSTOMER_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}

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

		if (induceDTO.getAccidentHistoryList() != null) {
			Integer accidentHistoryMaxId = accidentHistoryEntityModel.getMaxId();
			int accidentHistoryId;
			int maxIndex;
			if (accidentHistoryMaxId == null) {
				accidentHistoryId = Integer.parseInt(AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else {
				String index = (accidentHistoryMaxId + "").substring(
					(AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "").length());
				maxIndex = Integer.parseInt(index) + 1;
				accidentHistoryId = Integer.parseInt((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
			for (AccidentHistoryDTO e : induceDTO.getAccidentHistoryList()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);
				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(customerId);
				customer.getAccidentHistoryList().add(accidentHistory);
				maxIndex++;
				accidentHistoryId = Integer.parseInt((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}

		if (induceDTO.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryEntityModel.getMaxId();
			int surgeryHistoryId;
			int maxIndex;
			if (surgeryHistoryMaxId == null) {
				surgeryHistoryId = Integer.parseInt(SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else {
				String index = (surgeryHistoryMaxId + "").substring(
					(SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "").length());
				maxIndex = Integer.parseInt(index) + 1;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
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
				maxIndex++;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}
		if (induceDTO.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryEntityModel.getMaxId();
			int diseaseHistoryId;
			int maxIndex;
			if (diseaseHistoryMaxId == null) {
				diseaseHistoryId = Integer.parseInt(DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else {
				String index = (diseaseHistoryMaxId + "").substring(
					(DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "").length());
				maxIndex = Integer.parseInt(index) + 1;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
			for (DiseaseHistoryDTO e : induceDTO.getDiseaseHistoryList()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);
				LocalDate localDate = LocalDate.parse(e.getDate_of_diagnosis(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);
				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(customerId);
				customer.getDiseaseHistoryList().add(diseaseHistory);
				maxIndex++;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}

		Integer contractMaxId = contractEntityModel.getMaxId();
		int contractId;
		if (contractMaxId == null) {
			contractId = Integer.parseInt(Contract.CONTRACT_SERIAL_NUMBER + "1");
		} else {
			String index = (contractMaxId + "").substring((Contract.CONTRACT_SERIAL_NUMBER + "").length());
			contractId = Integer.parseInt((Contract.CONTRACT_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		Contract contract = new Contract();
		contract.setId(contractId);
		contract.setDate(null);
		contract.setExpirationDate(null);
		contract.setPaymentDate(null);
		contract.setTerminationDate(null);
		contract.setContractStatus(ContractStatus.ContractRequesting);
		contract.setCustomerID(customerId);
		contract.setEmployeeID(induceDTO.getEmployeeId());
//		contract.setProduct(productEntityModel.getById(induceDTO.getProductId()));
		// 대현님 이거 contract에 product 빼면서 로직 수정했습니다...!
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

	public ArrayList<Employee> getAllEmployee() {
		return (ArrayList<Employee>)employeeEntityModel.getAll();
	}

	public Employee getEmployee(int id) {
		return employeeEntityModel.getById(id);
	}

	public Sales getSales(int id) {
		return salesEntityModel.getById(id);
	}

	public ArrayList<Counsel> getAllCounsel() {
		return (ArrayList<Counsel>)counselEntityModel.getAll();
	}

	public Counsel getCounsel(int id) {
		return counselEntityModel.getById(id);
	}

	public ArrayList<Product> getAllProduct() {
		return (ArrayList<Product>)productEntityModel.getAll();
	}

	public DiseaseHistory addDiseaseHistory(DiseaseHistoryDTO diseaseHistoryDTO) {
		DiseaseHistory diseaseHistory = new DiseaseHistory();

		Integer diseaseHistoryId = diseaseHistoryEntityModel.getMaxId();
		if (diseaseHistoryId == null) {
			diseaseHistoryId = Integer.parseInt("" + DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + 1);
		} else {
			int diseaseHistorySerialLength = ("" + DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER).length();
			String index = (diseaseHistoryId + "").substring(diseaseHistorySerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + index;
			diseaseHistoryId = Integer.parseInt(compound);
		}

		diseaseHistory.setId(diseaseHistoryId);
		diseaseHistory.setDate_of_diagnosis(Date.from(
			LocalDate.parse(diseaseHistoryDTO.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
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

	public ArrayList<Insurance> getAllDiseaseInsurance() {
		return new ArrayList<>(diseaseEntityModel.getAll());
	}

	public ArrayList<Insurance> getAllInjuryInsurance() {
		return new ArrayList<>(injuryEntityModel.getAll());
	}

	public ArrayList<Insurance> getAllAutomobileInsurance() {
		return new ArrayList<>(automobileEntityModel.getAll());
	}

	public ArrayList<Loan> getAllCollateralLoan() {
		return new ArrayList<>(collateralEntityModel.getAll());
	}

	public ArrayList<Loan> getAllFixedDepositLoan() {
		return new ArrayList<>(fixedDepositEntityModel.getAll());
	}

	public ArrayList<Loan> getAllInsuranceContractLoan() {
		return new ArrayList<>(insuranceContractEntityModel.getAll());
	}

	public Sales getSalesContractCount(int id) {
		return getSales(id);
	}

	public void setContractCount(int contractCount, int id) {
		Sales sales = salesEntityModel.getById(id);
		sales.setContractCount(++contractCount);
		salesEntityModel.update(sales);
	}
}
