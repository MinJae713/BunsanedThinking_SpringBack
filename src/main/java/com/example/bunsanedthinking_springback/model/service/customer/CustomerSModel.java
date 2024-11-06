package com.example.bunsanedthinking_springback.model.service.customer;

import com.example.bunsanedthinking_springback.dto.customer.DepositDTO;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.exception.*;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerSModel {
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
	private EndorsmentMapper endorsmentMapper;
	@Autowired
	private RevivalMapper revivalMapper;
	@Autowired
	private TerminationMapper terminationMapper;
	@Autowired
	private RecontractMapper recontractMapper;
	public void applyEndorsement(int index, int contractId) throws NotExistContractException, NotExistException {
		// 배서(Endorsement) 납부일만 변경됨 - 기존 내용은 유지&ContractStatus만 변경, 배서 하나 추가됨
		// 타입이 contractVO, endorsementVO인거 주의
		/**
		 * contract status 상관 없이 해당 contract가 있다면 실행 ㄱㄴ
		 * paymentDate를 입력받은 날짜로 변경
		 * endorsement 테이블에 없다면 endorsement 하나 추가
		 */
		if (contractMapper.getById_Customer(contractId).orElse(null) == null) throw new NotExistContractException();
		LocalDate localDate = contractMapper.getById_Customer(contractId).orElse(null).getPayment_date();
		localDate = localDate.plusDays(index-localDate.getDayOfMonth());
		contractMapper.updatePaymentDate_Customer(localDate, contractId);
		contractMapper.updateStatus_Customer(ContractStatus.EndorsementRequesting.ordinal(), contractId);
		EndorsementVO endorsementVO = endorsmentMapper.getById_Customer(contractId).orElse(null);
		if (endorsementVO == null) endorsmentMapper.addById_Customer(contractId);
		// contract엔 있는데 endorsement가 없는 경우라면 endorsement를 하나 추가하기로 했수다 - 잘됨
//		contract.setContractStatus(ContractStatus.EndorsementRequesting);
//		contractList.update(contract);
//		Endorsement endorsement = new Endorsement(contract);
//		endorsement.setPaymentDate(index);
//		endorsementList.add(endorsement);
	}

	public void applyInsuranceRevival(int contractId, Date expirationDate)
            throws NotExistContractException, NotExistTerminatedContract, NotExistException {
		// 일단 status 받는건 정수로 받아서 비교하기로 - 이건 다시 상의해봅시다(뷰에서 뭐로 전달받을지 문제임)
		// 안받고 DB 정보와 비교하는 방법이 맞는거 같기도?
		/**
		 * contract status가 Terminating이라면 실행 ㄱㄴ - 이 status를 DB에서 받음
		 * contract status를 RevivalRequesting로 변경
		 * Revival 테이블에 없다면 Revival 하나 추가
		 */
		ContractVO contractVO = contractMapper.getById_Customer(contractId).orElse(null);
		if (contractVO == null) throw new NotExistContractException();
		if (contractVO.getContract_status() == ContractStatus.Terminating.ordinal() && expirationDate != null) {
			contractMapper.updateStatus_Customer(ContractStatus.RevivalRequesting.ordinal(), contractId);
			RevivalVO revivalVO = revivalMapper.getById_Customer(contractId).orElse(null);
			if (revivalVO == null) revivalMapper.addById_Customer(contractId);
		} else {
			throw new NotExistTerminatedContract();
		}

//		if (contract.getContractStatus() == ContractStatus.Terminating && contract.getExpirationDate() != null) {
//			contract.setContractStatus(ContractStatus.RevivalRequesting);
//			contractList.update(contract);
//			Revival revival = new Revival(contract);
//			revivalList.add(revival);
//		} else {
//			throw new NotExistTerminatedContract();
//		}
	}

	public void applyInsuranceTermination(int contractId)
			throws NotExistContractException, NotExistMaintainedContract, NotExistException {
		/**
		 * contract status가 Maintaining이라면 실행 ㄱㄴ - 이 status를 DB에서 받음
		 * contract status를 TerminationRequesting로 변경
		 * Termination 테이블에 없다면 Termination 하나 추가
		 */
		ContractVO contractVO = contractMapper.getById_Customer(contractId).orElse(null);
		if (contractVO == null) throw new NotExistContractException();
		if (contractVO.getContract_status() == ContractStatus.Maintaining.ordinal()) {
			contractMapper.updateStatus_Customer(ContractStatus.TerminationRequesting.ordinal(), contractId);
			TerminationVO terminationVO = terminationMapper.getById_Customer(contractId).orElse(null);
			if (terminationVO == null) terminationMapper.addById_Customer(contractId);
		} else {
			throw new NotExistMaintainedContract();
		}

//		if (contract.getContractStatus() == ContractStatus.Maintaining) {
//			contract.setContractStatus(ContractStatus.TerminationRequesting);
//			contractList.update(contract);
//			Termination termination = new Termination(contract);
//			terminationList.add(termination);
//		} else {
//			throw new NotExistMaintainedContract();
//		}
	}

	public void applyRecontract(int contractId) throws NotExistContractException, NotExistExpiredContract, NotExistException {
		/**
		 * contract status가 Maturing이라면 실행 ㄱㄴ - 이 status를 DB에서 받음
		 * contract status를 RecontractRequesting로 변경
		 * Recontract 테이블에 없다면 Recontract 하나 추가
		 */
		ContractVO contractVO = contractMapper.getById_Customer(contractId).orElse(null);
		if (contractVO == null) throw new NotExistContractException();
		if (contractVO.getContract_status() == ContractStatus.Maturing.ordinal()) {
			contractMapper.updateStatus_Customer(ContractStatus.RecontractRequesting.ordinal(), contractId);
			RecontractVO recontractVO = recontractMapper.getById_Customer(contractId).orElse(null);
			if (recontractVO == null) recontractMapper.addById_Customer(contractId);
		} else {
			throw new NotExistExpiredContract();
		}

//		if (contract.getContractStatus() == ContractStatus.Maturing) {
//			contract.setContractStatus(ContractStatus.RecontractRequesting);
//			contractList.update(contract);
//			Recontract recontract = new Recontract(contract);
//			recontractList.add(recontract);
//		} else {
//			throw new NotExistExpiredContract();
//		}
	}
	public void payInsurancefee(DepositDTO depositDTO)
			throws NotExistContractException, NotExistException {
		String depositorName = depositDTO.getDepositorName();
		int contractId = depositDTO.getContractId();
		int money = depositDTO.getMoney();
		int depositPath = depositDTO.getDepositPath();
		if (contractMapper.getById_Customer(contractId).orElse(null) == null)
			throw new NotExistContractException();
		int depositId = depositDetailMapper.getCount_Customer() == 0 ?
				8101 : depositDetailMapper.getLastId_Customer()+1;
		depositDetailMapper.add_Customer(new DepositDetailVO(
				depositId, depositorName, LocalDate.now(),
				money, depositPath, contractId
		));
//		DepositDetail depositDetail = new DepositDetail(customer.getName(), contract.getId(), money, path);
//		depositDetailList.add(depositDetail);
//		contract.getDepositDetailList().add(depositDetail);
	}


	// 이 아래는 완료
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
//		return customerList.get(id);
	}
	public ArrayList<Insurance> getAllInsurance() {
		ArrayList<Insurance> insuranceList = new ArrayList<Insurance>();
		List<InsuranceVO> insuranceVOS = insuranceMapper.getAll_Customer();
		for (InsuranceVO insuranceVO : insuranceVOS) {
			int product_id = insuranceVO.getProduct_id();

			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			// DiseaseVO
			DiseaseVO diseaseVO = diseaseMapper.getDiseaseById_Customer(product_id).orElse(null);
			if (diseaseVO != null) {
				String disease_name = diseaseVO.getDisease_name();
				int disease_limit = diseaseVO.getDisease_limit();
				int surgeries_limit = diseaseVO.getSurgeries_limit();
				insuranceList.add(new Disease(productVO, insuranceVO,
						disease_name, disease_limit, surgeries_limit));
				continue;
			}

			// AutoMobileVO
			AutoMobileVO autoMobileVO = automobileMapper.getAutoMobileById_Customer(product_id).orElse(null);
			if (autoMobileVO != null) {
				List<ServiceVO> serviceVOS = serviceMapper.getAllByProductId_Customer(product_id);
				ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
				serviceVOS.stream().map(s -> s.getService()).forEach(s -> serviceTypeList.add(ServiceType.values()[s]));
				VehicleType verhicle_type = VehicleType.values()[autoMobileVO.getVehicle_type()];
				int accident_limit = autoMobileVO.getAccident_limit();
				insuranceList.add(new Automobile(productVO, insuranceVO,
						accident_limit, verhicle_type, serviceTypeList));
				continue;
			}

			// InjuryVO
			InjuryVO injuryVO = injuryMapper.getInjuryById_Customer(product_id).orElse(null);
			if (injuryVO != null) {
				InjuryType injury_type = InjuryType.values()[injuryVO.getInjury_type()];
				int surgeries_limit = injuryVO.getSurgeries_limit();
				insuranceList.add(new Injury(productVO, insuranceVO,
						injury_type, surgeries_limit));
			}
		}
		return insuranceList;
//		return productList.getAllInsurance();
	}
	public ArrayList<Insurance> getAllDiseaseInsurance() {
		ArrayList<Insurance> diseases = new ArrayList<Insurance>();
		List<DiseaseVO> diseaseVOS = diseaseMapper.getAll_Customer();
		for (DiseaseVO diseaseVO : diseaseVOS) {
			// DiseaseVO
			int product_id = diseaseVO.getProduct_id();
			int disease_limit = diseaseVO.getDisease_limit();
			String disease_name = diseaseVO.getDisease_name();
			int surgeries_limit = diseaseVO.getSurgeries_limit();
			// InsuranceVO
			InsuranceVO insuranceVO = insuranceMapper.getInsuranceById_Customer(product_id).orElse(null);
			if (insuranceVO == null) throw new RuntimeException("보험을 못찾았습니다.");
			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");
			diseases.add(new Disease(productVO, insuranceVO,
					disease_name, disease_limit, surgeries_limit));
		}
		return diseases;
//		return productList.getAllDiseaseInsurance();
	}
	public ArrayList<Insurance> getAllInjuryInsurance() {
		ArrayList<Insurance> injuries = new ArrayList<Insurance>();
		List<InjuryVO> injuryVOS = injuryMapper.getAll_Customer();
		for (InjuryVO injuryVO : injuryVOS) {
			// InjuryVO
			int product_id = injuryVO.getProduct_id();
			InjuryType injury_type = InjuryType.values()[injuryVO.getInjury_type()];
			int surgeries_limit = injuryVO.getSurgeries_limit();
			// InsuranceVO
			InsuranceVO insuranceVO = insuranceMapper.getInsuranceById_Customer(product_id).orElse(null);
			if (insuranceVO == null) throw new RuntimeException("보험을 못찾았습니다.");
			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");
			injuries.add(new Injury(productVO, insuranceVO, injury_type, surgeries_limit));
		}
		return injuries;
//		return productList.getAllInjuryInsurance();
	}
	public ArrayList<Insurance> getAllAutomobileInsurance() {
		ArrayList<Insurance> automobiles = new ArrayList<Insurance>();
		List<AutoMobileVO> autoMobileVOS = automobileMapper.getAll_Customer();
		for (AutoMobileVO autoMobileVO : autoMobileVOS) {
			// AutomobileVO
			int product_id = autoMobileVO.getProduct_id();
			List<ServiceVO> serviceVOS = serviceMapper.getAllByProductId_Customer(product_id);
			ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
			serviceVOS.stream().map(s -> s.getService()).forEach(s -> serviceTypeList.add(ServiceType.values()[s]));
			VehicleType verhicle_type = VehicleType.values()[autoMobileVO.getVehicle_type()];
			int accident_limit = autoMobileVO.getAccident_limit();
			// InsuranceVO
			InsuranceVO insuranceVO = insuranceMapper.getInsuranceById_Customer(product_id).orElse(null);
			if (insuranceVO == null) throw new RuntimeException("보험을 못찾았습니다.");
			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");
			automobiles.add(new Automobile(productVO, insuranceVO,
					accident_limit, verhicle_type, serviceTypeList));
		}
		return automobiles;
//		return productList.getAllAutomobileInsurance();
	}
	public Insurance getInsuranceByProductId(int id) throws NotExistException {
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

//		ArrayList<Insurance> insuranceList = productList.getAllInsurance();
//		for (Insurance insurance : insuranceList) {
//			if (insurance.getId() == id) {
//				return insurance;
//			}
//		}
	}
	public ArrayList<Loan> getAllLoan() {
		ArrayList<Loan> loanList = new ArrayList<Loan>();
		List<LoanVO> loanVOS = loanMapper.getAll_Customer();
		for (LoanVO loanVO : loanVOS) {
			int product_id = loanVO.getProduct_id();

			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			// CollateralVO
			CollateralVO collateralVO = collateralMapper.getCollateralById_Customer(product_id).orElse(null);
			if (collateralVO != null) {
				CollateralType collateral_type = CollateralType.values()[collateralVO.getCollateral_type()];
				int minimum_value = collateralVO.getMinimum_value();
				loanList.add(new Collateral(productVO, loanVO, collateral_type, minimum_value));
				continue;
			}

			// FixedDepositVO
			FixedDepositVO fixedDepositVO = fixedDepositMapper.getFixedDepositById_Customer(product_id).orElse(null);
			if (fixedDepositVO != null) {
				int minimum_amount = fixedDepositVO.getMinimum_amount();
				loanList.add(new FixedDeposit(productVO, loanVO, minimum_amount));
				continue;
			}

			// InsuranceContractVO
			InsuranceContractVO insuranceContractVO = insuranceContractMapper.getInsuranceContractById_Customer(product_id).orElse(null);
			if (insuranceContractVO != null) loanList.add(new InsuranceContract(productVO, loanVO, product_id));
		}
		return loanList;
//		return productList.getAllLoan();
	}
	public ArrayList<Loan> getAllCollateralLoan() {
		ArrayList<Loan> collateralList = new ArrayList<Loan>();
		List<CollateralVO> collateralVOS = collateralMapper.getAll_Customer();
		for (CollateralVO collateralVO : collateralVOS) {
			// CollateralVO
			int product_id = collateralVO.getProduct_id();
			CollateralType collateral_type = CollateralType.values()[collateralVO.getCollateral_type()];
			int minimum_value = collateralVO.getMinimum_value();

			// loanVO
			LoanVO loanVO = loanMapper.getLoanById_Customer(product_id).orElse(null);
			if (loanVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			collateralList.add(new Collateral(productVO, loanVO, collateral_type, minimum_value));
		}
		return collateralList;
//		return productList.getAllCollateralLoan();
	}
	public ArrayList<Loan> getAllFixedDepositLoan() {
		ArrayList<Loan> fixedDepositList = new ArrayList<Loan>();
		List<FixedDepositVO> fixedDepositVOS = fixedDepositMapper.getAll_Customer();
		for (FixedDepositVO fixedDepositVO : fixedDepositVOS) {
			// FixedDepositVO
			int product_id = fixedDepositVO.getProduct_id();
			int minimum_amount = fixedDepositVO.getMinimum_amount();

			// loanVO
			LoanVO loanVO = loanMapper.getLoanById_Customer(product_id).orElse(null);
			if (loanVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			fixedDepositList.add(new FixedDeposit(productVO, loanVO, minimum_amount));
		}
		return fixedDepositList;
//		return productList.getAllFixedDepositLoan();
	}
	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList) {
		ArrayList<Loan> insuranceContractList = new ArrayList<Loan>();
		List<InsuranceContractVO> insuranceContractVOS = insuranceContractMapper.getAll_Customer();
		for (InsuranceContractVO insuranceContractVO : insuranceContractVOS) {
			// InsuranceContractVO
			int product_id = insuranceContractVO.getProduct_id();

			// loanVO
			LoanVO loanVO = loanMapper.getLoanById_Customer(product_id).orElse(null);
			if (loanVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			// ProductVO
			ProductVO productVO = productMapper.getProductById_Customer(product_id).orElse(null);
			if (productVO == null) throw new RuntimeException("상품을 못찾았습니다.");

			insuranceContractList.add(new InsuranceContract(productVO, loanVO, product_id));
		}
		return insuranceContractList;
//		return productList.getAllInsuranceContractLoan();
	}
	public Loan getLoanByProductId(int id) throws NotExistException {
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
//		ArrayList<Loan> loanList = productList.getAllLoan();
//		for (Loan loan : loanList) {
//			if (loan.getId() == id) {
//				return loan;
//			}
//		}
		throw new NotExistException();
	}
	public ArrayList<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		// if (contract.getContractStatus() != ContractStatus.ContractRequesting && contract.getExpirationDate() != null)
		ArrayList<Contract> result = new ArrayList<Contract>();
		List<ContractVO> contractVOS = contractMapper.getAll_Customer();
		for (ContractVO contractVO : contractVOS){
			Contract contract = getContractById(contractVO.getId());
			if (contract.getContractStatus() != ContractStatus.ContractRequesting &&
					contract.getExpirationDate() != null)
				result.add(contract);
		}
		return result;
//		return contractList.getAllApprovedByCustomer(id);
	}
	public ArrayList<Contract> getAllContractByCustomerId(int id) throws NotExistContractException, NotExistException {
		// 계약들의 고객 번호를 비교 - 고객 번호가 같은 계약들만 추출 - 한 고객이 신청한 계약만 나옴
		ArrayList<Contract> result = new ArrayList<Contract>();
		List<ContractVO> contractVOS = contractMapper.getAllByCustomerId_Customer(id);
		for (ContractVO contractVO : contractVOS) result.add(getContractById(contractVO.getId()));
		return result;
//		return contractList.getAllByCustomer(id);
	}
	public ArrayList<Contract> getAllAutomobileInsuranceContract() throws NotExistContractException, NotExistException {
		ArrayList<Contract> result = new ArrayList<Contract>();
		List<AutoMobileVO> autoMobileVOS = automobileMapper.getAll_Customer();
		for (AutoMobileVO autoMobileVO : autoMobileVOS) {
			List<Contract> contracts = getAllContractByProductId(autoMobileVO.getProduct_id());
			contracts.stream().forEach(contract -> result.add(contract));
		}
		return result;
//		return contractList.getAllAutomobileInsuranceContract();
	}
	public ArrayList<Contract> getAllInjuryInsuranceContract() throws NotExistContractException, NotExistException {
		ArrayList<Contract> result = new ArrayList<Contract>();
		List<InjuryVO> injuryVOS = injuryMapper.getAll_Customer();
		for (InjuryVO injuryVO : injuryVOS) {
			List<Contract> contracts = getAllContractByProductId(injuryVO.getProduct_id());
			contracts.stream().forEach(contract -> result.add(contract));
		}
		return result;
//		return contractList.getAllInjuryInsuranceContract();
	}
	public ArrayList<Contract> getAllDiseaseInsuranceContract() throws NotExistContractException, NotExistException {
		ArrayList<Contract> result = new ArrayList<Contract>();
		List<DiseaseVO> diseaseVOS = diseaseMapper.getAll_Customer();
		for (DiseaseVO diseaseVO : diseaseVOS) {
			List<Contract> contracts = getAllContractByProductId(diseaseVO.getProduct_id());
			contracts.stream().forEach(contract -> result.add(contract));
		}
		return result;
//		return contractList.getAllDiseaseInsuranceContract();
	}
	public ArrayList<Contract> getAllContractByProductId(int id) throws NotExistContractException, NotExistException {
		ArrayList<Contract> result = new ArrayList<Contract>();
		ContractVO contractVO = contractMapper.getAllByProductId_Customer(id).orElse(null);
		result.add(getContractById(contractVO.getId()));
		return result;
	} // 2. 이거로 하나 productId 찾아서 여거 contract id 갖고 여러 contract 찾을 수 있음
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
	} // 1. 이거로 contract 하나 받아낼 수 있으면
	private Product getProductById(int product_id) throws NotExistException {
		Product product = getInsuranceByProductId(product_id);
		if (product != null) return product;
		product = getLoanByProductId(product_id);
		if (product != null) return product;
		throw new NotExistException();
	}
	public Contract getContractByOneAutomobileId(int id) throws NotExistContractException, NotExistException {
		List<Contract> contracts = getAllContractByCustomerId(id);
		for (Contract contract : contracts){
			Product product = contract.getProduct();
			if (product instanceof Insurance){
				Insurance insurance = (Insurance) product;
				if (insurance.getInsuranceType() == InsuranceType.Automobile)
					return contract;
			}
		}
		throw new NotExistContractException();
//		return contractList.getContractByOneAutomobileId(id);
	}
	// 위는 Contract 쭈물떡, 아래는 좀 쉬울듯
	public ArrayList<Accident> getAllAccidentByCustomerId(int id) throws NotExistException {
		ArrayList<Accident> result = new ArrayList<Accident>();
		List<AccidentVO> accidentVOS = accidentMapper.getAllByCustomerId_Customer(id);
		for (AccidentVO accidentVO : accidentVOS)
			result.add(getAccidentById(accidentVO.getId()));
		return result;
//		return accidentList.getAllByCustomer(id);
	}
	public Accident getAccidentById(int id) throws NotExistException {
		AccidentVO accidentVO = accidentMapper.getAccidentById_Customer(id).orElse(null);
		if (accidentVO == null) throw new NotExistException();
		int customer_id = accidentVO.getCustomer_id();
		String customer_name = customerMapper.getNameById_Customer(customer_id).orElse(null);
		if (customer_name == null) throw new NotExistException();
		String customer_phoneNumber = customerMapper.getPNById_Customer(customer_id).orElse(null);
		if (customer_phoneNumber == null) throw  new NotExistException();
		return new Accident(accidentVO, customer_name, customer_phoneNumber);
	}
	public ArrayList<Complaint> getAllComplaintsByCustomerId(int id) throws NotExistException {
		ArrayList<Complaint> result = new ArrayList<Complaint>();
		List<ComplaintVO> complainVOS = complaintMapper.getComplaintByCustomerId_Customer(id);
		for (ComplaintVO complaintVO : complainVOS)
			result.add(getComplaintById(complaintVO.getId()));
		return result;
//		return complaintList.getAllByCustomerId(id);
	}
	public Complaint getComplaintById(int id) throws NotExistException {
		ComplaintVO complaintVO = complaintMapper.getComplaintById_Customer(id).orElse(null);
		if (complaintVO == null) throw new NotExistException();
		return new Complaint(complaintVO);
	}

	// 새로 추가됨 - controller는 아직 추가x
	public void signUp(String name, String phoneNumber, String job, int age, Gender gender,
					   String residentRegistrationNumber, String address, long property,
					   ArrayList<AccidentHistory> tempAccidentHistoryList, ArrayList<SurgeryHistory> tempSurgeryHistoryList,
					   ArrayList<DiseaseHistory> tempDiseaseHistoryList, String bankName, String bankAccount,
					   CustomerList customerList, AccidentHistoryList accidentHistoryList, SurgeryHistoryList surgeryHistoryList,
					   DiseaseHistoryList diseaseHistoryList, Customer customer) throws DuplicateResidentRegistrationNumberException {
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
								 BufferedImage residentRegistrationCardImage, InsuranceMoneyList insuranceMoneyList, Customer customer) throws IOException {
		customer.receiveInsurance(contract, medicalCertificateImage, receiptImage, residentRegistrationCardImage,
				insuranceMoneyList);
	}
}
