package com.example.bunsanedthinking_springback.model.financialAccountant;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AutomobileMapper;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.CompensationDetailMapper;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceContractMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMoneyMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.repository.ServiceMapper;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

@Service
public class FinancialAccountantModel {

	@Autowired
	private DepositDetailMapper depositDetailMapper;
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private CompensationDetailMapper compensationDetailMapper;
	@Autowired
	private InsuranceMoneyMapper insuranceMoneyMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private InjuryMapper injuryMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
	@Autowired
	private AutomobileMapper automobileMapper;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private CollateralMapper collateralMapper;
	@Autowired
	private InsuranceContractMapper insuranceContractMapper;
	@Autowired
	private FixedDepositMapper fixedDepositMapper;
	@Autowired
	private CustomerMapper customerMapper;

	public DepositDetail getDepositDetail(DepositDetailList depositDetailList, int id) throws NotExistException{
		DepositDetailVO depositDetailVO = depositDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 입금 내역 정보가 존재하지 않습니다."));
		return new DepositDetail(depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(),
			depositDetailVO.getMoney(), DepositPath.indexOf(depositDetailVO.getPath()));
	}

	public void getTaxPaymentDetail(){
		//
	}

	public void handlePayment(PaymentDetail paymentDetail, PaymentDetailList paymentDetailList) throws NotExistException, AlreadyProcessedException {
		// if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		PaymentDetailVO paymentDetailVO = PaymentDetailVO.from(paymentDetail);
		paymentDetailVO.setProcess_status(PaymentProcessStatus.Completed.ordinal());
		paymentDetailMapper.update_FinancialAccountant(paymentDetailVO);
	}
	public ArrayList<PaymentDetail> getAllPaymentDetail(PaymentDetailList paymentDetailList) {
		ArrayList<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.getAll_FinancialAccountant();
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}
	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail(PaymentDetailList paymentDetailList) {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Unprocessed);
	}
	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail(PaymentDetailList paymentDetailList) {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Completed);
	}
	private ArrayList<PaymentDetail> getAllPaymentDetailByProcessStatus(PaymentProcessStatus processStatus) {
		ArrayList<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.findByProcessStatus_FinancialAccountant(processStatus.ordinal());
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}
	public PaymentDetail get(PaymentDetailList paymentDetailList, int id) throws NotExistException {
		PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 지급사항 정보를 찾을 수 없습니다."));
		return new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
			paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
			PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
			paymentDetailVO.getEmployee_id());
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		ContractVO contractVO = contractMapper.findById_FinancialAccountant(id)
			.orElseThrow(NotExistContractException::new);
		ProductVO productVO = productMapper.findById_LoanManagement(contractVO.getProduct_id())
			.orElseThrow(NotExistContractException::new);
		String productSerial = Product.PRODUCT_SERIAL_NUMBER + "";
		String insuranceSerial = Insurance.INSURANCE_SERIAL_NUMBER + "";
		String loanSerial = Loan.LOAN_SERIAL_NUMBER + "";
		String productTypeSerial = (productVO.getId() + "")
			.substring(productSerial.length(), productSerial.length() + insuranceSerial.length());
		Product product;
		try {
			if (productTypeSerial.equals(insuranceSerial)) {
				product = getInsurance(productVO);
			} else if (productTypeSerial.equals(loanSerial)) {
				product = getLoan(productVO);
			} else {
				throw new NotExistContractException();
			}
		} catch (NotExistException e) {
			throw new NotExistContractException();
		}
		ArrayList<CompensationDetail> compensationDetailList = new ArrayList<>();
		for (CompensationDetailVO compensationDetailVO :
			compensationDetailMapper.findByContractId_FinancialAccountant(contractVO.getId())) {
			compensationDetailList.add(new CompensationDetail(
				compensationDetailVO.getContract_id(), compensationDetailVO.getMoney()));
		}
		ArrayList<DepositDetail> depositDetailList = new ArrayList<>();
		Date lastPaymentDate = null;
		for (DepositDetailVO depositDetailVO :
			depositDetailMapper.findByContractId_FinancialAccountant(contractVO.getId())) {
			DepositDetail depositDetail = new DepositDetail(
				depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(),
				depositDetailVO.getMoney(), DepositPath.indexOf(depositDetailVO.getPath()));
			if (lastPaymentDate == null || depositDetailVO.getDate().isAfter(
					lastPaymentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				lastPaymentDate = Date.valueOf(depositDetailVO.getDate());
			}
			depositDetailList.add(depositDetail);
		}
		ArrayList<InsuranceMoney> insuranceMoneyList = new ArrayList<>();
		for (InsuranceMoneyVO insuranceMoneyVO :
			insuranceMoneyMapper.findByContractId_FinancialAccountant(contractVO.getId())) {
			try {
				insuranceMoneyList.add(
					new InsuranceMoney(insuranceMoneyVO.getContract_id(), insuranceMoneyVO.getBank_name(),
						insuranceMoneyVO.getBank_account(),
						ImageIO.read(new File(insuranceMoneyVO.getMedical_certificate())),
						ImageIO.read(new File(insuranceMoneyVO.getReceipt())),
						ImageIO.read(new File(insuranceMoneyVO.getResident_registration_card()))));
			} catch (IOException ignored) {
			}
		}
		return new Contract(compensationDetailList, ContractStatus.indexOf(contractVO.getContract_status()),
			contractVO.getCustomer_id(), Date.valueOf(contractVO.getDate()), depositDetailList, contractVO.getEmployee_id(),
			contractVO.getPayment_date().getDayOfMonth(), Date.valueOf(contractVO.getExpiration_date()), contractVO.getId(), insuranceMoneyList,
			lastPaymentDate, product, Date.valueOf(contractVO.getTermination_date()));
	}

	private Insurance getInsurance(ProductVO productVO) throws NotExistException{
		InsuranceVO insuranceVO = insuranceMapper.findById_FinancialAccountant(productVO.getId())
			.orElseThrow(NotExistException::new);
		InsuranceType insuranceType = InsuranceType.indexOf(insuranceVO.getInsurance_type());
		switch (insuranceType) {
			case Injury -> {
				InjuryVO injuryVO = injuryMapper.findById_FinancialAccountant(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 상해 보험 정보"));
				return new Injury(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), InjuryType.indexOf(injuryVO.getInjury_type()),
					injuryVO.getSurgeries_limit());
			}
			case Disease -> {
				DiseaseVO diseaseVO = diseaseMapper.findById_FinancialAccountant(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 질병 보험 정보"));
				return new Disease(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), diseaseVO.getDisease_name(), diseaseVO.getDisease_limit(),
					diseaseVO.getSurgeries_limit());
			}
			case Automobile -> {
				AutoMobileVO autoMobileVO = automobileMapper.findById_FinancialAccountant(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 질병 보험 정보"));
				ArrayList<ServiceType> serviceTypeList = new ArrayList<>();
				for (ServiceVO serviceVO : serviceMapper.findByProductId_FinancialAccountant(productVO.getId())) {
					serviceTypeList.add(ServiceType.indexOf(serviceVO.getService()));
				}
				return new Automobile(insuranceType, productVO.getName(), productVO.getMaximum_money(),
					insuranceVO.getAge_range(), insuranceVO.getCoverage(), insuranceVO.getMonthly_premium(),
					insuranceVO.getContract_period(), autoMobileVO.getAccident_limit(),
					VehicleType.indexOf(autoMobileVO.getVerhicle_type()), serviceTypeList);
			}
			default -> throw new NotExistException();
		}
	}

	private Loan getLoan(ProductVO productVO) throws NotExistException {
		LoanVO loanVO = loanMapper.findById_LoanManagement(productVO.getId())
			.orElseThrow(NotExistException::new);
		LoanType loanType = LoanType.indexOf(loanVO.getLoan_type());
		switch (loanType) {
			case Collateral -> {
				CollateralVO collateralVO = collateralMapper.findById_LoanManagement(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 담보 대출 정보"));
				return new Collateral(loanType, productVO.getName(), loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(),
					CollateralType.indexOf(collateralVO.getCollateral_type()), collateralVO.getMinimum_value());
			}
			case InsuranceContract -> {
				InsuranceContractVO insuranceContractVO = insuranceContractMapper.findById_LoanManagement(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 보험 계약 대출 정보"));
				return new InsuranceContract(loanType, productVO.getName(), loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(), insuranceContractVO.getInsurance_id());
			}
			case FixedDeposit -> {
				FixedDepositVO fixedDepositVO = fixedDepositMapper.findById_LoanManagement(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 정기 예금 대출 정보"));
				return new FixedDeposit(loanType, productVO.getName(), loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(), fixedDepositVO.getMinimum_amount());
			}
			default -> throw new NotExistException();
		}
	}

	public Customer get(CustomerList customerList, int id) throws NotExistException {
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(id)
			.orElseThrow(NotExistException::new);
		Customer customer = new Customer();
		customer.setId(customerVO.getId());
		customer.setBankAccount(customerVO.getBank_account());
		customer.setBankName(customerVO.getBank_name());
		return customer;
	}
	public ArrayList<DepositDetail> getAllDepositDetail(DepositDetailList depositDetailList) {
		ArrayList<DepositDetail> result = new ArrayList<>();
		List<DepositDetailVO> depositDetailVOList = depositDetailMapper.getAll_FinancialAccountant();
		for (DepositDetailVO depositDetailVO : depositDetailVOList) {
			result.add(new DepositDetail(depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(), depositDetailVO.getMoney(),
				DepositPath.indexOf(depositDetailVO.getPath())));
		}
		return result;
	}
}
