package com.example.bunsanedthinking_springback.model.service.customer;

import com.example.bunsanedthinking_springback.dto.customer.request.*;
import com.example.bunsanedthinking_springback.dto.customer.response.*;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.automobile.AutomobileEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.collateral.CollateralEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.complaint.ComplaintEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.disease.DiseaseEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit.FixedDepositEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.injury.InjuryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceContract.InsuranceContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney.InsuranceMoneyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

	@Autowired
	private InsuranceEntityModel insuranceEntityModel;
	@Autowired
	private DiseaseEntityModel diseaseEntityModel;
	@Autowired
	private InjuryEntityModel injuryEntityModel;
	@Autowired
	private AutomobileEntityModel automobileEntityModel;
	@Autowired
	private LoanEntityModel loanEntityModel;
	@Autowired
	private CollateralEntityModel collateralEntityModel;
	@Autowired
	private FixedDepositEntityModel fixedDepositEntityModel;
	@Autowired
	private InsuranceContractEntityModel insuranceContractEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private EndorsementEntityModel endorsementEntityModel;
	@Autowired
	private RevivalEntityModel revivalEntityModel;
	@Autowired
	private TerminationEntityModel terminationEntityModel;
	@Autowired
	private RecontractEntityModel recontractEntityModel;
	@Autowired
	private AccidentEntityModel accidentEntityModel;
	@Autowired
	private ComplaintEntityModel complaintEntityModel;
	@Autowired
	private DepositDetailEntityModel depositDetailEntityModel;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private InsuranceMoneyEntityModel insuranceMoneyEntityModel;
	@Autowired
	private DiseaseHistoryEntityModel diseaseHistoryEntityModel;
	@Autowired
	private CounselEntityModel counselEntityModel;
	@Autowired
	private ProductEntityModel productEntityModel;

	@Value("${serials.customer}")
	public Integer CUSTOMER_SERIAL_NUMBER;
	@Value("${serials.depositDetail}")
	public Integer DEPOSIT_DETAIL_SERIAL_NUMBER;
	@Value("${serials.counsel}")
	public Integer COUNSEL_SERIAL_NUMBER;
	@Value("${serials.contract}")
	public Integer CONTRACT_SERIAL_NUMBER;
	@Value("${serials.complaint}")
	public Integer COMPLAINT_SERIAL_NUMBER;
	@Value("${serials.accident}")
	public Integer ACCIDENT_SERIAL_NUMBER;
	@Value("${serials.accidentHistory}")
	public Integer ACCIDENT_HISTORY_SERIAL_NUMBER;
	@Value("${serials.surgeryHistory}")
	public Integer SURGERY_HISTORY_SERIAL_NUMBER;
	@Value("${serials.diseaseHistory}")
	public Integer DISEASE_HISTORY_SERIAL_NUMBER;

	public void applyEndorsement(int index, int contractId) throws NotExistContractException, NotExistException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		contract.setContractStatus(ContractStatus.EndorsementRequesting);
		contractEntityModel.update(contract);
		Endorsement endorsement = endorsementEntityModel.getById(contractId);
		if (endorsement == null) {
			endorsement = new Endorsement(contract);
			endorsement.setPaymentDate(index);
			endorsementEntityModel.add(endorsement);
		} else {
			endorsement.setPaymentDate(index);
			endorsementEntityModel.update(endorsement);
		}
	}

	public void applyInsuranceRevival(int contractId)
		throws NotExistContractException, NotExistTerminatedContract, NotExistException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Terminating && contract.getExpirationDate() != null) {
			contract.setContractStatus(ContractStatus.RevivalRequesting);
			contractEntityModel.update(contract);
			if (revivalEntityModel.getById(contractId) != null) return;
			revivalEntityModel.add(new Revival(contract));
		} else {
			throw new NotExistTerminatedContract();
		}
	}

	public void applyInsuranceTermination(int contractId)
		throws NotExistContractException, NotExistMaintainedContract, NotExistException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Maintaining) {
			contract.setContractStatus(ContractStatus.TerminationRequesting);
			contractEntityModel.update(contract);
			if (terminationEntityModel.getById(contractId) != null) return;
			terminationEntityModel.add(new Termination(contract));
		} else {
			throw new NotExistMaintainedContract();
		}
	}

	public void applyRecontract(int contractId) throws NotExistContractException,
		NotExistExpiredContract, NotExistException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Maturing) {
			contract.setContractStatus(ContractStatus.RecontractRequesting);
			contractEntityModel.update(contract);
			if (recontractEntityModel.getById(contractId) != null) return;
			recontractEntityModel.add(new Recontract(contract));
		} else {
			throw new NotExistExpiredContract();
		}
	}

	public void payInsurancefee(DepositDTO depositDTO)
		throws NotExistContractException, NotExistException {
		String depositorName = depositDTO.getDepositorName();
		int contractId = depositDTO.getContractId();
		int money = depositDTO.getMoney();
		DepositPath depositPath = DepositPath.values()[depositDTO.getDepositPath()];
		if (contractEntityModel.getById(contractId) == null)
			throw new NotExistContractException();
		int depositId = NextIdGetter.getNextId(depositDetailEntityModel.getMaxId(), DEPOSIT_DETAIL_SERIAL_NUMBER);
		depositDetailEntityModel.add(new DepositDetail(depositId, depositorName,
			contractId, money, depositPath));
	}

	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerEntityModel.getById(id);
	}

	public List<GetAllInsuranceResponse> getAllInsurance() {
		List<GetAllInsuranceResponse> result = new ArrayList<GetAllInsuranceResponse>();
		for (Insurance insurance : insuranceEntityModel.getAll()) {
			String name = insurance.getName();
			String insuranceType = insurance.getInsuranceType().getName();
			int id = insurance.getId();
			int ageRange = insurance.getAgeRange();
			int monthlyPremium = insurance.getMonthlyPremium();
			result.add(new GetAllInsuranceResponse(name,
					insuranceType, id, ageRange, monthlyPremium));
		}
		return result;
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

	public Insurance getInsuranceByProductId(int id) throws NotExistException {
		return insuranceEntityModel.getById(id);
	}

	public List<GetAllLoanReponse> getAllLoan() {
		List<GetAllLoanReponse> result = new ArrayList<GetAllLoanReponse>();
		for (Loan loan : loanEntityModel.getAll()) {
			String name = loan.getName();
			String loanType = loan.getLoanType().getName();
			int id = loan.getId();
			int interestRate = loan.getInterestRate();
			int maximumMoney = loan.getMaximumMoney();
			result.add(new GetAllLoanReponse(name, loanType,
					id, interestRate, maximumMoney));
		}
		return result;
	}

	public List<Collateral> getAllCollateralLoan() {
		return collateralEntityModel.getAll();
	}

	public List<FixedDeposit> getAllFixedDepositLoan() {
		return fixedDepositEntityModel.getAll();
	}

	public List<InsuranceContract> getAllInsuranceContractLoan(ProductList productList) {
		return insuranceContractEntityModel.getAll();
	}

	public Loan getLoanByProductId(int id) throws NotExistException {
		return loanEntityModel.getById(id);
	}

	public List<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().stream().filter(
			e -> e.getContractStatus() != ContractStatus.ContractRequesting &&
				e.getExpirationDate() != null).toList();
	}

	public List<GetAllContractByCustomerIdResponse> getAllContractByCustomerId(int id)
			throws NotExistContractException, NotExistException {
		List<GetAllContractByCustomerIdResponse> result = new ArrayList<GetAllContractByCustomerIdResponse>();
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == id).toList();
		for (Contract contract : contracts) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (!(product instanceof Insurance)) continue;
			Insurance insurance = (Insurance) product;

			String name = insurance.getName();
			String type = insurance.getInsuranceType().getName();
			int insuranceId = insurance.getId();
			int ageRange = insurance.getAgeRange();
			int monthlyPremium = insurance.getMonthlyPremium();
			String expirationDate = contract.getExpirationDate();
			String date = contract.getDate();
			int paymentDate = contract.getPaymentDate();
			String status = contract.getContractStatus().getText();
			result.add(new GetAllContractByCustomerIdResponse(name,
					type, insuranceId, ageRange, monthlyPremium,
					expirationDate, date, paymentDate, status));
		}
		return result;
	}

	public List<Contract> getAllAutomobileInsuranceContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		for (Automobile automobile : automobileEntityModel.getAll())
            result.addAll(getAllContractByProductId(automobile.getId()));
		return result;
		//		return contractList.getAllAutomobileInsuranceContract();
	}

	public List<Contract> getAllInjuryInsuranceContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		for (Injury injury : injuryEntityModel.getAll())
			result.addAll(getAllContractByProductId(injury.getId()));
		return result;
	}

	public List<Contract> getAllDiseaseInsuranceContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		for (Disease disease : diseaseEntityModel.getAll())
			result.addAll(getAllContractByProductId(disease.getId()));
		return result;
	}

	public List<Contract> getAllContractByProductId(int id) throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().stream().filter(e -> e.getProductId() == id).toList();
	}

	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractEntityModel.getById(contractId);
	}

	public Contract getContractByOneAutomobileId(int id) throws NotExistContractException, NotExistException {
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == id).toList();
		for (Contract contract : contracts) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (product instanceof Insurance) {
				Insurance insurance = (Insurance)product;
				if (insurance.getInsuranceType() == InsuranceType.Automobile)
					return contract;
			}
		}
		throw new NotExistContractException();
	}

	public List<GetAllAccidentByCustomerIdResponse> getAllAccidentByCustomerId(int id) throws NotExistException {
		List<GetAllAccidentByCustomerIdResponse> result = new ArrayList<GetAllAccidentByCustomerIdResponse>();
		List<Accident> accidents = accidentEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == id).toList();
		for (Accident accident : accidents) {
			int accidentId  = accident.getId();
			String serviceType = accident.getServiceType().getName();
			String date = accident.getDate();
			String customerName = accident.getCustomerName();
			String customerPhoneNumber = accident.getCustomerPhoneNumber();
			String processStatus = accident.getProcessStatus().getName();
			result.add(new GetAllAccidentByCustomerIdResponse(accidentId, serviceType, date,
					customerName, customerPhoneNumber, processStatus));
		}
		return result;
	}

	public Accident getAccidentById(int id) throws NotExistException {
		return accidentEntityModel.getById(id);
	}

	public List<GetAllComplaintsByCustomerIdResponse> getAllComplaintsByCustomerId(int id)
			throws NotExistException {
		List<GetAllComplaintsByCustomerIdResponse> result =
				new ArrayList<GetAllComplaintsByCustomerIdResponse>();
		List<Complaint> complaints = complaintEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == id).toList();
		for (Complaint complaint : complaints) {
			String type = complaint.getComplaintType().getName();
			int complaintId = complaint.getId();
			String title = complaint.getTitle();
			String postDate = complaint.getPostDate();
			java.util.Date processingDate = complaint.getProcessingDate();
			String status = complaint.getProcessStatus().getText();
			result.add(new GetAllComplaintsByCustomerIdResponse(type, complaintId,
					title, postDate, processingDate, status));
		}
		return result;
	}

	public Complaint getComplaintById(int id) throws NotExistException {
		return complaintEntityModel.getById(id);
	}

	// 새로 추가됨 - controller는 아직 추가x
	public void signUp(SignUpDTO signUpDTO) throws DuplicateResidentRegistrationNumberException {
		// 이거 고객 아이디를 직접 입력받나유...???
		String name = signUpDTO.getName();
		String phoneNumber = signUpDTO.getPhoneNumber();
		String job = signUpDTO.getJob();
		int age = signUpDTO.getAge();
		Gender gender = Gender.values()[signUpDTO.getGender()];
		String residentRegistrationNumber = signUpDTO.getResidentRegistrationNumber();
		String address = signUpDTO.getAddress();
		long property = signUpDTO.getProperty();
		String bankName = signUpDTO.getBankName();
		String bankAccount = signUpDTO.getBankAccount();
		List<AccidentHistory> tempAccidentHistoryList = signUpDTO.getTempAccidentHistoryList();
		List<SurgeryHistory> tempSurgeryHistoryList = signUpDTO.getTempSurgeryHistoryList();
		List<DiseaseHistory> tempDiseaseHistoryList = signUpDTO.getTempDiseaseHistoryList();

		for (Customer customer : customerEntityModel.getAll())
			if (customer.getResidentRegistrationNumber().equals(residentRegistrationNumber))
				throw new DuplicateResidentRegistrationNumberException();
		Customer customer = new Customer(name, phoneNumber, job, age, gender,
				residentRegistrationNumber, address, property, bankName, bankAccount);

		// 이 생성자 쓰면 엔티티 7개는 요소가 아얘 없는 ArrayList가 생김 (널포인터 방지 ㄱㄴ)
		customer.setId(NextIdGetter.getNextId(customerEntityModel.getMaxId(), CUSTOMER_SERIAL_NUMBER));
		customerEntityModel.add(customer);
		// add 시점에 customer의 엔티티 7개는 요소가 아얘 없으니 dmodel add 시 각 테이블에 정보가 추가되지 않음
		// 단, accidenthistory, surgeryhistory, diseasehistory의 경우,
		// 파라미터로 받아온 요소가 있다면 아래 코드로 각 테이블에 추가됨 (customerDModel과 별개임)
		if (tempAccidentHistoryList != null)
			for (AccidentHistory e : tempAccidentHistoryList) {
				e.setCustomerID(customer.getId());
				e.setId(NextIdGetter.getNextId(accidentEntityModel.getMaxId(),
						ACCIDENT_HISTORY_SERIAL_NUMBER));
				accidentHistoryEntityModel.add(e);
			}
		if (tempSurgeryHistoryList != null)
			for (SurgeryHistory e : tempSurgeryHistoryList) {
				e.setCustomerID(customer.getId());
				e.setId(NextIdGetter.getNextId(surgeryHistoryEntityModel.getMaxId(),
						SURGERY_HISTORY_SERIAL_NUMBER));
				surgeryHistoryEntityModel.add(e);
			}
		if (tempDiseaseHistoryList != null)
			for (DiseaseHistory e : tempDiseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				e.setId(NextIdGetter.getNextId(diseaseHistoryEntityModel.getMaxId(),
						DISEASE_HISTORY_SERIAL_NUMBER));
				diseaseHistoryEntityModel.add(e);
			}
	}

	public void askInsuranceCounsel(AskInsuranceCounselDTO askInsuranceCounselDTO) throws NotExistException {

		int insuranceId = askInsuranceCounselDTO.getInsuranceId();
		if (insuranceEntityModel.getById(insuranceId) == null) throw new NotExistException("해당 보험이 없습니다.");
		int customerId = askInsuranceCounselDTO.getCustomerId();
		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		String name = customer.getName();
		String phoneNumber = customer.getPhoneNumber();
		String job = customer.getJob();
		int age = customer.getAge();
		Gender gender = customer.getGender();
//		askInsurnaceCounsel - name, phoneNumber, job, age, gender는 고객 정보가 아닌가 싶네유
//		ㄴ 이거인거 같아서 파라미터에서는 뺌, customerId 받으면 해당 고객 정보에서 다섯 정보 받아내는걸로함(맞나유..?)
		Date counselDate = askInsuranceCounselDTO.getCounselDate();
		Counsel counsel = new Counsel(customerId, insuranceId, name, phoneNumber, counselDate, job, age, gender);
		counsel.setId(NextIdGetter.getNextId(counselEntityModel.getMaxId(), COUNSEL_SERIAL_NUMBER));
		counselEntityModel.add(counsel);
//		customer.askInsuranceCounsel(insurance, name, phoneNumber, counselDate, job, age, gender, counselList);
	}

	public void buyInsurance(BuyInsuranceDTO buyInsuranceDTO) throws NotExistException {
		int insuranceId = buyInsuranceDTO.getInsuranceId();
		int customerId = buyInsuranceDTO.getCustomerId();
		Integer employeeId = buyInsuranceDTO.getEmployeeId();
		// employeeId는 null 허용 - 이 시점에서 직원 아이디를 받나유...??

		if (customerEntityModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Insurance insurance = insuranceEntityModel.getById(insuranceId);
		if (insurance == null) throw new NotExistException("지정된 보험이 없습니다.");
		Contract contract = new Contract(customerId, insuranceId);
		contract.setId(NextIdGetter.getNextId(contractEntityModel.getMaxId(), CONTRACT_SERIAL_NUMBER));
		contract.setEmployeeID(employeeId);
		contractEntityModel.add(contract);
		// id, customerId, insurnace 이외에는 아무 정보도 지정X
//		return customer.buyInsurance(insurance, contractList);
	}

	public void complain(ComplainDTO complainDTO) throws NotExistException {
		ComplaintType complainType = ComplaintType.values()[complainDTO.getComplainType()];
		String title = complainDTO.getTitle();
		String content = complainDTO.getContent();
		int customerId = complainDTO.getCustomerId();

		if (customerEntityModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Complaint complaint = new Complaint(complainType, content, customerId, title);
		complaint.setId(NextIdGetter.getNextId(complaintEntityModel.getMaxId(), COMPLAINT_SERIAL_NUMBER));
		complaintEntityModel.add(complaint);
//		customer.complain(complaintList, customerList, complainType, title, content);
	}

	public void loan(LoanDTO loanDTO) throws AlreadyRequestingException, NotExistException {
		int loanId = loanDTO.getLoanId();
		int customerId = loanDTO.getCustomerId();
		Integer employeeId = loanDTO.getEmployeeId();

		if (customerEntityModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Loan loan = loanEntityModel.getById(loanId);
		if (loan == null) throw new NotExistException("해당 대출이 없습니다.");
		List<Contract> myContractList = contractEntityModel.getAll().stream().filter(e -> e.getCustomerID() == customerId).toList();
		if (myContractList.isEmpty()) myContractList = new ArrayList<>();
		for (Contract contract : myContractList)
			if (contract.getProductId() == loanId)
				throw new AlreadyRequestingException();
		Contract contract = new Contract(customerId, loanId);
		contract.setId(NextIdGetter.getNextId(contractEntityModel.getMaxId(), CONTRACT_SERIAL_NUMBER));
		contract.setEmployeeID(employeeId);
		contractEntityModel.add(contract);
//		return customer.loan(loan, contractList);
	}

	public void receiveInsurance(ReceiveInsuranceDTO receiveInsuranceDTO) throws NotExistContractException, NotExistException {
		int contractId = receiveInsuranceDTO.getContractId();
		int customerId = receiveInsuranceDTO.getCustomerId();
		BufferedImage medicalCertificateImage = receiveInsuranceDTO.getMedicalCertificateImage();
		BufferedImage receiptImage = receiveInsuranceDTO.getReceiptImage();
		BufferedImage residentRegistrationCardImage = receiveInsuranceDTO.getResidentRegistrationCardImage();
		// 일단 이미지도 DTO로 받는 형태 - 이건 다시 얘기해봅시다

		if (contractEntityModel.getById(contractId) == null) throw new NotExistContractException();
		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		if (contractEntityModel.getById(contractId).getCustomerID() != customerId)
			throw new NotExistException("해당 고객이 가입한 계약이 아닙니다");
		InsuranceMoney insuranceMoney = new InsuranceMoney(contractId, customer.getBankName(),
				customer.getBankAccount(), medicalCertificateImage,
				receiptImage, residentRegistrationCardImage);
		insuranceMoney.setId(NextIdGetter.getNextId(insuranceMoneyEntityModel.getMaxId(), 00));
		// InsuranceMoney가 Serial이 없네유...??
		// serial 수정하면 더미데이터도 수정해야합니다 - 근데 넣는게 맞긴 할듯
		insuranceMoneyEntityModel.add(insuranceMoney);
//		customer.receiveInsurance(contract, medicalCertificateImage, receiptImage, residentRegistrationCardImage,
//			insuranceMoneyList);
	}

	public void reportAccident(ReportAccidentDTO reportAccidentDTO) throws NotExistException {
		Date accidentDate = reportAccidentDTO.getAccidentDate();
		String location = reportAccidentDTO.getLocation();
		ServiceType serviceType = ServiceType.values()[reportAccidentDTO.getServiceType()];
		int customerId = reportAccidentDTO.getCustomerId();

		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		Accident accident = new Accident();
		accident.report(customerId, customer.getName(), customer.getPhoneNumber(), accidentDate, location, serviceType);
		accident.setId(NextIdGetter.getNextId(accidentEntityModel.getMaxId(), ACCIDENT_SERIAL_NUMBER));
		accidentEntityModel.add(accident);
//		customer.reportAccident(customerName, customerPhoneNumber, accidentDate, location, serviceType,
//				accidentList);
	}
}
