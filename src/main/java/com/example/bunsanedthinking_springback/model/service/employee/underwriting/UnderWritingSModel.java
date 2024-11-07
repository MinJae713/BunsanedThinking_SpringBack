package com.example.bunsanedthinking_springback.model.service.employee.underwriting;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static com.example.bunsanedthinking_springback.entity.loan.LoanType.fromInt;

@Service
public class UnderWritingSModel {

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

	public void applyCoperation() {

	}

	public void applyReinsurance() {

	}

	public boolean reviewAcquisition(int contractId, boolean result) throws AlreadyProcessedException {

		ContractVO contractVO = contractMapper.get_UnderWritingModel(contractId);

		if (ContractStatus.fromInt(contractVO.getContract_status()) != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}

		Insurance insurance = null;

		if (result) {
			ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
			for (DiseaseVO diseaseVO : diseaseVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if (productVO.getId() == contractVO.getProduct_id()) {
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
					break;
				}
			}

			if (insurance == null) {
				ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
				for (InjuryVO injuryVO : injuryVOs) {
					InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
					ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
					if (productVO.getId() == contractVO.getProduct_id()) {
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
						break;
					}
				}
			}

			if (insurance == null) {
				ArrayList<AutoMobileVO> automobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
				for (AutoMobileVO automobileVO : automobileVOs) {
					InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
					ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

					if (productVO.getId() == contractVO.getProduct_id()) {
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
						ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(contractVO.getProduct_id());
						for (ServiceVO serviceVO : serviceVOs) {
							serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
						}
						automobile.setServiceList(serviceTypes);

						insurance = automobile;
						break;
					}
				}
			}

			if (insurance != null) {
				contractVO.setExpiration_date(LocalDate.now().plusYears(insurance.getContractPeriod()));
			}
			contractVO.setDate(LocalDate.now());
			contractVO.setContract_status(ContractStatus.Maintaining.getValue());
		} else {
			contractVO.setContract_status(ContractStatus.Terminating.getValue());
		}

		contractMapper.update(contractVO);

		return result;
		// contract.review(result, contractList);
	}

	public ArrayList<Contract> getAllRequestingInsurance() throws
		NotExistException {

		ArrayList<Contract> contracts = new ArrayList<>();
		ArrayList<ContractVO> contractVOs = contractMapper.getAll_UnderWritingModel();
		for (ContractVO e : contractVOs) {
			if (ContractStatus.ContractRequesting.getValue() == e.getContract_status()) {

				Contract contract = new Contract();

				ContractVO contractVO = contractMapper.get_UnderWritingModel(e.getId());
				contract.setId(contractVO.getId());
				contract.setDate(Date.from(contractVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				contract.setExpirationDate(
					Date.from(contractVO.getExpiration_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				contract.setPaymentDate(contractVO.getPayment_date().getDayOfMonth());
				if (contractVO.getTermination_date() != null) {
					contract.setTerminationDate(
						Date.from(contractVO.getTermination_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
				contract.setContractStatus(ContractStatus.fromInt(contractVO.getContract_status()));
				if (contractVO.getCustomer_id() != null) {
					contract.setCustomerID(contractVO.getCustomer_id());
				}
				if (contractVO.getEmployee_id() != null) {
					contract.setEmployeeID(contractVO.getEmployee_id());
				}
				contract.setLastPaidDate(
					Date.from(contractVO.getLastpaid_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));

				ArrayList<CompensationDetail> compensationDetails = new ArrayList<>();
				ArrayList<CompensationDetailVO> CompensationDetailVOs = compensationDetailMapper.getAll_UnderwritingModel();
				for (CompensationDetailVO compensationDetailVO : CompensationDetailVOs) {
					if (compensationDetailVO.getContract_id() == contract.getId()) {
						CompensationDetail compensationDetail = new CompensationDetail();
						compensationDetail.setId(compensationDetailVO.getId());
						compensationDetail.setMoney(compensationDetailVO.getMoney());
						compensationDetail.setPaymentDate(Date.from(
							compensationDetailVO.getPayment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						compensationDetail.setContractID(compensationDetailVO.getContract_id());
						compensationDetails.add(compensationDetail);
					}
				}
				contract.setCompensationDetailList(compensationDetails);

				ArrayList<DepositDetail> depositDetails = new ArrayList<>();
				ArrayList<DepositDetailVO> depositDetailVOs = depositDetailMapper.getAll_UnderwritingModel();
				for (DepositDetailVO depositDetailVO : depositDetailVOs) {
					if (depositDetailVO.getContract_id() == contract.getId()) {
						DepositDetail depositDetail = new DepositDetail();
						depositDetail.setId(depositDetailVO.getId());
						depositDetail.setDepositorName(depositDetailVO.getDepositor_name());
						depositDetail.setDate(
							Date.from(depositDetailVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						depositDetail.setMoney(depositDetailVO.getMoney());
						depositDetail.setPath(DepositPath.fromInt(depositDetailVO.getPath()));
						depositDetail.setContractID(depositDetailVO.getContract_id());
						depositDetails.add(depositDetail);
					}
				}
				contract.setDepositDetailList(depositDetails);

				ArrayList<InsuranceMoney> insuranceMoneys = new ArrayList<>();
				ArrayList<InsuranceMoneyVO> insuranceMoneyVOs = insuranceMoneyMapper.getAll_UnderwritingModel();
				for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyVOs) {
					if (insuranceMoneyVO.getContract_id() == contract.getId()) {
						InsuranceMoney insuranceMoney = new InsuranceMoney();
						insuranceMoney.setId(insuranceMoneyVO.getId());
						insuranceMoney.setBankAccount(insuranceMoneyVO.getBank_account());

						// insuranceMoney.setResidentRegistrationCard(
						// 	ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())));
						// insuranceMoney.setReceipt(ImageIO.read(new File(insuranceMoneyVO.getReceipt())));
						// insuranceMoney.setMedicalCertificate(
						// 	ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())));

						insuranceMoney.setContractID(insuranceMoneyVO.getContract_id());
						insuranceMoney.setBankName(insuranceMoneyVO.getBank_name());
						insuranceMoney.setProcessStatus(
							InsuranceMoneyStatus.fromInt(insuranceMoneyVO.getProcess_status()));
						insuranceMoney.setApplyDate(Date.from(
							insuranceMoneyVO.getApply_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						insuranceMoneys.add(insuranceMoney);
					}
				}
				contract.setInsuranceMoneyList(insuranceMoneys);

				///////////
				try {
					Product product = null;

					ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
					for (DiseaseVO diseaseVO : diseaseVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
						if (productVO.getId() == e.getProduct_id()) {
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

							product = disease;
						}
					}
					ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
					for (InjuryVO injuryVO : injuryVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
						if (productVO.getId() == e.getProduct_id()) {
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

							product = injury;
						}
					}
					ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
					for (AutoMobileVO automobileVO : AutomobileVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

						if (productVO.getId() == e.getProduct_id()) {
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
							ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(e.getProduct_id());
							for (ServiceVO serviceVO : serviceVOs)
								serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
							automobile.setServiceList(serviceTypes);

							product = automobile;
						}
					}

					ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
					for (CollateralVO collateralVO : collateralVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							Collateral collateral = new Collateral();

							collateral.setName(productVO.getName());
							collateral.setLoanType(fromInt(loanVO.getLoan_type()));
							collateral.setId(loanVO.getProduct_id());
							collateral.setInterestRate(loanVO.getInterest_rate());
							collateral.setMaximumMoney(productVO.getMaximum_money());
							collateral.setMinimumAsset(loanVO.getMinimum_asset());
							collateral.setCollateralType(CollateralType.fromInt(collateralVO.getCollateral_type()));
							collateral.setMinimumValue(collateralVO.getMinimum_value());

							product = collateral;
						}
					}
					ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
					for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							com.example.bunsanedthinking_springback.entity.loan.FixedDeposit fixedDeposit = new FixedDeposit();

							fixedDeposit.setName(productVO.getName());
							fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
							fixedDeposit.setId(loanVO.getProduct_id());
							fixedDeposit.setInterestRate(loanVO.getInterest_rate());
							fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
							fixedDeposit.setMinimumAsset(loanVO.getMinimum_asset());
							fixedDeposit.setMinimumAmount(fixedDepositVO.getMinimum_amount());

							product = fixedDeposit;
						}
					}
					ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
					for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							com.example.bunsanedthinking_springback.entity.loan.InsuranceContract insuranceContract = new InsuranceContract();

							insuranceContract.setName(productVO.getName());
							insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
							insuranceContract.setId(loanVO.getProduct_id());
							insuranceContract.setInterestRate(loanVO.getInterest_rate());
							insuranceContract.setMaximumMoney(productVO.getMaximum_money());
							insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());

							product = insuranceContract;
						}
					}
					contract.setProduct(product);
					if (product == null) {
						throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
					}
				} catch (NotExistException exception) {
					throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
				}

				contracts.add(contract);
			}
		}
		return contracts;

		// return contractList.getAllRequestingInsurance();
	}

	public ArrayList<Contract> getAllNotRequestingInsurance() throws
		NotExistException {

		ArrayList<Contract> contracts = new ArrayList<>();
		ArrayList<ContractVO> contractVOs = contractMapper.getAll_UnderWritingModel();
		for (ContractVO e : contractVOs) {
			if (ContractStatus.ContractRequesting.getValue() == e.getContract_status()) {

				Contract contract = new Contract();

				ContractVO contractVO = contractMapper.get_UnderWritingModel(e.getId());
				contract.setId(contractVO.getId());
				contract.setDate(Date.from(contractVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				contract.setExpirationDate(
					Date.from(contractVO.getExpiration_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				contract.setPaymentDate(contractVO.getPayment_date().getDayOfMonth());
				if (contractVO.getTermination_date() != null) {
					contract.setTerminationDate(
						Date.from(contractVO.getTermination_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
				contract.setContractStatus(ContractStatus.fromInt(contractVO.getContract_status()));
				if (contractVO.getCustomer_id() != null) {
					contract.setCustomerID(contractVO.getCustomer_id());
				}
				if (contractVO.getEmployee_id() != null) {
					contract.setEmployeeID(contractVO.getEmployee_id());
				}
				contract.setLastPaidDate(
					Date.from(contractVO.getLastpaid_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));

				ArrayList<CompensationDetail> compensationDetails = new ArrayList<>();
				ArrayList<CompensationDetailVO> CompensationDetailVOs = compensationDetailMapper.getAll_UnderwritingModel();
				for (CompensationDetailVO compensationDetailVO : CompensationDetailVOs) {
					if (compensationDetailVO.getContract_id() == contract.getId()) {
						CompensationDetail compensationDetail = new CompensationDetail();
						compensationDetail.setId(compensationDetailVO.getId());
						compensationDetail.setMoney(compensationDetailVO.getMoney());
						compensationDetail.setPaymentDate(Date.from(
							compensationDetailVO.getPayment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						compensationDetail.setContractID(compensationDetailVO.getContract_id());
						compensationDetails.add(compensationDetail);
					}
				}
				contract.setCompensationDetailList(compensationDetails);

				ArrayList<DepositDetail> depositDetails = new ArrayList<>();
				ArrayList<DepositDetailVO> depositDetailVOs = depositDetailMapper.getAll_UnderwritingModel();
				for (DepositDetailVO depositDetailVO : depositDetailVOs) {
					if (depositDetailVO.getContract_id() == contract.getId()) {
						DepositDetail depositDetail = new DepositDetail();
						depositDetail.setId(depositDetailVO.getId());
						depositDetail.setDepositorName(depositDetailVO.getDepositor_name());
						depositDetail.setDate(
							Date.from(depositDetailVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						depositDetail.setMoney(depositDetailVO.getMoney());
						depositDetail.setPath(DepositPath.fromInt(depositDetailVO.getPath()));
						depositDetail.setContractID(depositDetailVO.getContract_id());
						depositDetails.add(depositDetail);
					}
				}
				contract.setDepositDetailList(depositDetails);

				ArrayList<InsuranceMoney> insuranceMoneys = new ArrayList<>();
				ArrayList<InsuranceMoneyVO> insuranceMoneyVOs = insuranceMoneyMapper.getAll_UnderwritingModel();
				for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyVOs) {
					if (insuranceMoneyVO.getContract_id() == contract.getId()) {
						InsuranceMoney insuranceMoney = new InsuranceMoney();
						insuranceMoney.setId(insuranceMoneyVO.getId());
						insuranceMoney.setBankAccount(insuranceMoneyVO.getBank_account());

						// insuranceMoney.setResidentRegistrationCard(
						// 	ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())));
						// insuranceMoney.setReceipt(ImageIO.read(new File(insuranceMoneyVO.getReceipt())));
						// insuranceMoney.setMedicalCertificate(
						// 	ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())));

						insuranceMoney.setContractID(insuranceMoneyVO.getContract_id());
						insuranceMoney.setBankName(insuranceMoneyVO.getBank_name());
						insuranceMoney.setProcessStatus(
							InsuranceMoneyStatus.fromInt(insuranceMoneyVO.getProcess_status()));
						insuranceMoney.setApplyDate(Date.from(
							insuranceMoneyVO.getApply_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
						insuranceMoneys.add(insuranceMoney);
					}
				}
				contract.setInsuranceMoneyList(insuranceMoneys);

				///////////
				try {
					Product product = null;

					ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
					for (DiseaseVO diseaseVO : diseaseVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
						if (productVO.getId() == e.getProduct_id()) {
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

							product = disease;
						}
					}
					ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
					for (InjuryVO injuryVO : injuryVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
						if (productVO.getId() == e.getProduct_id()) {
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

							product = injury;
						}
					}
					ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
					for (AutoMobileVO automobileVO : AutomobileVOs) {
						InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

						if (productVO.getId() == e.getProduct_id()) {
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
							ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(e.getProduct_id());
							for (ServiceVO serviceVO : serviceVOs)
								serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
							automobile.setServiceList(serviceTypes);

							product = automobile;
						}
					}

					ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
					for (CollateralVO collateralVO : collateralVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							Collateral collateral = new Collateral();

							collateral.setName(productVO.getName());
							collateral.setLoanType(fromInt(loanVO.getLoan_type()));
							collateral.setId(loanVO.getProduct_id());
							collateral.setInterestRate(loanVO.getInterest_rate());
							collateral.setMaximumMoney(productVO.getMaximum_money());
							collateral.setMinimumAsset(loanVO.getMinimum_asset());
							collateral.setCollateralType(CollateralType.fromInt(collateralVO.getCollateral_type()));
							collateral.setMinimumValue(collateralVO.getMinimum_value());

							product = collateral;
						}
					}
					ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
					for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							com.example.bunsanedthinking_springback.entity.loan.FixedDeposit fixedDeposit = new FixedDeposit();

							fixedDeposit.setName(productVO.getName());
							fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
							fixedDeposit.setId(loanVO.getProduct_id());
							fixedDeposit.setInterestRate(loanVO.getInterest_rate());
							fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
							fixedDeposit.setMinimumAsset(loanVO.getMinimum_asset());
							fixedDeposit.setMinimumAmount(fixedDepositVO.getMinimum_amount());

							product = fixedDeposit;
						}
					}
					ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
					for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
						LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
						ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
						if (e.getProduct_id() == productVO.getId()) {
							com.example.bunsanedthinking_springback.entity.loan.InsuranceContract insuranceContract = new InsuranceContract();

							insuranceContract.setName(productVO.getName());
							insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
							insuranceContract.setId(loanVO.getProduct_id());
							insuranceContract.setInterestRate(loanVO.getInterest_rate());
							insuranceContract.setMaximumMoney(productVO.getMaximum_money());
							insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());

							product = insuranceContract;
						}
					}
					contract.setProduct(product);
					if (product == null) {
						throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
					}
				} catch (NotExistException exception) {
					throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
				}

				contracts.add(contract);
			}
		}
		return contracts;
		// return contractList.getAllNotRequestingInsurance();
	}

	public Customer getCustomer(int id) {

		CustomerVO customerVO = customerMapper.get_UnderWritingModel(id);
		Customer customer = new Customer();
		customer.setId(customerVO.getId());
		customer.setName(customerVO.getName());
		customer.setPhoneNumber(customerVO.getPhone_number());
		customer.setJob(customerVO.getJob());
		customer.setAge(customerVO.getAge());
		customer.setGender(Gender.fromInt(customerVO.getGender()));
		customer.setResidentRegistrationNumber(customerVO.getResident_registration_number());
		customer.setAddress(customerVO.getAddress());
		customer.setBankAccount(customerVO.getBank_account());
		customer.setBankName(customerVO.getBank_name());

		ArrayList<SurgeryHistoryVO> surgeryHistoryVOs = surgeryHistoryMapper.get_UnderWritingModel(id);
		ArrayList<SurgeryHistory> surgeryHistoryList = new ArrayList<>();
		for (SurgeryHistoryVO surgeryHistoryVO : surgeryHistoryVOs) {
			SurgeryHistory surgeryHistory = new SurgeryHistory();
			surgeryHistory.setId(surgeryHistoryVO.getId());
			surgeryHistory.setHospitalName(surgeryHistoryVO.getHospital_name());
			surgeryHistory.setName(surgeryHistoryVO.getName());
			surgeryHistory.setCustomerID(surgeryHistoryVO.getCustomer_id());
			surgeryHistory.setDate(java.sql.Date.valueOf(surgeryHistoryVO.getDate()));
			surgeryHistoryList.add(surgeryHistory);
		}
		customer.setSurgeryHistoryList(surgeryHistoryList);

		ArrayList<AccidentHistoryVO> accidentHistoryVOs = accidentHistoryMapper.get_UnderWritingModel(id);
		ArrayList<AccidentHistory> accidentHistoryList = new ArrayList<>();
		for (AccidentHistoryVO accidentHistoryVO : accidentHistoryVOs) {
			AccidentHistory accidentHistory = new AccidentHistory();
			accidentHistory.setId(accidentHistoryVO.getId());
			accidentHistory.setDate(
				Date.from(accidentHistoryVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			accidentHistory.setAccidentDetail(accidentHistoryVO.getDetails_of_accident());
			accidentHistory.setCustomerID(accidentHistoryVO.getCustomer_id());
		}
		customer.setAccidentHistoryList(accidentHistoryList);

		ArrayList<DiseaseHistoryVO> diseaseHistoryVOs = diseaseHistoryMapper.get_UnderWritingModel(id);
		ArrayList<DiseaseHistory> diseaseHistoryList = new ArrayList<>();
		for (DiseaseHistoryVO diseaseHistoryVO : diseaseHistoryVOs) {
			DiseaseHistory diseaseHistory = new DiseaseHistory();
			diseaseHistory.setId(diseaseHistoryVO.getId());
			diseaseHistory.setDate_of_diagnosis(
				Date.from(diseaseHistoryVO.getDate_of_diagnosis().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			diseaseHistory.setName(diseaseHistoryVO.getName());
			diseaseHistory.setCustomer_id(diseaseHistoryVO.getCustomer_id());
			diseaseHistoryList.add(diseaseHistory);
		}
		customer.setDiseaseHistoryList(diseaseHistoryList);

		return customer;
		// return customerList.get(id);
	}

	public Contract getContract(int id) throws
		NotExistException {

		Contract contract = new Contract();

		ContractVO contractVO = contractMapper.get_UnderWritingModel(id);
		contract.setId(contractVO.getId());
		contract.setDate(Date.from(contractVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		contract.setExpirationDate(
			Date.from(contractVO.getExpiration_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		contract.setPaymentDate(contractVO.getPayment_date().getDayOfMonth());
		if (contractVO.getTermination_date() != null) {
			contract.setTerminationDate(
				Date.from(contractVO.getTermination_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		contract.setContractStatus(ContractStatus.fromInt(contractVO.getContract_status()));
		if (contractVO.getCustomer_id() != null) {
			contract.setCustomerID(contractVO.getCustomer_id());
		}
		if (contractVO.getEmployee_id() != null) {
			contract.setEmployeeID(contractVO.getEmployee_id());
		}
		contract.setLastPaidDate(
			Date.from(contractVO.getLastpaid_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		ArrayList<CompensationDetail> compensationDetails = new ArrayList<>();
		ArrayList<CompensationDetailVO> CompensationDetailVOs = compensationDetailMapper.getAll_UnderwritingModel();
		for (CompensationDetailVO compensationDetailVO : CompensationDetailVOs) {
			if (compensationDetailVO.getContract_id() == contract.getId()) {
				CompensationDetail compensationDetail = new CompensationDetail();
				compensationDetail.setId(compensationDetailVO.getId());
				compensationDetail.setMoney(compensationDetailVO.getMoney());
				compensationDetail.setPaymentDate(
					Date.from(compensationDetailVO.getPayment_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				compensationDetail.setContractID(compensationDetailVO.getContract_id());
				compensationDetails.add(compensationDetail);
			}
		}
		contract.setCompensationDetailList(compensationDetails);

		ArrayList<DepositDetail> depositDetails = new ArrayList<>();
		ArrayList<DepositDetailVO> depositDetailVOs = depositDetailMapper.getAll_UnderwritingModel();
		for (DepositDetailVO depositDetailVO : depositDetailVOs) {
			if (depositDetailVO.getContract_id() == contract.getId()) {
				DepositDetail depositDetail = new DepositDetail();
				depositDetail.setId(depositDetailVO.getId());
				depositDetail.setDepositorName(depositDetailVO.getDepositor_name());
				depositDetail.setDate(
					Date.from(depositDetailVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				depositDetail.setMoney(depositDetailVO.getMoney());
				depositDetail.setPath(DepositPath.fromInt(depositDetailVO.getPath()));
				depositDetail.setContractID(depositDetailVO.getContract_id());
				depositDetails.add(depositDetail);
			}
		}
		contract.setDepositDetailList(depositDetails);

		ArrayList<InsuranceMoney> insuranceMoneys = new ArrayList<>();
		ArrayList<InsuranceMoneyVO> insuranceMoneyVOs = insuranceMoneyMapper.getAll_UnderwritingModel();
		for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyVOs) {
			if (insuranceMoneyVO.getContract_id() == contract.getId()) {
				InsuranceMoney insuranceMoney = new InsuranceMoney();
				insuranceMoney.setId(insuranceMoneyVO.getId());
				insuranceMoney.setBankAccount(insuranceMoneyVO.getBank_account());

				// insuranceMoney.setResidentRegistrationCard(
				// 	ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())));
				// insuranceMoney.setReceipt(ImageIO.read(new File(insuranceMoneyVO.getReceipt())));
				// insuranceMoney.setMedicalCertificate(ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())));

				insuranceMoney.setContractID(insuranceMoneyVO.getContract_id());
				insuranceMoney.setBankName(insuranceMoneyVO.getBank_name());
				insuranceMoney.setProcessStatus(InsuranceMoneyStatus.fromInt(insuranceMoneyVO.getProcess_status()));
				insuranceMoney.setApplyDate(
					Date.from(insuranceMoneyVO.getApply_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				insuranceMoneys.add(insuranceMoney);
			}
		}
		contract.setInsuranceMoneyList(insuranceMoneys);

		///////////
		try {
			Product product = null;

			ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
			for (DiseaseVO diseaseVO : diseaseVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if (productVO.getId() == contractVO.getProduct_id()) {
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

					product = disease;
				}
			}
			ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
			for (InjuryVO injuryVO : injuryVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if (productVO.getId() == contractVO.getProduct_id()) {
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

					product = injury;
				}
			}
			ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
			for (AutoMobileVO automobileVO : AutomobileVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

				if (productVO.getId() == contractVO.getProduct_id()) {
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
					ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(insuranceVO.getProduct_id());
					for (ServiceVO serviceVO : serviceVOs)
						serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
					automobile.setServiceList(serviceTypes);

					product = automobile;
				}
			}

			ArrayList<CollateralVO> collateralVOs = collateralMapper.getAllCollateralLoan_SalesModel();
			for (CollateralVO collateralVO : collateralVOs) {
				LoanVO loanVO = loanMapper.get_SalesModel(collateralVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
				if (contractVO.getProduct_id() == productVO.getId()) {
					Collateral collateral = new Collateral();

					collateral.setName(productVO.getName());
					collateral.setLoanType(fromInt(loanVO.getLoan_type()));
					collateral.setId(loanVO.getProduct_id());
					collateral.setInterestRate(loanVO.getInterest_rate());
					collateral.setMaximumMoney(productVO.getMaximum_money());
					collateral.setMinimumAsset(loanVO.getMinimum_asset());
					collateral.setCollateralType(CollateralType.fromInt(collateralVO.getCollateral_type()));
					collateral.setMinimumValue(collateralVO.getMinimum_value());

					product = collateral;
				}
			}
			ArrayList<FixedDepositVO> fixedDepositVOs = fixedDepositMapper.getAllFixedDepositLoan_SalesModel();
			for (FixedDepositVO fixedDepositVO : fixedDepositVOs) {
				LoanVO loanVO = loanMapper.get_SalesModel(fixedDepositVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
				if (contractVO.getProduct_id() == productVO.getId()) {
					FixedDeposit fixedDeposit = new FixedDeposit();

					fixedDeposit.setName(productVO.getName());
					fixedDeposit.setLoanType(fromInt(loanVO.getLoan_type()));
					fixedDeposit.setId(loanVO.getProduct_id());
					fixedDeposit.setInterestRate(loanVO.getInterest_rate());
					fixedDeposit.setMaximumMoney(productVO.getMaximum_money());
					fixedDeposit.setMinimumAsset(loanVO.getMinimum_asset());
					fixedDeposit.setMinimumAmount(fixedDepositVO.getMinimum_amount());

					product = fixedDeposit;
				}
			}
			ArrayList<InsuranceContractVO> insuranceContractVOs = insuranceContractMapper.getAllInsuranceContractLoan_SalesModel();
			for (InsuranceContractVO insuranceContractVO : insuranceContractVOs) {
				LoanVO loanVO = loanMapper.get_SalesModel(insuranceContractVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(loanVO.getProduct_id());
				if (contractVO.getProduct_id() == productVO.getId()) {
					com.example.bunsanedthinking_springback.entity.loan.InsuranceContract insuranceContract = new InsuranceContract();

					insuranceContract.setName(productVO.getName());
					insuranceContract.setLoanType(fromInt(loanVO.getLoan_type()));
					insuranceContract.setId(loanVO.getProduct_id());
					insuranceContract.setInterestRate(loanVO.getInterest_rate());
					insuranceContract.setMaximumMoney(productVO.getMaximum_money());
					insuranceContract.setMinimumAsset(loanVO.getMinimum_asset());

					product = insuranceContract;
				}
			}
			contract.setProduct(product);
			if (product == null) {
				throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
			}
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
		}

		return contract;
		// return contractList.get(id);
	}

}
