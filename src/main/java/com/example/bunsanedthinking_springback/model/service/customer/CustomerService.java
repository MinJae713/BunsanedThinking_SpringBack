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
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
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
import java.util.stream.Collectors;

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
		int contractId = depositDTO.getContractId();
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		if (customer == null) throw new NotExistException();
		String depositorName = customer.getName();
		int money = depositDTO.getMoney();
		DepositPath depositPath = DepositPath.values()[depositDTO.getDepositPath()];

		int depositId = NextIdGetter.getNextId(depositDetailEntityModel.getMaxId(), DEPOSIT_DETAIL_SERIAL_NUMBER);
		depositDetailEntityModel.add(new DepositDetail(depositId, depositorName,
			contractId, money, depositPath));
	}

	public Customer getCustomerById(int id) {
		return customerEntityModel.getById(id);
	}

	public List<InsuranceListResponse> getAllInsurance() {
		return insuranceEntityModel.getAll().stream()
				.map(InsuranceListResponse::of)
				.collect(Collectors.toList());
	}

	public List<InsuranceListResponse> getAllDiseaseInsurance() {
		return diseaseEntityModel.getAll().stream()
				.map(InsuranceListResponse::of)
				.collect(Collectors.toList());
	}

	public List<InsuranceListResponse> getAllInjuryInsurance() {
		return injuryEntityModel.getAll().stream()
				.map(InsuranceListResponse::of)
				.collect(Collectors.toList());
	}

	public List<InsuranceListResponse> getAllAutomobileInsurance() {
		return automobileEntityModel.getAll().stream()
				.map(InsuranceListResponse::of)
				.collect(Collectors.toList());
	}

	public Insurance getInsuranceByProductId(int id) throws NotExistException {
		Insurance insurance = insuranceEntityModel.getById(id);
		if (insurance == null) throw new NotExistException();
		return insurance;
	}

    public InsuranceListResponse getInsuranceRowByProductId(int id) throws NotExistException {
        return InsuranceListResponse.of(getInsuranceByProductId(id));
    } // 추가

	public List<LoanListReponse> getAllLoan() {
		return loanEntityModel.getAll().stream()
				.map(LoanListReponse::of)
				.collect(Collectors.toList());
	}

	public List<LoanListReponse> getAllCollateralLoan() {
		return collateralEntityModel.getAll().stream()
				.map(LoanListReponse::of)
				.collect(Collectors.toList());
	}

	public List<LoanListReponse> getAllFixedDepositLoan() {
		return fixedDepositEntityModel.getAll().stream()
				.map(LoanListReponse::of)
				.collect(Collectors.toList());
	}

	public List<LoanListReponse> getAllInsuranceContractLoan() {
		return insuranceContractEntityModel.getAll().stream()
				.map(LoanListReponse::of)
				.collect(Collectors.toList());
	}

	public Loan getLoanByProductId(int id) throws NotExistException {
		Loan loan = loanEntityModel.getById(id);
		if (loan == null) throw new NotExistException();
		return loan;
	}

	public LoanListReponse getLoanRowByProductId(int id) throws NotExistException {
		return LoanListReponse.of(getLoanByProductId(id));
	} // 추가

	public List<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().stream().filter(
			e -> e.getContractStatus() != ContractStatus.ContractRequesting &&
				e.getExpirationDate() != null).toList();
	}

	public List<ManagementContractResponse> getAllContractByCustomerId(int customerId)
			throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == customerId)
				.map(this::getOneContractByCustomerId)
				.collect(Collectors.toList());
	}

	private ManagementContractResponse getOneContractByCustomerId(Contract contract) {
		Product product = productEntityModel.getById(contract.getProductId());
		if (!(product instanceof Insurance insurance)) return null;
		return ManagementContractResponse.of(contract, insurance);
	}
	public List<ManagementContractResponse> getAllAutomobileContractByCustomerId(int customerId) throws NotExistContractException, NotExistException {
		List<ManagementContractResponse> result = new ArrayList<ManagementContractResponse>();
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == customerId).toList();
		for (Contract contract : contracts) {
			Automobile automobile = automobileEntityModel.getById(contract.getProductId());
			if (automobile == null) continue;
			result.add(ManagementContractResponse.of(contract, automobile));
		}
		return result;
	}

	public List<ManagementContractResponse> getAllInjuryContractByCustomerId(int customerId) throws NotExistContractException, NotExistException {
		List<ManagementContractResponse> result = new ArrayList<ManagementContractResponse>();
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == customerId).toList();
		for (Contract contract : contracts) {
			Injury injury = injuryEntityModel.getById(contract.getProductId());
			if (injury == null) continue;
			result.add(ManagementContractResponse.of(contract, injury));
		}
		return result;
	}

	public List<ManagementContractResponse> getAllDiseaseContractByCustomerId(int customerId) throws NotExistContractException, NotExistException {
		List<ManagementContractResponse> result = new ArrayList<ManagementContractResponse>();
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == customerId).toList();
		for (Contract contract : contracts) {
			Disease disease = diseaseEntityModel.getById(contract.getProductId());
			if (disease == null) continue;
			result.add(ManagementContractResponse.of(contract, disease));
		}
		return result;
	}
	public List<Contract> getAllContractByProductId(int id) throws NotExistContractException, NotExistException {
		return contractEntityModel.getAll().stream().filter(e -> e.getProductId() == id).toList();
	}

	public Contract getContractById(int id, int customerId) throws NotExistContractException, IllegalArgumentException {
		Contract contract = contractEntityModel.getById(id);
		if (contract == null) throw new NotExistContractException();
		if (contract.getCustomerID() != customerId) throw new IllegalArgumentException("해당 고객이 신청한 계약이 아닙니다");
		return contract;
	}

	public ManagementContractResponse getContractRowById(int id, int customerId) throws NotExistContractException, IllegalArgumentException {
		Contract contract = getContractById(id, customerId);
		Product product = productEntityModel.getById(contract.getProductId());
		if (!(product instanceof Insurance insurance)) throw new IllegalArgumentException("해당 고객이 신청한 계약이 아닙니다");
		return ManagementContractResponse.of(contract, insurance);
	} // 추가

	public Contract getContractByOneAutomobileId(int id) throws NotExistContractException, NotExistException {
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getCustomerID() == id).toList();
		for (Contract contract : contracts) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (product instanceof Insurance insurance)
                if (insurance.getInsuranceType() == InsuranceType.Automobile)
					return contract;
		}
		throw new NotExistContractException();
	}

	public List<ViewAccidentResponse> getAllAccidentByCustomerId(int id) throws NotExistException {
		return accidentEntityModel.getAll().stream()
				.filter(e -> e.getCustomerID() == id)
				.map(ViewAccidentResponse::of)
				.collect(Collectors.toList());
	} // 추가

	public Accident getAccidentById(int id, int customerId)
			throws NotExistException, IllegalArgumentException {
		Accident accident = accidentEntityModel.getById(id);
		if (accident == null) throw new NotExistException();
		if (accident.getCustomerID() != customerId)
			throw new IllegalArgumentException("고객이 신청한 사고 내역이 아닙니다");
		return accident;
	}

	public ViewAccidentResponse getAccidentRowById(int id, int customerId)
			throws NotExistException, IllegalArgumentException {
		return ViewAccidentResponse.of(getAccidentById(id, customerId));
	} // 추가

	public List<ViewComplaintResponse> getAllComplaintsByCustomerId(int id)
			throws NotExistException {
		return complaintEntityModel.getAll().stream()
				.filter(e -> e.getCustomerID() == id)
				.map(ViewComplaintResponse::of)
				.collect(Collectors.toList());
	}

	public Complaint getComplaintById(int id, int customerId)
			throws NotExistException, IllegalArgumentException {
		Complaint complaint = complaintEntityModel.getById(id);
		if (complaint == null) throw new NotExistException();
		if (complaint.getCustomerID() != customerId)
			throw new IllegalArgumentException("고객이 신청한 민원 신청 내역이 아닙니다");
		return complaint;
	}
	public ViewComplaintResponse getComplaintRowById(int id, int customerId)
			throws NotExistException, IllegalArgumentException {
		return ViewComplaintResponse.of(getComplaintById(id, customerId));
	} // 추가
	public void signUp(SignUpDTO signUpDTO) throws DuplicateResidentRegistrationNumberException {
		// 이거 고객 아이디를 직접 입력받나유...???
		String name = signUpDTO.getName();
		String phoneNumber = signUpDTO.getPhoneNumber();
		String job = signUpDTO.getJob();
		int age = signUpDTO.getAge();
		Gender gender = signUpDTO.getGender();
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

		if (customerEntityModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Insurance insurance = insuranceEntityModel.getById(insuranceId);
		if (insurance == null) throw new NotExistException("지정된 보험이 없습니다.");
		Contract contract = new Contract(customerId, insuranceId);
		contract.setId(NextIdGetter.getNextId(contractEntityModel.getMaxId(), CONTRACT_SERIAL_NUMBER));
		contract.setEmployeeID(null);
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
		contract.setEmployeeID(null);
		contractEntityModel.add(contract);
//		return customer.loan(loan, contractList);
	}

	public void receiveInsurance(ReceiveInsuranceDTO receiveInsuranceDTO) throws NotExistContractException, NotExistException {
		int contractId = receiveInsuranceDTO.getContractId();
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		BufferedImage medicalCertificateImage = receiveInsuranceDTO.getMedicalCertificateImage();
		BufferedImage receiptImage = receiveInsuranceDTO.getReceiptImage();
		BufferedImage residentRegistrationCardImage = receiveInsuranceDTO.getResidentRegistrationCardImage();
		// 일단 이미지도 DTO로 받는 형태 - 이건 다시 얘기해봅시다


		if (contractEntityModel.getById(contractId).getCustomerID() != customer.getId())
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
