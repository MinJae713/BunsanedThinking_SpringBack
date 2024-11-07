package com.example.bunsanedthinking_springback.model.service.employee.financialAccountant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
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
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;

@Service
public class FinancialAccountantSModel {

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
	@Autowired
	private ContractDModel contractDModel;

	public DepositDetail getDepositDetail(int id) throws NotExistException {
		DepositDetailVO depositDetailVO = depositDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 입금 내역 정보가 존재하지 않습니다."));
		return new DepositDetail(id, depositDetailVO.getDepositor_name(), depositDetailVO.getContract_id(),
			depositDetailVO.getMoney(), DepositPath.indexOf(depositDetailVO.getPath()));
	}

	public void getTaxPaymentDetail() {
		//
	}

	public void handlePayment(int paymentDetailId) throws NotExistException, AlreadyProcessedException {
		// if문 - 보험사 운영시간이 아닙니다. 다른 시간에 다시 이용해주세요 - 데코레이터 추가
		PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(paymentDetailId)
			.orElseThrow(() -> new NotExistException("해당하는 지급 사항 정보가 존재하지 않습니다."));
		if (paymentDetailVO.getProcess_status() == PaymentProcessStatus.Completed.ordinal()) {
			throw new AlreadyProcessedException("이미 지급이 완료되었습니다.");
		}
		paymentDetailVO.setProcess_status(PaymentProcessStatus.Completed.ordinal());
		paymentDetailMapper.update_FinancialAccountant(paymentDetailVO);
	}

	public List<PaymentDetail> getAllPaymentDetail() {
		List<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.getAll_FinancialAccountant();
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}

	public List<PaymentDetail> getAllUnprocessedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Unprocessed);
	}

	public List<PaymentDetail> getAllCompletedPaymentDetail() {
		return getAllPaymentDetailByProcessStatus(PaymentProcessStatus.Completed);
	}

	private List<PaymentDetail> getAllPaymentDetailByProcessStatus(PaymentProcessStatus processStatus) {
		List<PaymentDetail> result = new ArrayList<>();
		List<PaymentDetailVO> paymentDetailVOList = paymentDetailMapper.findByProcessStatus_FinancialAccountant(
			processStatus.ordinal());
		for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
			result.add(new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
				paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
				PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
				paymentDetailVO.getEmployee_id()));
		}
		return result;
	}

	public PaymentDetail getPaymentDetail(int id) throws NotExistException {
		PaymentDetailVO paymentDetailVO = paymentDetailMapper.findById_FinancialAccountant(id)
			.orElseThrow(() -> new NotExistException("해당하는 지급사항 정보를 찾을 수 없습니다."));
		return new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(),
			paymentDetailVO.getBank_account(), paymentDetailVO.getMoney(),
			PaymentType.indexOf(paymentDetailVO.getPayment_type()), paymentDetailVO.getContract_id(),
			paymentDetailVO.getEmployee_id());
	}

	public Contract getContract(int id) throws NotExistContractException {
		Contract contract = contractDModel.getById(id);
		if (contract == null) {
			throw new NotExistContractException();
		}
		return contract;
	}

	private Insurance getInsurance(ProductVO productVO) throws NotExistException {
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
					VehicleType.indexOf(autoMobileVO.getVehicle_type()), serviceTypeList);
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
				return new Collateral(productVO.getId(), loanType, productVO.getName(), loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(),
					CollateralType.indexOf(collateralVO.getCollateral_type()),
					collateralVO.getMinimum_value(), loanVO.getMonthly_income());
			}
			case InsuranceContract -> {
				InsuranceContractVO insuranceContractVO = insuranceContractMapper.findById_LoanManagement(
						productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 보험 계약 대출 정보"));
				return new InsuranceContract(productVO.getId(), loanType, productVO.getName(),
					loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(),
					insuranceContractVO.getInsurance_id(), loanVO.getMonthly_income());
			}
			case FixedDeposit -> {
				FixedDepositVO fixedDepositVO = fixedDepositMapper.findById_LoanManagement(productVO.getId())
					.orElseThrow(() -> new NotExistException("존재하지 않는 정기 예금 대출 정보"));
				return new FixedDeposit(productVO.getId(), loanType, productVO.getName(), loanVO.getInterest_rate(),
					productVO.getMaximum_money(), loanVO.getMinimum_asset(),
					fixedDepositVO.getMinimum_amount(), loanVO.getMonthly_income());
			}
			default -> throw new NotExistException();
		}
	}

	public Customer getCustomer(int id) throws NotExistException {
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(id)
			.orElseThrow(NotExistException::new);
		Customer customer = new Customer();
		customer.setId(customerVO.getId());
		customer.setBankAccount(customerVO.getBank_account());
		customer.setBankName(customerVO.getBank_name());
		return customer;
	}

	public List<DepositDetail> getAllDepositDetail() {
		List<DepositDetail> result = new ArrayList<>();
		List<DepositDetailVO> depositDetailVOList = depositDetailMapper.getAll_FinancialAccountant();
		for (DepositDetailVO depositDetailVO : depositDetailVOList) {
			result.add(new DepositDetail(depositDetailVO.getId(), depositDetailVO.getDepositor_name(),
				depositDetailVO.getContract_id(), depositDetailVO.getMoney(),
				DepositPath.indexOf(depositDetailVO.getPath())));
		}
		return result;
	}
}
