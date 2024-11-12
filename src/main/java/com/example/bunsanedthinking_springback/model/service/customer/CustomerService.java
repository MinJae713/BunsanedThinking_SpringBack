package com.example.bunsanedthinking_springback.model.service.customer;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.customer.AskInsuranceCounselDTO;
import com.example.bunsanedthinking_springback.dto.customer.BuyInsuranceDTO;
import com.example.bunsanedthinking_springback.dto.customer.ComplainDTO;
import com.example.bunsanedthinking_springback.dto.customer.DepositDTO;
import com.example.bunsanedthinking_springback.dto.customer.LoanDTO;
import com.example.bunsanedthinking_springback.dto.customer.ReceiveInsuranceDTO;
import com.example.bunsanedthinking_springback.dto.customer.ReportAccidentDTO;
import com.example.bunsanedthinking_springback.dto.customer.SignUpDTO;
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
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
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
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.automobile.AutomobileEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.collateral.CollateralEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.complaint.ComplaintEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailDModel;
import com.example.bunsanedthinking_springback.model.entityModel.disease.DiseaseDModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryDModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementDModel;
import com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit.FixedDepositEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.injury.InjuryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceContract.InsuranceContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney.InsuranceMoneyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;

@Service
public class CustomerService {

	@Autowired
	private InsuranceEntityModel insuranceDModel;
	@Autowired
	private DiseaseDModel diseaseDModel;
	@Autowired
	private InjuryEntityModel injuryDModel;
	@Autowired
	private AutomobileEntityModel automobileEntityModel;
	@Autowired
	private LoanEntityModel loanDModel;
	@Autowired
	private CollateralEntityModel collateralEntityModel;
	@Autowired
	private FixedDepositEntityModel fixedDepositDModel;
	@Autowired
	private InsuranceContractEntityModel insuranceContractDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private EndorsementDModel endorsementDModel;
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
	private DepositDetailDModel depositDetailDModel;
	@Autowired
	private AccidentHistoryDModel accidentHistoryDModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private DiseaseHistoryDModel diseaseHistoryDModel;
	@Autowired
	private CounselDModel counselDModel;
	@Autowired
	private InsuranceMoneyEntityModel insuranceMoneyEntityModel;

	public void applyEndorsement(int index, int contractId) throws NotExistContractException, NotExistException {
		// 배서(Endorsement) 납부일만 변경됨 - 기존 내용은 유지&ContractStatus만 변경,
		// 배서 정보가 없다면 배서 하나 추가됨, 배서 정보가 그대로 있다면 해당 배서의 납부일 변경
		Contract contract = contractDModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		contract.setContractStatus(ContractStatus.EndorsementRequesting);
		contractDModel.update(contract);
		Endorsement endorsement = endorsementDModel.getById(contractId);
		if (endorsement == null) {
			// 여기서는 endorsement 테이블만 추가됨
			// 이유는 contractId 해당 Contract는 엔티티가 있기 때문
			// dmodel에서 add할 때 contract의 dmodel까지 들어가긴 하지만
			// contract dmodel은 아무것도 안하고 그냥 종료되는 형태
			// id는 Contract랑 똑같아서 지정 x
			endorsement = new Endorsement(contract);
			endorsement.setPaymentDate(index);
			endorsementDModel.add(endorsement);
		} else {
			// 만일 기존 endorsement가 있는 상황에서 납입일 변경이라면?
			// 납부일 변경 및 테이블 수정
			endorsement.setPaymentDate(index);
			endorsementDModel.update(endorsement);
		}
	}

	public void applyInsuranceRevival(int contractId)
		throws NotExistContractException, NotExistTerminatedContract, NotExistException {
		/*
		 * contract status가 Terminating이라면 실행 ㄱㄴ - 이 status를 DB에서 받음
		 * contract status를 RevivalRequesting로 변경
		 * Revival 테이블에 없다면 Revival 하나 추가
		 */

		Contract contract = contractDModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Terminating && contract.getExpirationDate() != null) {
			contract.setContractStatus(ContractStatus.RevivalRequesting);
			contractDModel.update(contract);
			if (revivalEntityModel.getById(contractId) != null) return;
			revivalEntityModel.add(new Revival(contract));
		} else {
			throw new NotExistTerminatedContract();
		}
	}
	// revival, termination 안 넣은 이유? - 각각 dModel에서 추가 시에 중복 아이디가 있는지 확인하는 로직이 있음
	// ㄴ 이건 contract dmodel 쪽에도 있고, termination dmodel 쪽에도 있어서
	//    둘 다 정보가 있다? - 추가X, cont쪽만 있다? - rev, ter쪽만 추가,
	//    둘 다 없다? - 둘 다 추가 이런 식임
	public void applyInsuranceTermination(int contractId)
		throws NotExistContractException, NotExistMaintainedContract, NotExistException {
		Contract contract = contractDModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Maintaining) {
			contract.setContractStatus(ContractStatus.TerminationRequesting);
			contractDModel.update(contract);
			if (terminationEntityModel.getById(contractId) != null) return;
			terminationEntityModel.add(new Termination(contract));
		} else {
			throw new NotExistMaintainedContract();
		}
	}

	public void applyRecontract(int contractId) throws NotExistContractException,
		NotExistExpiredContract, NotExistException {
		/*
		 * contract status가 Maturing이라면 실행 ㄱㄴ - 이 status를 DB에서 받음
		 * contract status를 RecontractRequesting로 변경
		 * Recontract 테이블에 없다면 Recontract 하나 추가
		 */
		Contract contract = contractDModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		if (contract.getContractStatus() == ContractStatus.Maturing) {
			contract.setContractStatus(ContractStatus.RecontractRequesting);
			contractDModel.update(contract);
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
		if (contractDModel.getById(contractId) == null)
			throw new NotExistContractException();
		int depositId = depositDetailDModel.getAll().isEmpty() ?
				Integer.parseInt(DepositDetail.DEPOSIT_DETAIL_SERIAL+"1") :
				NextIdGetter.getNextId(depositDetailDModel.getMaxId(), DepositDetail.DEPOSIT_DETAIL_SERIAL);
		depositDetailDModel.add(new DepositDetail(depositId, depositorName,
				contractId, money, depositPath));
	}

	// 이 아래는 완료
	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerDModel.getById(id);
	}

	public List<Insurance> getAllInsurance() {
		return insuranceDModel.getAll();
	}

	public List<Disease> getAllDiseaseInsurance() {
		return diseaseDModel.getAll();
	}

	public List<Injury> getAllInjuryInsurance() {
		return injuryDModel.getAll();
	}

	public List<Automobile> getAllAutomobileInsurance() {
		return automobileEntityModel.getAll();
		//		return productList.getAllAutomobileInsurance();
	}

	public Insurance getInsuranceByProductId(int id) throws NotExistException {
		return insuranceDModel.getById(id);
	}

	public List<Loan> getAllLoan() {
		return loanDModel.getAll();
	}

	public List<Collateral> getAllCollateralLoan() {
		return collateralEntityModel.getAll();
		//		return productList.getAllCollateralLoan();
	}

	public List<FixedDeposit> getAllFixedDepositLoan() {
		return fixedDepositDModel.getAll();
	}

	public List<InsuranceContract> getAllInsuranceContractLoan(ProductList productList) {
		return insuranceContractDModel.getAll();
	}

	public Loan getLoanByProductId(int id) throws NotExistException {
		return loanDModel.getById(id);
	}

	public List<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		return contractDModel.getAll().stream().filter(
				e -> e.getContractStatus() != ContractStatus.ContractRequesting &&
						e.getExpirationDate() != null).toList();
		//		return contractList.getAllApprovedByCustomer(id);
	}

	public List<Contract> getAllContractByCustomerId(int id) throws NotExistContractException, NotExistException {
		// 계약들의 고객 번호를 비교 - 고객 번호가 같은 계약들만 추출 - 한 고객이 신청한 계약만 나옴
		return contractDModel.getAll().stream().filter(e -> e.getCustomerID() == id).toList();
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
		for (Injury injury : injuryDModel.getAll())
            result.addAll(getAllContractByProductId(injury.getId()));
		return result;
		//		return contractList.getAllInjuryInsuranceContract();
	}

	public List<Contract> getAllDiseaseInsuranceContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		for (Disease disease : diseaseDModel.getAll())
            result.addAll(getAllContractByProductId(disease.getId()));
		return result;
		//		return contractList.getAllDiseaseInsuranceContract();
	}

	public List<Contract> getAllContractByProductId(int id) throws NotExistContractException, NotExistException {
		return contractDModel.getAll().stream().filter(e -> e.getProduct().getId() == id).toList();
	}

	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractDModel.getById(contractId);
	}

	public Contract getContractByOneAutomobileId(int id) throws NotExistContractException, NotExistException {
		List<Contract> contracts = getAllContractByCustomerId(id);
		for (Contract contract : contracts) {
			Product product = contract.getProduct();
			if (product instanceof Insurance) {
				Insurance insurance = (Insurance)product;
				if (insurance.getInsuranceType() == InsuranceType.Automobile)
					return contract;
			}
		}
		throw new NotExistContractException();
		//		return contractList.getContractByOneAutomobileId(id);
	}

	public List<Accident> getAllAccidentByCustomerId(int id) throws NotExistException {
		return accidentEntityModel.getAll().stream().filter(e -> e.getCustomerID() == id).toList();
		//		return accidentList.getAllByCustomer(id);
	}

	public Accident getAccidentById(int id) throws NotExistException {
		return accidentEntityModel.getById(id);
	}

	public List<Complaint> getAllComplaintsByCustomerId(int id) throws NotExistException {
		return complaintEntityModel.getAll().stream().filter(e -> e.getCustomerID() == id).toList();
		//		return complaintList.getAllByCustomerId(id);
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

		for (Customer customer : customerDModel.getAll())
			if (customer.getResidentRegistrationNumber().equals(residentRegistrationNumber))
				throw new DuplicateResidentRegistrationNumberException();
		Customer customer = new Customer(name, phoneNumber, job, age, gender,
				residentRegistrationNumber, address, property, bankName, bankAccount);

		// 이 생성자 쓰면 엔티티 7개는 요소가 아얘 없는 ArrayList가 생김 (널포인터 방지 ㄱㄴ)
		customer.setId(NextIdGetter.getNextId(customerDModel.getMaxId(), Customer.CUSTOMER_SERIAL_NUMBER));
		customerDModel.add(customer);
		// add 시점에 customer의 엔티티 7개는 요소가 아얘 없으니 dmodel add 시 각 테이블에 정보가 추가되지 않음
		// 단, accidenthistory, surgeryhistory, diseasehistory의 경우,
		// 파라미터로 받아온 요소가 있다면 아래 코드로 각 테이블에 추가됨 (customerDModel과 별개임)
		if (tempAccidentHistoryList != null)
			for (AccidentHistory e : tempAccidentHistoryList) {
				e.setCustomerID(customer.getId());
				e.setId(NextIdGetter.getNextId(accidentDModel.getMaxId(),
						AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER));
				accidentHistoryDModel.add(e);
			}
		if (tempSurgeryHistoryList != null)
			for (SurgeryHistory e : tempSurgeryHistoryList) {
				e.setCustomerID(customer.getId());
				e.setId(NextIdGetter.getNextId(surgeryHistoryEntityModel.getMaxId(),
						SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER));
				surgeryHistoryEntityModel.add(e);
			}
		if (tempDiseaseHistoryList != null)
			for (DiseaseHistory e : tempDiseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				e.setId(NextIdGetter.getNextId(diseaseHistoryDModel.getMaxId(),
						DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER));
				diseaseHistoryDModel.add(e);
			}
	}

	public void askInsuranceCounsel(AskInsuranceCounselDTO askInsuranceCounselDTO) throws NotExistException {

		int insuranceId = askInsuranceCounselDTO.getInsuranceId();
		if (insuranceDModel.getById(insuranceId) == null) throw new NotExistException("해당 보험이 없습니다.");
		int customerId = askInsuranceCounselDTO.getCustomerId();
		Customer customer = customerDModel.getById(customerId);
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
		counsel.setId(NextIdGetter.getNextId(counselDModel.getMaxId(), Counsel.COUNSEL_SERIAL_NUMBER));
		counselDModel.add(counsel);
//		customer.askInsuranceCounsel(insurance, name, phoneNumber, counselDate, job, age, gender, counselList);
	}

	public void buyInsurance(BuyInsuranceDTO buyInsuranceDTO) throws NotExistException {
		int insuranceId = buyInsuranceDTO.getInsuranceId();
		int customerId = buyInsuranceDTO.getCustomerId();
		Integer employeeId = buyInsuranceDTO.getEmployeeId();
		// employeeId는 null 허용 - 이 시점에서 직원 아이디를 받나유...??

		if (customerDModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Insurance insurance = insuranceDModel.getById(insuranceId);
		if (insurance == null) throw new NotExistException("지정된 보험이 없습니다.");
		Contract contract = new Contract(customerId, insurance);
		contract.setId(NextIdGetter.getNextId(contractDModel.getMaxId(), Contract.CONTRACT_SERIAL_NUMBER));
		contract.setEmployeeID(employeeId);
		contractDModel.add(contract);
		// id, customerId, insurnace 이외에는 아무 정보도 지정X
//		return customer.buyInsurance(insurance, contractList);
	}

	public void complain(ComplainDTO complainDTO) throws NotExistException {
		ComplaintType complainType = ComplaintType.values()[complainDTO.getComplainType()];
		String title = complainDTO.getTitle();
		String content = complainDTO.getContent();
		int customerId = complainDTO.getCustomerId();

		if (customerDModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Complaint complaint = new Complaint(complainType, content, customerId, title);
		complaint.setId(NextIdGetter.getNextId(complaintDModel.getMaxId(), Complaint.COMPLAINT_SERIAL));
		complaintDModel.add(complaint);
//		customer.complain(complaintList, customerList, complainType, title, content);
	}

	public void loan(LoanDTO loanDTO) throws AlreadyRequestingException, NotExistException {
		int loanId = loanDTO.getLoanId();
		int customerId = loanDTO.getCustomerId();
		Integer employeeId = loanDTO.getEmployeeId();

		if (customerDModel.getById(customerId) == null) throw new NotExistException("해당 고객이 없습니다.");
		Loan loan = loanDModel.getById(loanId);
		if (loan == null) throw new NotExistException("해당 대출이 없습니다.");
		List<Contract> myContractList = contractDModel.getAll().stream().filter(e -> e.getCustomerID() == customerId).toList();
		if (myContractList.isEmpty()) myContractList = new ArrayList<>();
		for (Contract contract : myContractList)
			if (contract.getProduct().getId() == loanId)
				throw new AlreadyRequestingException();
		Contract contract = new Contract(customerId, loan);
		contract.setId(NextIdGetter.getNextId(contractDModel.getMaxId(), Contract.CONTRACT_SERIAL_NUMBER));
		contract.setEmployeeID(employeeId);
		contractDModel.add(contract);
//		return customer.loan(loan, contractList);
	}

	public void receiveInsurance(ReceiveInsuranceDTO receiveInsuranceDTO) throws NotExistContractException, NotExistException {
		int contractId = receiveInsuranceDTO.getContractId();
		int customerId = receiveInsuranceDTO.getCustomerId();
		BufferedImage medicalCertificateImage = receiveInsuranceDTO.getMedicalCertificateImage();
		BufferedImage receiptImage = receiveInsuranceDTO.getReceiptImage();
		BufferedImage residentRegistrationCardImage = receiveInsuranceDTO.getResidentRegistrationCardImage();
		// 일단 이미지도 DTO로 받는 형태 - 이건 다시 얘기해봅시다

		if (contractDModel.getById(contractId) == null) throw new NotExistContractException();
		Customer customer = customerDModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		if (contractDModel.getById(contractId).getCustomerID() != customerId)
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

		Customer customer = customerDModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다.");
		Accident accident = new Accident();
		accident.report(customerId, customer.getName(), customer.getPhoneNumber(), accidentDate, location, serviceType);
		accident.setId(NextIdGetter.getNextId(accidentDModel.getMaxId(), Accident.ACCIDENT_SERIAL));
		accidentDModel.add(accident);
//		customer.reportAccident(customerName, customerPhoneNumber, accidentDate, location, serviceType,
//				accidentList);
	}
}
