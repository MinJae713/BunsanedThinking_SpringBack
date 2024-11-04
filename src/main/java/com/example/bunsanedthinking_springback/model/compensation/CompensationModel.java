package com.example.bunsanedthinking_springback.model.compensation;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.AutomobileMapper;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMoneyMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

@Service
public class CompensationModel {

	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private AccidentMapper accidentMapper;
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private InsuranceMoneyMapper insuranceMoneyMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
	@Autowired
	private InjuryMapper injuryMapper;
	@Autowired
	private AutomobileMapper automobileMapper;

	public void requestCompensation(String accountHolder, String bank, String bankAccount, int money,
									PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList, Report report, ReportList reportList, AccidentList accidentList)
					throws NotExistException, AlreadyProcessedException{
		if (report.getProcessStatus() == ReportProcessStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		Integer maxId = paymentDetailMapper.getMaxId_LoanManagement();
		int id;
		if (maxId == null) {
			String compound = PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "1";
			id = Integer.parseInt(compound);
		} else {
			int paymentSerialLength = (PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "").length();
			int index = Integer.parseInt(maxId.toString().substring(paymentSerialLength));
			String compound = PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "" +index;
			id = Integer.parseInt(compound);
		}

		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(id, accountHolder, bank, bankAccount, money, paymentType.ordinal(),
			PaymentProcessStatus.Unprocessed.ordinal(), contractId, null);
		paymentDetailMapper.insert_LoanManagement(paymentDetailVO);
		ReportVO reportVO = reportMapper.findById_Compensation(report.getId())
			.orElseThrow(() -> new NotExistException("해당하는 신고 정보를 찾을 수 없습니다."));
		reportVO.setProcess_status(ReportProcessStatus.Completed.ordinal());
		reportMapper.update_Compensation(reportVO);
		AccidentVO accidentVO = accidentMapper.findByID_CustomerSupport(reportVO.getAccident_id())
			.orElseThrow(() -> new NotExistException("해당하는 사고 정보를 찾을 수 없습니다."));
		accidentVO.setProcess_status(AccidentProcessStatus.Completed.ordinal());
		accidentMapper.update_CustomerSupport(accidentVO);
	}
	
	public void requestInsuranceMoney(Customer customer, int money, InsuranceMoney insuranceMoney, InsuranceMoneyList insuranceMoneyList,
									  PaymentType paymentType, int contractId, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException{
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		Integer maxId = paymentDetailMapper.getMaxId_LoanManagement();
		int id;
		if (maxId == null) {
			id = 1;
		} else {
			int paymentSerialLength = (PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "").length();
			int index = Integer.parseInt(maxId.toString().substring(paymentSerialLength));
			String compound = PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "" +index;
			id = Integer.parseInt(compound);
		}
		ContractVO contractVO = contractMapper.findById_FinancialAccountant(contractId)
			.orElseThrow(() -> new NotExistException("해당하는 계약 정보가 존재하지 않습니다."));
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(contractVO.getCustomer_id())
			.orElseThrow(() -> new NotExistException("해당하는 고객 정보가 존재하지 않습니다."));
		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(id, customerVO.getName(),
			customerVO.getBank_name(), customerVO.getBank_account(), money, paymentType.ordinal(),
			PaymentProcessStatus.Unprocessed.ordinal(), contractId, null);
		paymentDetailMapper.insert_LoanManagement(paymentDetailVO);
		InsuranceMoneyVO insuranceMoneyVO = insuranceMoneyMapper.findById_Compensation(insuranceMoney.getId())
			.orElseThrow(() -> new NotExistException("해당하는 보험금 신청 정보가 존재하지 않습니다."));
		insuranceMoneyVO.setProcess_status(InsuranceMoneyStatus.Completed.ordinal());
		insuranceMoneyMapper.insert_Compensation(insuranceMoneyVO);
	}
	// 메소드는 아래에 적어주셔유! (MVC 적용)

	public ArrayList<InsuranceMoney> getAll(InsuranceMoneyList insuranceMoneyList) {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyMapper.getAll_Compensation()) {
			try {
				result.add(new InsuranceMoney(insuranceMoneyVO.getBank_name(), insuranceMoneyVO.getBank_account(),
					insuranceMoneyVO.getContract_id(), insuranceMoneyVO.getId(),
					ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())),
					ImageIO.read(new File(insuranceMoneyVO.getReceipt())),
					ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())),
					InsuranceMoneyStatus.indexOf(insuranceMoneyVO.getProcess_status()),
					Date.valueOf(insuranceMoneyVO.getApply_date())));
			} catch (IOException ignored) {
			}
		}
		return result;
	}

	public ArrayList<InsuranceMoney> getAllUnprocessed(InsuranceMoneyList insuranceMoneyList) {
		return getAllByInsuranceMoneyStatus(InsuranceMoneyStatus.Unprocessed);
	}

	public ArrayList<InsuranceMoney> getAllProcessed(InsuranceMoneyList insuranceMoneyList) {
		return getAllByInsuranceMoneyStatus(InsuranceMoneyStatus.Completed);
	}

	private ArrayList<InsuranceMoney> getAllByInsuranceMoneyStatus(InsuranceMoneyStatus insuranceMoneyStatus) {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		for (InsuranceMoneyVO insuranceMoneyVO : insuranceMoneyMapper.findByStatus_Compensation(insuranceMoneyStatus.ordinal())) {
			try {
				result.add(new InsuranceMoney(insuranceMoneyVO.getBank_name(), insuranceMoneyVO.getBank_account(),
					insuranceMoneyVO.getContract_id(), insuranceMoneyVO.getId(),
					ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())),
					ImageIO.read(new File(insuranceMoneyVO.getReceipt())),
					ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())),
					InsuranceMoneyStatus.indexOf(insuranceMoneyVO.getProcess_status()),
					Date.valueOf(insuranceMoneyVO.getApply_date())));
			} catch (IOException ignored) {
			}
		}
		return result;
	}

	public InsuranceMoney get(InsuranceMoneyList insuranceMoneyList, int id) throws NotExistException {
		try {
			InsuranceMoneyVO insuranceMoneyVO = insuranceMoneyMapper.findById_Compensation(id)
				.orElseThrow(() -> new NotExistException("해당하는 보험금 신청 정보가 존재하지 않습니다."));
			return new InsuranceMoney(insuranceMoneyVO.getBank_name(), insuranceMoneyVO.getBank_account(),
				insuranceMoneyVO.getContract_id(), insuranceMoneyVO.getId(),
				ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())),
				ImageIO.read(new File(insuranceMoneyVO.getReceipt())),
				ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card())),
				InsuranceMoneyStatus.indexOf(insuranceMoneyVO.getProcess_status()),
				Date.valueOf(insuranceMoneyVO.getApply_date()));
		} catch (IOException e) {
			throw new NotExistException("보험금 신청 정보 이미지를 불러오는 도중 문제가 발생했습니다.");
		}
	}

	public Contract get(ContractList contractList, int contractId) throws NotExistContractException {
		ContractVO contractVO = contractMapper.findById_FinancialAccountant(contractId)
			.orElseThrow(NotExistContractException::new);
		ProductVO productVO = productMapper.findById_LoanManagement(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		InsuranceVO insuranceVO = insuranceMapper.findById_FinancialAccountant(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		Product product = null;
		InsuranceType insuranceType = InsuranceType.indexOf(insuranceVO.getInsurance_type());
		switch (insuranceType) {
			case Disease -> {
				DiseaseVO diseaseVO = diseaseMapper.findById_FinancialAccountant(contractVO.getProduct_id())
					.orElseThrow(NotExistContractException::new);
				product = new Disease(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), diseaseVO.getDisease_name(), diseaseVO.getDisease_limit(), diseaseVO.getSurgeries_limit());
			}
			case Injury -> {
				InjuryVO injuryVO = injuryMapper.findById_FinancialAccountant(contractVO.getProduct_id())
					.orElseThrow(NotExistContractException::new);
				product = new Injury(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), InjuryType.indexOf(injuryVO.getInjury_type()), injuryVO.getSurgeries_limit());
			}
			case Automobile -> {
				AutoMobileVO autoMobileVO = automobileMapper.findById_FinancialAccountant(contractVO.getProduct_id())
					.orElseThrow(NotExistContractException::new);
				product = new Automobile(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), autoMobileVO.getAccident_limit(),
					VehicleType.indexOf(autoMobileVO.getVerhicle_type()), new ArrayList<>());
			}
		}
		return new Contract(null, ContractStatus.indexOf(contractVO.getContract_status()),
			contractVO.getCustomer_id(), Date.valueOf(contractVO.getDate()), null,
			contractVO.getEmployee_id(), contractVO.getPayment_date().getDayOfMonth(),
			Date.valueOf(contractVO.getExpiration_date()), contractVO.getId(), null,
			null, product, Date.valueOf(contractVO.getTermination_date()));
	}

	public Customer get(CustomerList customerList, int customerID) throws NotExistException {
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(customerID)
			.orElseThrow(() -> new NotExistException("해당하는 고객 정보가 존재하지 않습니다."));
		return new Customer(customerVO.getName(), customerVO.getPhone_number(), customerVO.getJob(),
			customerVO.getAge(), Gender.indexOf(customerVO.getGender()), customerVO.getResident_registration_number(),
			customerVO.getAddress(), customerVO.getProperty(), customerVO.getBank_name(), customerVO.getBank_account());
	}

	public ArrayList<Report> getAll(ReportList reportList) {
		ArrayList<Report> result = new ArrayList<>();
		for (ReportVO reportVO : reportMapper.getAll_Compensation()) {
			Optional<AccidentVO> optionalAccidentVO = accidentMapper.findByID_CustomerSupport(reportVO.getAccident_id());
			if (optionalAccidentVO.isEmpty()) {
				continue;
			}
			AccidentVO accidentVO = optionalAccidentVO.get();
			Optional<CustomerVO> optionalCustomerVO = customerMapper.findById_FinancialAccountant(accidentVO.getId());
			if (optionalCustomerVO.isEmpty()) {
				continue;
			}
			CustomerVO customerVO = optionalCustomerVO.get();
			Accident accident = new Accident(customerVO.getId(), customerVO.getName(), customerVO.getPhone_number(),
				Date.valueOf(accidentVO.getDate()), accidentVO.getId(), accidentVO.getLocation(),
				AccidentProcessStatus.indexOf(accidentVO.getProcess_status()), ServiceType.indexOf(accidentVO.getService_type()));
			result.add(new Report(accident, reportVO.getDamage_assessment_money(), reportVO.getAccident_id(),
				reportVO.getRoadside_assistance_company_id(), reportVO.getDamage_assessment_company_id(),
				ReportProcessStatus.indexOf(reportVO.getProcess_status())));
		}
		return result;
	}

	public Report get(ReportList reportList, int id) throws NotExistException {
		ReportVO reportVO = reportMapper.findById_Compensation(id)
			.orElseThrow(() -> new NotExistException("해당하는 신고 정보가 존재하지 않습니다."));;
		AccidentVO accidentVO = accidentMapper.findByID_CustomerSupport(reportVO.getAccident_id())
			.orElseThrow(() -> new NotExistException("해당하는 사고 정보가 존재하지 않습니다."));
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(accidentVO.getCustomer_id())
			.orElseThrow(() -> new NotExistException("해당하는 고객 정보가 존재하지 않습니다."));
		Accident accident = new Accident(customerVO.getId(), customerVO.getName(), customerVO.getPhone_number(),
			Date.valueOf(accidentVO.getDate()), accidentVO.getId(), accidentVO.getLocation(),
			AccidentProcessStatus.indexOf(accidentVO.getProcess_status()), ServiceType.indexOf(accidentVO.getService_type()));
		return new Report(accident, reportVO.getDamage_assessment_money(), reportVO.getAccident_id(),
			reportVO.getRoadside_assistance_company_id(), reportVO.getDamage_assessment_company_id(),
			ReportProcessStatus.indexOf(reportVO.getProcess_status()));
	}

	public ArrayList<Report> getAllUnprocessedReport(ReportList reportList) {
		return getAllByProcessStatus(ReportProcessStatus.Unprocessed);
	}

	public ArrayList<Report> getAllCompletedReport(ReportList reportList) {
		return getAllByProcessStatus(ReportProcessStatus.Completed);
	}

	private ArrayList<Report> getAllByProcessStatus(ReportProcessStatus processStatus) {
		ArrayList<Report> result = new ArrayList<>();
		for (ReportVO reportVO : reportMapper.findByProcessStatus_Compensation(processStatus.ordinal())) {
			Optional<AccidentVO> optionalAccidentVO = accidentMapper.findByID_CustomerSupport(reportVO.getAccident_id());
			if (optionalAccidentVO.isEmpty()) {
				continue;
			}
			AccidentVO accidentVO = optionalAccidentVO.get();
			Optional<CustomerVO> optionalCustomerVO = customerMapper.findById_FinancialAccountant(accidentVO.getId());
			if (optionalCustomerVO.isEmpty()) {
				continue;
			}
			CustomerVO customerVO = optionalCustomerVO.get();
			Accident accident = new Accident(customerVO.getId(), customerVO.getName(), customerVO.getPhone_number(),
				Date.valueOf(accidentVO.getDate()), accidentVO.getId(), accidentVO.getLocation(),
				AccidentProcessStatus.indexOf(accidentVO.getProcess_status()), ServiceType.indexOf(accidentVO.getService_type()));
			result.add(new Report(accident, reportVO.getDamage_assessment_money(), reportVO.getAccident_id(),
				reportVO.getRoadside_assistance_company_id(), reportVO.getDamage_assessment_company_id(),
				ReportProcessStatus.indexOf(reportVO.getProcess_status())));
		}
		return result;
	}

	public Contract getAutomobileByMember(ContractList contractList, int customerID) throws NotExistContractException {
		ContractVO contractVO = null;
		for (InsuranceVO insuranceVO: insuranceMapper.findByInsuranceType_Compensation(InsuranceType.Automobile.ordinal())) {
			Optional<ContractVO> optionalContractVO = contractMapper.findByCustomerIdAndProductId_FinancialAccountant(customerID, insuranceVO.getProduct_id());
			if (optionalContractVO.isPresent()) {
				contractVO = optionalContractVO.get();
				break;
			}
		}
		if (contractVO == null) {
			throw new NotExistContractException();
		}
		ProductVO productVO = productMapper.findById_LoanManagement(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		InsuranceVO insuranceVO = insuranceMapper.findById_FinancialAccountant(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		AutoMobileVO autoMobileVO = automobileMapper.findById_FinancialAccountant(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		Product product = new Automobile(InsuranceType.indexOf(insuranceVO.getInsurance_type()),
			productVO.getName(), productVO.getMaximum_money(), insuranceVO.getAge_range(),
			insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(), insuranceVO.getContract_period(),
			autoMobileVO.getAccident_limit(), VehicleType.indexOf(autoMobileVO.getVerhicle_type()),
			new ArrayList<>());
		return new Contract(null, ContractStatus.indexOf(contractVO.getContract_status()),
			contractVO.getCustomer_id(), Date.valueOf(contractVO.getDate()), null,
			contractVO.getEmployee_id(), contractVO.getPayment_date().getDayOfMonth(),
			Date.valueOf(contractVO.getExpiration_date()), contractVO.getId(), null,
			null, product, Date.valueOf(contractVO.getTermination_date()));
	}
}
