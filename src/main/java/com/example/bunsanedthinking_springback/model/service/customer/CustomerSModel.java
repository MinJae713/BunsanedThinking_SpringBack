package com.example.bunsanedthinking_springback.model.service.customer;

import com.example.bunsanedthinking_springback.dto.customer.DepositDTO;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.model.domain.accident.AccidentDModel;
import com.example.bunsanedthinking_springback.model.domain.automobile.AutomobileDModel;
import com.example.bunsanedthinking_springback.model.domain.collateral.CollateralDModel;
import com.example.bunsanedthinking_springback.model.domain.complaint.ComplaintDModel;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.domain.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.domain.depositDetail.DepositDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.disease.DiseaseDModel;
import com.example.bunsanedthinking_springback.model.domain.endorsement.EndorsementDModel;
import com.example.bunsanedthinking_springback.model.domain.fixedDeposit.FixedDepositDModel;
import com.example.bunsanedthinking_springback.model.domain.injury.InjuryDModel;
import com.example.bunsanedthinking_springback.model.domain.insurance.InsuranceDModel;
import com.example.bunsanedthinking_springback.model.domain.insuranceContract.InsuranceContractDModel;
import com.example.bunsanedthinking_springback.model.domain.loan.LoanDModel;
import com.example.bunsanedthinking_springback.model.domain.recontract.RecontractDModel;
import com.example.bunsanedthinking_springback.model.domain.revival.RevivalDModel;
import com.example.bunsanedthinking_springback.model.domain.termination.TerminationDModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerSModel {

	@Autowired
	private InsuranceDModel insuranceDModel;
	@Autowired
	private DiseaseDModel diseaseDModel;
	@Autowired
	private InjuryDModel injuryDModel;
	@Autowired
	private AutomobileDModel automobileDModel;
	@Autowired
	private LoanDModel loanDModel;
	@Autowired
	private CollateralDModel collateralDModel;
	@Autowired
	private FixedDepositDModel fixedDepositDModel;
	@Autowired
	private InsuranceContractDModel insuranceContractDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private EndorsementDModel endorsementDModel;
	@Autowired
	private RevivalDModel revivalDModel;
	@Autowired
	private TerminationDModel terminationDModel;
	@Autowired
	private RecontractDModel recontractDModel;
	@Autowired
	private AccidentDModel accidentDModel;
	@Autowired
	private ComplaintDModel complaintDModel;
	@Autowired
	private DepositDetailDModel depositDetailDModel;

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
			if (revivalDModel.getById(contractId) != null) return;
			revivalDModel.add(new Revival(contract));
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
			if (terminationDModel.getById(contractId) != null) return;
			terminationDModel.add(new Termination(contract));
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
			if (recontractDModel.getById(contractId) != null) return;
			recontractDModel.add(new Recontract(contract));
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
				getNextId(depositDetailDModel.getMaxId(), DepositDetail.DEPOSIT_DETAIL_SERIAL);
		depositDetailDModel.add(new DepositDetail(depositId, depositorName,
				contractId, money, depositPath));
	}

	private int getNextId(int maxId, int serial) {
		String maxIdStr = maxId+"";
		int serialLength = (serial+"").length();
		int nextId = Integer.parseInt(maxIdStr.substring(serialLength));
		nextId++;
		return Integer.parseInt(serial+""+nextId);
	}

	// 이 아래는 완료
	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerDModel.getById(id);
		//		return customerList.get(id);
	}

	public List<Insurance> getAllInsurance() {
		return insuranceDModel.getAll();
		//		return productList.getAllInsurance();
	}

	public List<Disease> getAllDiseaseInsurance() {
		return diseaseDModel.getAll();
		//		return productList.getAllDiseaseInsurance();
	}

	public List<Injury> getAllInjuryInsurance() {
		return injuryDModel.getAll();
		//		return productList.getAllInjuryInsurance();
	}

	public List<Automobile> getAllAutomobileInsurance() {
		return automobileDModel.getAll();
		//		return productList.getAllAutomobileInsurance();
	}

	public Insurance getInsuranceByProductId(int id) throws NotExistException {
		return insuranceDModel.getById(id);
	}

	public List<Loan> getAllLoan() {
		return loanDModel.getAll();
		//		return productList.getAllLoan();
	}

	public List<Collateral> getAllCollateralLoan() {
		return collateralDModel.getAll();
		//		return productList.getAllCollateralLoan();
	}

	public List<FixedDeposit> getAllFixedDepositLoan() {
		return fixedDepositDModel.getAll();
		//		return productList.getAllFixedDepositLoan();
	}

	public List<InsuranceContract> getAllInsuranceContractLoan(ProductList productList) {
		return insuranceContractDModel.getAll();
		//		return productList.getAllInsuranceContractLoan();
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
		//		return contractList.getAllByCustomer(id);
	}

	public List<Contract> getAllAutomobileInsuranceContract() throws NotExistContractException, NotExistException {
		List<Contract> result = new ArrayList<Contract>();
		for (Automobile automobile : automobileDModel.getAll())
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
//		ArrayList<Contract> result = new ArrayList<Contract>();
//		ContractVO contractVO = contractMapper.getAllByProductId_Customer(id).orElse(null);
//		result.add(getContractById(contractVO.getId()));
		return contractDModel.getAll().stream().filter(e -> e.getProduct().getId() == id).toList();
	}

	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractDModel.getById(contractId);
		//		return contractList.get(contractId);
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
		return accidentDModel.getAll().stream().filter(e -> e.getCustomerID() == id).toList();
		//		return accidentList.getAllByCustomer(id);
	}

	public Accident getAccidentById(int id) throws NotExistException {
		return accidentDModel.getById(id);
	}

	public List<Complaint> getAllComplaintsByCustomerId(int id) throws NotExistException {
		return complaintDModel.getAll().stream().filter(e -> e.getCustomerID() == id).toList();
		//		return complaintList.getAllByCustomerId(id);
	}

	public Complaint getComplaintById(int id) throws NotExistException {
		return complaintDModel.getById(id);
	}

	// 새로 추가됨 - controller는 아직 추가x
	public void signUp(String name, String phoneNumber, String job, int age, Gender gender,
		String residentRegistrationNumber, String address, long property,
		ArrayList<AccidentHistory> tempAccidentHistoryList, ArrayList<SurgeryHistory> tempSurgeryHistoryList,
		ArrayList<DiseaseHistory> tempDiseaseHistoryList, String bankName, String bankAccount,
		CustomerList customerList, AccidentHistoryList accidentHistoryList, SurgeryHistoryList surgeryHistoryList,
		DiseaseHistoryList diseaseHistoryList, Customer customer) throws
		DuplicateResidentRegistrationNumberException {
		customer.signUp(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
			property, tempAccidentHistoryList, tempSurgeryHistoryList, tempDiseaseHistoryList,
			bankName, bankAccount, customerList, accidentHistoryList, surgeryHistoryList,
			diseaseHistoryList);
	}

	public void complain(ComplaintList complaintList, CustomerList customerList, ComplaintType complainType,
		String title, String content, Customer customer) {
		customer.complain(complaintList, customerList, complainType, title, content);
	}

	public void reportAccident(String customerName, String customerPhoneNumber, Date accidentDate, String location,
		ServiceType serviceType, AccidentList accidentList, Customer customer) {
		customer.reportAccident(customerName, customerPhoneNumber, accidentDate, location, serviceType,
			accidentList);
	}

	public boolean buyInsurance(Insurance insurance, ContractList contractList, Customer customer) {
		return customer.buyInsurance(insurance, contractList);
	}

	public void askInsuranceCounsel(Insurance insurance, String name, String phoneNumber, Date counselDate, String job,
		int age, Gender gender, CounselList counselList, Customer customer) {
		customer.askInsuranceCounsel(insurance, name, phoneNumber, counselDate, job, age, gender, counselList);
	}

	public boolean loan(Loan loan, ContractList contractList, Customer customer) throws AlreadyRequestingException {
		return customer.loan(loan, contractList);
	}

	public void receiveInsurance(Contract contract, BufferedImage medicalCertificateImage, BufferedImage receiptImage,
		BufferedImage residentRegistrationCardImage, InsuranceMoneyList insuranceMoneyList, Customer customer) throws
		IOException {
		customer.receiveInsurance(contract, medicalCertificateImage, receiptImage, residentRegistrationCardImage,
			insuranceMoneyList);
	}
}
