package com.example.bunsanedthinking_springback.model.sales;

import static com.example.bunsanedthinking_springback.entity.loan.LoanType.*;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import com.example.bunsanedthinking_springback.repository.AutomobileMapper;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.CounselMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceContractMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.repository.SalesMapper;
import com.example.bunsanedthinking_springback.repository.ServiceMapper;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import com.example.bunsanedthinking_springback.vo.CounselVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.SalesVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SalesModel {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
	@Autowired
	private AutomobileMapper automobileMapper;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private InjuryMapper injuryMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private CollateralMapper collateralMapper;
	@Autowired
	private FixedDepositMapper fixedDepositMapper;
	@Autowired
	private InsuranceContractMapper insuranceContractMapper;

	@Autowired
	private SalesMapper salesMapper;

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AccidentHistoryMapper accidentHistoryMapper;
	@Autowired
	private SurgeryHistoryMapper surgeryHistoryMapper;
	@Autowired
	private DiseaseHistoryMapper diseaseHistoryMapper;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private CounselMapper counselMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	public void evaluateSalesPerformance(int evaluate, Sales sales, EmployeeList employeeList) throws
		NotExistException {
		sales.setEvaluate(evaluate);
		salesMapper.updateEvaluate_SalesModel(sales.getId(), evaluate);
		// employeeList.update(sales);
	}

	public void handleInsuranceConsultation(Counsel counsel, CounselList counselList) throws
		NotExistException,
		AlreadyProcessedException {
		if (counsel.getProcessStatus() == CounselProcessStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		counsel.handle();
		CounselVO counselVO = new CounselVO();
		counselVO.setId(counsel.getId());
		counselVO.setCounsel_date(LocalDate.parse(counsel.getCounselDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		counselVO.setProcess_status(counsel.getProcessStatus().getValue());
		counselVO.setCustomer_id(counsel.getCustomerID());
		counselVO.setProduct_id(counsel.getProductID());
		counselMapper.update_SalesModel(counselVO);
		// counselList.update(counsel);
	}

	public Customer induceInsuranceProduct(String name, String address, String bankAccount, String bankName,
		String phoneNumber,
		String job, long property, String residentRegistrationNumber, int age, Gender gender,
		ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
		ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList,
		ContractList contractList) {

		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
			property, bankName, bankAccount);
		CustomerVO customerVO = new CustomerVO();
		customerVO.setId(customer.getId());
		customerVO.setAddress(customer.getAddress());
		customerVO.setAge(customer.getAge());
		customerVO.setBank_account(customer.getBankAccount());
		customerVO.setBank_name(customer.getBankName());
		customerVO.setGender(customer.getGender().getValue());
		customerVO.setJob(customer.getJob());
		customerVO.setName(customer.getName());
		customerVO.setPhone_number(customer.getPhoneNumber());
		customerVO.setProperty(customer.getProperty());
		customerVO.setResident_registration_number(customer.getResidentRegistrationNumber());
		customerMapper.insert_SalesModel(customerVO);
		// customerList.add(customer);

		if (accidentHistoryList != null) {
			for (AccidentHistory e : accidentHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setAccidentHistoryList(accidentHistoryList);

				AccidentHistoryVO accidentHistoryVO = new AccidentHistoryVO();
				accidentHistoryVO.setId(e.getId());
				accidentHistoryVO.setDate(LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				accidentHistoryVO.setDetails_of_accident(e.getAccidentDetail());
				accidentHistoryVO.setCustomer_id(e.getCustomerID());
				accidentHistoryMapper.insert_SalesModel(accidentHistoryVO);
				// accidentHistoryList.add(e);
			}
		}
		if (surgeryHistoryList != null) {
			for (SurgeryHistory e : surgeryHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setSurgeryHistoryList(surgeryHistoryList);

				SurgeryHistoryVO surgeryHistoryVO = new SurgeryHistoryVO();
				surgeryHistoryVO.setId(e.getId());
				surgeryHistoryVO.setHospital_name(e.getHospitalName());
				surgeryHistoryVO.setName(e.getName());
				surgeryHistoryVO.setDate(LocalDateTime.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				surgeryHistoryVO.setCustomer_id(e.getCustomerID());
				surgeryHistoryMapper.insert_SalesModel(surgeryHistoryVO);
				// surgeryHistoryList.add(e);
			}
		}
		if (diseaseHistoryList != null) {
			for (DiseaseHistory e : diseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				customer.setDiseaseHistoryList(diseaseHistoryList);

				DiseaseHistoryVO diseaseHistoryVO = new DiseaseHistoryVO();
				diseaseHistoryVO.setId(e.getId());
				diseaseHistoryVO.setDate_of_diagnosis(LocalDate.parse(e.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				diseaseHistoryVO.setName(e.getName());
				diseaseHistoryVO.setCustomer_id(e.getCustomer_id());
				diseaseHistoryMapper.insert_SalesModel(diseaseHistoryVO);
				// diseaseHistoryList.add(e);
			}
		}

		Contract contract = new Contract(customer.getId(), product);
		ContractVO contractVO = new ContractVO();
		contractVO.setId(contract.getId());
		contractVO.setDate(LocalDate.parse(contract.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (contract.getExpirationDate() != null) {
			contractVO.setExpiration_date(LocalDate.parse(contract.getExpirationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		} else {
			contractVO.setExpiration_date(null);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		contractVO.setPayment_date(LocalDate.parse(String.valueOf(contract.getPaymentDate()), formatter));

		contractVO.setTermination_date(LocalDate.parse(contract.getTerminationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		contractVO.setContract_status(contract.getContractStatus().getValue());
		contractVO.setCustomer_id(contract.getCustomerID());
		contractVO.setEmployee_id(contract.getEmployeeID());
		contractVO.setProduct_id(contract.getProduct().getId());
		contractVO.setLastpaid_date(contract.getLastPaidDate() != null ? LocalDate.parse(contract.getLastPaidDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		contractMapper.insert_SalesModel(contractVO);
		// contractList.add(contract);

		return customer;
	}

	public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {

		Insurance insurance = null;

		ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
		for (DiseaseVO diseaseVO : diseaseVOs) {
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			if(productVO.getId() == id){
				Disease disease = new Disease();

				disease.setId(insuranceVO.getProduct_id());
				disease.setName(productVO.getName());
				disease.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
				disease.setAgeRange(insuranceVO.getAge_range());
				disease.setCoverage(insuranceVO.getCoverage());
				disease.setMonthlyPremium(insuranceVO.getMonthly_premium());
				disease.setContractPeriod(insuranceVO.getContract_period());

				disease.setDiseaseLimit(diseaseVO.getDisease_limit());
				disease.setDiseaseName(diseaseVO.getDisease_name());
				disease.setSurgeriesLimit(diseaseVO.getSurgeries_limit());

				insurance = disease;
			}
		}
		ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
		for (InjuryVO injuryVO : injuryVOs) {
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			if(productVO.getId() == id){
				Injury injury = new Injury();

				injury.setId(insuranceVO.getProduct_id());
				injury.setName(productVO.getName());
				injury.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
				injury.setAgeRange(insuranceVO.getAge_range());
				injury.setCoverage(insuranceVO.getCoverage());
				injury.setMonthlyPremium(insuranceVO.getMonthly_premium());
				injury.setContractPeriod(insuranceVO.getContract_period());

				injury.setInjuryType(InjuryType.fromInt(injuryVO.getInjury_type()));
				injury.setSurgeriesLimit(injuryVO.getSurgeries_limit());

				insurance = injury;
			}
		}
		ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
		for (AutoMobileVO automobileVO : AutomobileVOs) {
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

			if(productVO.getId() == id){
				Automobile automobile = new Automobile();

				automobile.setId(insuranceVO.getProduct_id());
				automobile.setName(productVO.getName());
				automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
				automobile.setAgeRange(insuranceVO.getAge_range());
				automobile.setCoverage(insuranceVO.getCoverage());
				automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
				automobile.setContractPeriod(insuranceVO.getContract_period());

				automobile.setAccidentLimit(automobileVO.getAccident_limit());
				automobile.setVehicleType(VehicleType.fromInt(automobileVO.getVehicle_type()));
				ArrayList<ServiceType> serviceTypes = new ArrayList<>();
				ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(id);
				for (ServiceVO serviceVO :serviceVOs)
					serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
				automobile.setServiceList(serviceTypes);

				insurance = automobile;
			}
		}

		return insurance;
		// return (Insurance)productList.get(id);
	}

	public Customer induceLoanProduct(String name, String address, String bankAccount, String bankName,
		String phoneNumber,
		String job, long property, String residentRegistrationNumber, int age, Gender gender,
		ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
		ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList,
		ContractList contractList) {

		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
			property, bankName, bankAccount);
		CustomerVO customerVO = new CustomerVO();
		customerVO.setId(customer.getId());
		customerVO.setAddress(customer.getAddress());
		customerVO.setAge(customer.getAge());
		customerVO.setBank_account(customer.getBankAccount());
		customerVO.setBank_name(customer.getBankName());
		customerVO.setGender(customer.getGender().getValue());
		customerVO.setJob(customer.getJob());
		customerVO.setName(customer.getName());
		customerVO.setPhone_number(customer.getPhoneNumber());
		customerVO.setProperty(customer.getProperty());
		customerVO.setResident_registration_number(customer.getResidentRegistrationNumber());
		customerMapper.insert_SalesModel(customerVO);
		// customerList.add(customer);

		if (accidentHistoryList != null) {
			for (AccidentHistory e : accidentHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setAccidentHistoryList(accidentHistoryList);

				AccidentHistoryVO accidentHistoryVO = new AccidentHistoryVO();
				accidentHistoryVO.setId(e.getId());
				accidentHistoryVO.setDate(LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				accidentHistoryVO.setDetails_of_accident(e.getAccidentDetail());
				accidentHistoryVO.setCustomer_id(e.getCustomerID());
				accidentHistoryMapper.insert_SalesModel(accidentHistoryVO);
				// accidentHistoryList.add(e);
			}
		}
		if (surgeryHistoryList != null) {
			for (SurgeryHistory e : surgeryHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setSurgeryHistoryList(surgeryHistoryList);

				SurgeryHistoryVO surgeryHistoryVO = new SurgeryHistoryVO();
				surgeryHistoryVO.setId(e.getId());
				surgeryHistoryVO.setHospital_name(e.getHospitalName());
				surgeryHistoryVO.setName(e.getName());
				surgeryHistoryVO.setDate(LocalDateTime.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				surgeryHistoryVO.setCustomer_id(e.getCustomerID());
				surgeryHistoryMapper.insert_SalesModel(surgeryHistoryVO);
				// surgeryHistoryList.add(e);
			}
		}
		if (diseaseHistoryList != null) {
			for (DiseaseHistory e : diseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				customer.setDiseaseHistoryList(diseaseHistoryList);

				DiseaseHistoryVO diseaseHistoryVO = new DiseaseHistoryVO();
				diseaseHistoryVO.setId(e.getId());
				diseaseHistoryVO.setDate_of_diagnosis(LocalDate.parse(e.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				diseaseHistoryVO.setName(e.getName());
				diseaseHistoryVO.setCustomer_id(e.getCustomer_id());
				diseaseHistoryMapper.insert_SalesModel(diseaseHistoryVO);
				// diseaseHistoryList.add(e);
			}
		}

		Contract contract = new Contract(customer.getId(), product);
		ContractVO contractVO = new ContractVO();
		contractVO.setId(contract.getId());
		contractVO.setDate(LocalDate.parse(contract.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (contract.getExpirationDate() != null) {
			contractVO.setExpiration_date(LocalDate.parse(contract.getExpirationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		} else {
			contractVO.setExpiration_date(null);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		contractVO.setPayment_date(LocalDate.parse(String.valueOf(contract.getPaymentDate()), formatter));

		contractVO.setTermination_date(LocalDate.parse(contract.getTerminationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		contractVO.setContract_status(contract.getContractStatus().getValue());
		contractVO.setCustomer_id(contract.getCustomerID());
		contractVO.setEmployee_id(contract.getEmployeeID());
		contractVO.setProduct_id(contract.getProduct().getId());
		contractVO.setLastpaid_date(contract.getLastPaidDate() != null ? LocalDate.parse(contract.getLastPaidDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		contractMapper.insert_SalesModel(contractVO);
		// contractList.add(contract);

		return customer;

	}

	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {

		Loan loan = null;

		ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
		for (CollateralVO collateralVO : collateralVOs) {
			LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			if (id == productVO.getId()) {
				Collateral collateral = new Collateral();

				collateral.setName(productVO.getName());
				collateral.setLoanType(fromInt(loanVO.getLoan_type()));
				collateral.setId(loanVO.getProduct_id());
				collateral.setInterestRate(loanVO.getInterest_rate());
				collateral.setMaximumMoney(productVO.getMaximum_money());
				collateral.setMinimumAsset(loanVO.getMinimum_asset());
				collateral.setCollateralType(CollateralType.fromInt(collateralVO.getCollateral_type()));
				collateral.setMinimumValue(collateralVO.getMinimum_value());

				loan = collateral;
			}
		}
		ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
		for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
			LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			if (id == productVO.getId()) {
				FixedDeposit fixedDeposit = new FixedDeposit();

				fixedDeposit.setName(productVO.getName());
				fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
				fixedDeposit.setId(loanVO.getProduct_id());
				fixedDeposit.setInterestRate(loanVO.getInterest_rate());
				fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
				fixedDeposit.setMinimumAsset(loanVO.getMinimum_asset());
				fixedDeposit.setMinimumAmount(fixedDepositVO.getMinimum_amount());

				loan = fixedDeposit;
			}
		}
		ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
		for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
			LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			if (id == productVO.getId()) {
				InsuranceContract insuranceContract = new InsuranceContract();

				insuranceContract.setName(productVO.getName());
				insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
				insuranceContract.setId(loanVO.getProduct_id());
				insuranceContract.setInterestRate(loanVO.getInterest_rate());
				insuranceContract.setMaximumMoney(productVO.getMaximum_money());
				insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());
				insuranceContract.setProductID(insuranceContractVO.getProduct_id());

				loan = insuranceContract;
			}
		}

		return loan;
		// return (Loan) productList.get(id);
	}

	public ArrayList<Employee> getAll(EmployeeList employeeList) {

		ArrayList<SalesVO> salesVOs = salesMapper.getAll_SalesModel();
		ArrayList<Sales> salesList = new ArrayList<>();
		for(SalesVO salesVO : salesVOs){
			Sales sales = new Sales();
			sales.setId(salesVO.getEmployee_id());
			sales.setEvaluate(salesVO.getEvaluate());
			sales.setContractCount(salesVO.getContract_count());
			salesList.add(sales);
		}

		for(Sales sales :salesList){
			EmployeeVO employeeVO = employeeMapper.get_SalesModel(sales.getId());
			sales.setAddress(employeeVO.getAddress());
			sales.setBankName(employeeVO.getBank_name());
			sales.setBankAccount(employeeVO.getBank_account());
			sales.setEmploymentDate(Date.from(employeeVO.getEmployment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			sales.setName(employeeVO.getName());
			sales.setPhoneNumber(employeeVO.getPhone_number());
			sales.setPosition(EmployeePosition.fromInt(employeeVO.getPosition()));
			sales.setResidentRegistrationNumber(employeeVO.getResident_registration_number());
			sales.setSalary(employeeVO.getSalary());
			sales.setDepartmentID(employeeVO.getDepartment_id());
		}

		return new ArrayList<>(salesList);
		// return employeeList.getAll();
	}

	public Employee get(EmployeeList employeeList, int id) throws NotExistException {
		Sales sales = new Sales();
		EmployeeVO employeeVO = employeeMapper.get_SalesModel(id);
		sales.setId(employeeVO.getId());
		sales.setAddress(employeeVO.getAddress());
		sales.setBankName(employeeVO.getBank_name());
		sales.setBankAccount(employeeVO.getBank_account());
		sales.setEmploymentDate(Date.from(employeeVO.getEmployment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		sales.setName(employeeVO.getName());
		sales.setPhoneNumber(employeeVO.getPhone_number());
		sales.setPosition(EmployeePosition.fromInt(employeeVO.getPosition()));
		sales.setResidentRegistrationNumber(employeeVO.getResident_registration_number());
		sales.setSalary(employeeVO.getSalary());
		sales.setDepartmentID(employeeVO.getDepartment_id());

		SalesVO salesVO = salesMapper.get_SalesModel(id);
		sales.setId(salesVO.getEmployee_id());
		sales.setEvaluate(salesVO.getEvaluate());
		sales.setContractCount(salesVO.getContract_count());

		return sales;
		// return employeeList.get(id);
	}

	public Sales getSales(EmployeeList employeeList, int id) throws NotExistException {

		Sales sales = new Sales();
		EmployeeVO employeeVO = employeeMapper.get_SalesModel(id);
		sales.setId(employeeVO.getId());
		sales.setAddress(employeeVO.getAddress());
		sales.setBankName(employeeVO.getBank_name());
		sales.setBankAccount(employeeVO.getBank_account());
		sales.setEmploymentDate(Date.from(employeeVO.getEmployment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		sales.setName(employeeVO.getName());
		sales.setPhoneNumber(employeeVO.getPhone_number());
		sales.setPosition(EmployeePosition.fromInt(employeeVO.getPosition()));
		sales.setResidentRegistrationNumber(employeeVO.getResident_registration_number());
		sales.setSalary(employeeVO.getSalary());
		sales.setDepartmentID(employeeVO.getDepartment_id());

		SalesVO salesVO = salesMapper.get_SalesModel(id);
		sales.setId(salesVO.getEmployee_id());
		sales.setEvaluate(salesVO.getEvaluate());
		sales.setContractCount(salesVO.getContract_count());

		return sales;
		// return employeeList.getSales(id);
	}

	public ArrayList<Counsel> getAll(CounselList counselList) {
		ArrayList<Counsel> counsels = new ArrayList<>();

		ArrayList<CounselVO> counselVOs = counselMapper.getAll_SalesModel();
		for(CounselVO counselVO :counselVOs){
			Counsel counsel = new Counsel();

			counsel.setId(counselVO.getId());
			counsel.setCounselDate(Date.from(counselVO.getCounsel_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			counsel.setProcessStatus(CounselProcessStatus.fromInt(counselVO.getProcess_status()));
			counsel.setCustomerID(counselVO.getCustomer_id());
			counsel.setProductID(counselVO.getProduct_id());

			CustomerVO customerVO = customerMapper.get_SalesModel(counselVO.getCustomer_id());
			counsel.setName(customerVO.getName());
			counsel.setPhoneNumber(customerVO.getPhone_number());
			counsel.setJob(customerVO.getJob());
			counsel.setAge(customerVO.getAge());
			counsel.setGender(Gender.fromInt(customerVO.getGender()));

			counsels.add(counsel);
		}

		return counsels;
		// return counselList.getAll();
	}

	public Counsel get(CounselList counselList, int id) throws NotExistException {

		Counsel counsel = new Counsel();

		CounselVO counselVO = counselMapper.get_SalesModel(id);
		counsel.setId(counselVO.getId());
		counsel.setCounselDate(Date.from(counselVO.getCounsel_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		counsel.setProcessStatus(CounselProcessStatus.fromInt(counselVO.getProcess_status()));
		counsel.setCustomerID(counselVO.getCustomer_id());
		counsel.setProductID(counselVO.getProduct_id());

		CustomerVO customerVO = customerMapper.get_SalesModel(counselVO.getCustomer_id());
		counsel.setName(customerVO.getName());
		counsel.setPhoneNumber(customerVO.getPhone_number());
		counsel.setJob(customerVO.getJob());
		counsel.setAge(customerVO.getAge());
		counsel.setGender(Gender.fromInt(customerVO.getGender()));

		return counsel;
		// return counselList.get(id);
	}

	public ArrayList<Product> getAll(ProductList productList) {
		ArrayList<Product> products = new ArrayList<>();

		ArrayList<Insurance> insurances = new ArrayList<>();
		ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
		for (DiseaseVO diseaseVO : diseaseVOs) {
			Disease disease = new Disease();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			disease.setId(insuranceVO.getProduct_id());
			disease.setName(productVO.getName());
			disease.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			disease.setAgeRange(insuranceVO.getAge_range());
			disease.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(disease);
		}
		ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
		for (InjuryVO injuryVO : injuryVOs) {
			Injury injury = new Injury();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			injury.setId(insuranceVO.getProduct_id());
			injury.setName(productVO.getName());
			injury.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			injury.setAgeRange(insuranceVO.getAge_range());
			injury.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(injury);
		}
		ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
		for (AutoMobileVO automobileVO : AutomobileVOs) {
			Automobile automobile = new Automobile();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			automobile.setId(insuranceVO.getProduct_id());
			automobile.setName(productVO.getName());
			automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			automobile.setAgeRange(insuranceVO.getAge_range());
			automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(automobile);
		}

		ArrayList<Loan> loans = new ArrayList<>();
		ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
		for (CollateralVO collateralVO : collateralVOs) {
			Collateral collateral = new Collateral();
			LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			collateral.setName(productVO.getName());
			collateral.setLoanType(fromInt(loanVO.getLoan_type()));
			collateral.setId(loanVO.getProduct_id());
			collateral.setInterestRate(loanVO.getInterest_rate());
			collateral.setMaximumMoney(productVO.getMaximum_money());
			loans.add(collateral);
		}
		ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
		for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
			FixedDeposit fixedDeposit = new FixedDeposit();
			LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			fixedDeposit.setName(productVO.getName());
			fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
			fixedDeposit.setId(loanVO.getProduct_id());
			fixedDeposit.setInterestRate(loanVO.getInterest_rate());
			fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
			loans.add(fixedDeposit);
		}
		ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
		for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
			InsuranceContract insuranceContract = new InsuranceContract();
			LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			insuranceContract.setName(productVO.getName());
			insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
			insuranceContract.setId(loanVO.getProduct_id());
			insuranceContract.setInterestRate(loanVO.getInterest_rate());
			insuranceContract.setMaximumMoney(productVO.getMaximum_money());
			loans.add(insuranceContract);
		}
		products.addAll(insurances);
		products.addAll(loans);
		return products;
	}

	public void add(DiseaseHistoryList diseaseHistoryList, DiseaseHistory diseaseHistory) {
		DiseaseHistoryVO diseaseHistoryVO = new DiseaseHistoryVO();
		diseaseHistoryVO.setId(diseaseHistory.getId());
		diseaseHistoryVO.setDate_of_diagnosis(LocalDate.parse(diseaseHistory.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		diseaseHistoryVO.setName(diseaseHistory.getName());
		diseaseHistoryVO.setCustomer_id(diseaseHistory.getCustomer_id());
		diseaseHistoryMapper.insert_SalesModel(diseaseHistoryVO);
		// diseaseHistoryList.add(diseaseHistory);
	}

	public void update(EmployeeList employeeList, Sales sales) throws NotExistException {
		SalesVO salesVO = new SalesVO();

		salesVO.setEmployee_id(sales.getId());
		salesVO.setEvaluate(sales.getEvaluate());
		salesVO.setContract_count(sales.getContractCount());

		salesMapper.update_SalesModel(salesVO);
		// employeeList.update(sales);
	}

	public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList) {
		ArrayList<Insurance> insurances = new ArrayList<>();
		ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
		for (DiseaseVO diseaseVO : diseaseVOs) {
			Disease disease = new Disease();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			disease.setId(insuranceVO.getProduct_id());
			disease.setName(productVO.getName());
			disease.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			disease.setAgeRange(insuranceVO.getAge_range());
			disease.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(disease);
		}
		return insurances;
		// return productList.getAllDiseaseInsurance();
	}

	public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList) {
		ArrayList<Insurance> insurances = new ArrayList<>();
		ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
		for (InjuryVO injuryVO : injuryVOs) {
			Injury injury = new Injury();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			injury.setId(insuranceVO.getProduct_id());
			injury.setName(productVO.getName());
			injury.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			injury.setAgeRange(insuranceVO.getAge_range());
			injury.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(injury);
		}
		return insurances;
		// return productList.getAllInjuryInsurance();
	}

	public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList) {
		ArrayList<Insurance> insurances = new ArrayList<>();
		ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
		for (AutoMobileVO automobileVO : AutomobileVOs) {
			Automobile automobile = new Automobile();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			automobile.setId(insuranceVO.getProduct_id());
			automobile.setName(productVO.getName());
			automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			automobile.setAgeRange(insuranceVO.getAge_range());
			automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(automobile);
		}
		return insurances;
		// return productList.getAllAutomobileInsurance();
	}

	public ArrayList<Loan> getAllCollateralLoan(ProductList productList) {
		ArrayList<Loan> loans = new ArrayList<>();
		ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
		for (CollateralVO collateralVO : collateralVOs) {
			Collateral collateral = new Collateral();
			LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			collateral.setName(productVO.getName());
			collateral.setLoanType(fromInt(loanVO.getLoan_type()));
			collateral.setId(loanVO.getProduct_id());
			collateral.setInterestRate(loanVO.getInterest_rate());
			collateral.setMaximumMoney(productVO.getMaximum_money());
			loans.add(collateral);
		}
		return loans;
		// return productList.getAllCollateralLoan();
	}

	public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList) {
		ArrayList<Loan> loans = new ArrayList<>();
		ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
		for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
			FixedDeposit fixedDeposit = new FixedDeposit();
			LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			fixedDeposit.setName(productVO.getName());
			fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
			fixedDeposit.setId(loanVO.getProduct_id());
			fixedDeposit.setInterestRate(loanVO.getInterest_rate());
			fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
			loans.add(fixedDeposit);
		}
		return loans;
		// return productList.getAllFixedDepositLoan();
	}

	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList) {
		ArrayList<Loan> loans = new ArrayList<>();
		ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
		for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
			InsuranceContract insuranceContract = new InsuranceContract();
			LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
			insuranceContract.setName(productVO.getName());
			insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
			insuranceContract.setId(loanVO.getProduct_id());
			insuranceContract.setInterestRate(loanVO.getInterest_rate());
			insuranceContract.setMaximumMoney(productVO.getMaximum_money());
			loans.add(insuranceContract);
		}
		return loans;
		// return productList.getAllInsuranceContractLoan();
	}

	public Sales getSalesContractCount(EmployeeList employeeList, int id) throws NotExistException {
		Sales sales = new Sales();
		SalesVO SalesVO = salesMapper.get_SalesModel(id);
		sales.setContractCount(SalesVO.getContract_count());
		return sales;
		// return (Sales) employeeList.get(id);
	}
}
