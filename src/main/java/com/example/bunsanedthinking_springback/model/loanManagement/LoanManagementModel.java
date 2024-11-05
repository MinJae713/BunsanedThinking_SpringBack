package com.example.bunsanedthinking_springback.model.loanManagement;

import com.example.bunsanedthinking_springback.dto.chan.CollateralDTO;
import com.example.bunsanedthinking_springback.dto.chan.LoanDTO;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanManagementModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private CollateralMapper collateralMapper;
	@Autowired
	private FixedDepositMapper fixedDepositMapper;
	@Autowired
	private InsuranceContractMapper insuranceContractMapper;
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private CompensationDetailMapper compensationDetailMapper;
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private CustomerMapper customerMapper;

	public void addLoanProduct(CollateralDTO collateralDTO) throws DuplicateLoanException {
		checkLoanName(collateralDTO.getName());
		ProductVO productVO = createProductVO(collateralDTO.getName(), collateralDTO.getMaximumMoney());
		LoanVO loanVO = createLoanVO(productVO.getId(), LoanType.indexOf(collateralDTO.getLoanType()).ordinal(),
			collateralDTO.getMinimumAsset(), collateralDTO.getMonthlyPremium(), collateralDTO.getInterestRate());

		CollateralVO collateralVO = new CollateralVO(productVO.getId(),
			CollateralType.indexOf(collateralDTO.getCollateralType()).ordinal(), collateralDTO.getMinimumValue());
		productMapper.insert_LoanManagement(productVO);
		loanMapper.insert_LoanManagement(loanVO);
		collateralMapper.insert_LoanManagement(collateralVO);
	}

	public void addLoanProduct(LoanDTO loanDTO) throws DuplicateLoanException {
		checkLoanName(loanDTO.getName());
		ProductVO productVO = createProductVO(loanDTO.getName(), loanDTO.getMaximumMoney());
		LoanVO loanVO = createLoanVO(productVO.getId(), LoanType.indexOf(loanDTO.getLoanType()).ordinal(),
			loanDTO.getMinimumAsset(), loanDTO.getMonthlyPremium(), loanDTO.getInterestRate());

		productMapper.insert_LoanManagement(productVO);
		loanMapper.insert_LoanManagement(loanVO);

		if (loanDTO.getLoanType() == LoanType.FixedDeposit.ordinal()) {
			FixedDepositVO fixedDepositVO = new FixedDepositVO(productVO.getId(), loanDTO.getParameter());
			fixedDepositMapper.insert_LoanManagement(fixedDepositVO);

		} else if (loanDTO.getLoanType() == LoanType.InsuranceContract.ordinal()) {
			InsuranceContractVO insuranceContractVO = new InsuranceContractVO(productVO.getId(), loanDTO.getParameter());
			insuranceContractMapper.insert_LoanManagement(insuranceContractVO);
		}
	}

	private void checkLoanName(String name) throws DuplicateLoanException {
		Integer isExistName = productMapper.isExistName(name);
		if (isExistName == 1) {
			throw new DuplicateLoanException();
		}
	}

	private ProductVO createProductVO(String name, int limit) {
		Integer productId = loanMapper.getMaxId_LoanManagement();
		if (productId == null) {
			productId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + 1);
		} else {
			productId++;
		}
		return new ProductVO(productId, name, limit);
	}

	private LoanVO createLoanVO(int id, int ordinal, int minimumAsset, int monthlyIncome, int interestRate) {
		return new LoanVO(id, ordinal, minimumAsset, monthlyIncome, interestRate);
	}

	public Loan getLoanProduct(int id) throws NotExistException {
		Optional<LoanVO> optionalLoanVO = loanMapper.findById_LoanManagement(id);
		LoanVO loanVO = optionalLoanVO.orElseThrow(() -> new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다."));
		Optional<ProductVO> optionalProductVO = productMapper.findById_LoanManagement(id);
		ProductVO productVO = optionalProductVO.orElseThrow(() -> new NotExistException("해당하는 상품 정보가 존재하지 않습니다."));

		LoanType loanType = LoanType.indexOf(loanVO.getLoan_type());
		switch (loanType) {
			case Collateral -> {
				Optional<CollateralVO> optionalCollateralVO = collateralMapper.findById_LoanManagement(id);
				CollateralVO collateralVO = optionalCollateralVO.orElseThrow(() -> new NotExistException("해당하는 담보 대출 정보가 존재하지 않습니다."));
				return new Collateral(loanType, productVO.getName(), loanVO.getInterest_rate(), productVO.getMaximum_money(),
					loanVO.getMinimum_asset(), CollateralType.indexOf(collateralVO.getCollateral_type()), collateralVO.getMinimum_value());
			}
			case FixedDeposit -> {
				Optional<FixedDepositVO> optionalFixedDepositVO = fixedDepositMapper.findById_LoanManagement(id);
				FixedDepositVO fixedDepositVO = optionalFixedDepositVO.orElseThrow(() -> new NotExistException("해당하는 정기 예금 대출 정보가 존재하지 않습니다."));
				return new FixedDeposit(loanType, productVO.getName(), loanVO.getInterest_rate(), productVO.getMaximum_money(),
					loanVO.getMinimum_asset(), fixedDepositVO.getMinimum_amount());
			}
			case InsuranceContract -> {
				Optional<InsuranceContractVO> optionalInsuranceContractVO = insuranceContractMapper.findById_LoanManagement(id);
				InsuranceContractVO insuranceContractVO = optionalInsuranceContractVO.orElseThrow(() -> new NotExistException("해당하는 보험 계약 대출 정보가 존재하지 않습니다."));
				return new InsuranceContract(loanType, productVO.getName(), loanVO.getInterest_rate(), productVO.getMaximum_money(),
					loanVO.getMinimum_asset(), insuranceContractVO.getProduct_id());
			}
			default -> throw new NotExistException("대출 상품 종류가 잘못되었습니다.");
		}
	}

	public boolean collectLoanPrincipalInterest() {
		return true;
	}

	public void requestLoan(int contractId, int money, int paymentType,
			boolean result) throws AlreadyProcessedException, NotExistContractException {
		ContractVO contractVO = contractMapper.findById_FinancialAccountant(contractId)
			.orElseThrow(NotExistContractException::new);

		if (contractVO.getContract_status() != ContractStatus.ContractRequesting.ordinal()) {
			throw new AlreadyProcessedException();
		}

		int productSerialLength = (Product.PRODUCT_SERIAL_NUMBER + "").length();
		int insuranceSerialLength = (Insurance.INSURANCE_SERIAL_NUMBER + "").length();
		String productType = (contractVO.getProduct_id() + "").substring(productSerialLength, productSerialLength + insuranceSerialLength);

		if (result) {
			LocalDate currentDate = LocalDate.now();
			if (productType.equals(Insurance.INSURANCE_SERIAL_NUMBER + "")) {
				InsuranceVO insuranceVO = insuranceMapper.findById_FinancialAccountant(contractVO.getProduct_id())
					.orElseThrow(NotExistContractException::new);
				LocalDate expirationDate = currentDate.plusYears(insuranceVO.getContract_period());
				contractVO.setExpiration_date(expirationDate);
			}
			contractVO.setDate(currentDate);
			contractVO.setContract_status(ContractStatus.Maintaining.ordinal());
		} else {
			contractVO.setContract_status(ContractStatus.Terminating.ordinal());
			contractMapper.update(contractVO);
			return;
		}
		contractMapper.update(contractVO);

		Integer id = paymentDetailMapper.getMaxId_LoanManagement();
		if (id == null) {
			id = Integer.parseInt( "" + PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + 1);
		} else {
			id++;
		}
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(contractVO.getCustomer_id())
			.orElseThrow(NotExistContractException::new);
		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(id, customerVO.getName(), customerVO.getBank_name(),
			customerVO.getBank_account(), money, paymentType,
			PaymentProcessStatus.Unprocessed.ordinal(), contractId, null);
		paymentDetailMapper.insert_LoanManagement(paymentDetailVO);

		id = compensationDetailMapper.getMaxId_LoanManagement();
		if (id == null) {
			id = 1;
		} else {
			id++;
		}
		CompensationDetailVO compensationDetailVO = new CompensationDetailVO(id, money, LocalDate.now(), contractId);
		compensationDetailMapper.insert_LoanManagement(compensationDetailVO);
	}

	// public void updateLoanProduct(int index, String input, Collateral collateralLoan, ProductList productList)
	// 		throws DuplicateLoanException, NotExistException {
	// 	ProductVO productVO;
	// 	LoanVO loanVO;
	// 	CollateralVO collateralVO;
	// 	switch (index) {
	// 	case 1:
	// 		for (ProductVO product : productMapper.getAll_LoanManagement()) {
	// 			if (product.getName().equals(input)) {
	// 				throw new DuplicateLoanException();
	// 			}
	// 		}
	// 		productVO = productMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		productVO.setName(input);
	// 		productMapper.update_LoanManagement(productVO);
	// 		break;
	// 	case 2:
	// 		loanVO = loanMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		loanVO.setInterest_rate(Integer.parseInt(input));
	// 		loanMapper.update_LoanManagement(loanVO);
	// 		break;
	// 	case 3:
	// 		productVO = productMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		productVO.setMaximum_money(Integer.parseInt(input));
	// 		productMapper.update_LoanManagement(productVO);
	// 		break;
	// 	case 4:
	// 		loanVO = loanMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		loanVO.setMinimum_asset(Integer.parseInt(input));
	// 		loanMapper.update_LoanManagement(loanVO);
	// 		break;
	// 	case 5:
	// 		collateralVO = collateralMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		collateralVO.setCollateral_type(Integer.parseInt(input) - 1); // CollateralType의 index+1로 입력받는듯
	// 		collateralMapper.update_LoanManagement(collateralVO);
	// 		break;
	// 	case 6:
	// 		collateralVO = collateralMapper.findById_LoanManagement(collateralLoan.getId())
	// 			.orElseThrow(NotExistException::new);
	// 		collateralVO.setMinimum_value(Integer.parseInt(input) - 1); // CollateralType의 index+1로 입력받는듯
	// 		collateralMapper.update_LoanManagement(collateralVO);
	// 		break;
	// 	default:
	// 		break;
	// 	}
	// }

	public void updateLoanProduct(int index, String input, int loanId)
			throws DuplicateLoanException, NotExistException {
		ProductVO productVO;
		LoanVO loanVO;
		switch (index) {
			case 1 -> {
				Integer isExistName = productMapper.isExistName(input);
				if (isExistName == 1) {
					throw new DuplicateLoanException();
				}
				productVO = productMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				if (loanMapper.findById_LoanManagement(loanId).isEmpty()) {
					throw new NotExistException();
				}
				productVO.setName(input);
				productMapper.update_LoanManagement(productVO);
			}
			case 2 -> {
				loanVO = loanMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				loanVO.setInterest_rate(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 3 -> {
				productVO = productMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				if (loanMapper.findById_LoanManagement(loanId).isEmpty()) {
					throw new NotExistException();
				}
				productVO.setMaximum_money(Integer.parseInt(input));
				productMapper.update_LoanManagement(productVO);
			}
			case 4 -> {
				loanVO = loanMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				loanVO.setMinimum_asset(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 5 -> {
				loanVO = loanMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				if (loanVO.getLoan_type() ==  LoanType.FixedDeposit.ordinal()) {
					FixedDepositVO fixedDepositVO;
					fixedDepositVO = fixedDepositMapper.findById_LoanManagement(loanId)
						.orElseThrow(NotExistException::new);
					fixedDepositVO.setMinimum_amount(Integer.parseInt(input));
					fixedDepositMapper.update_LoanManagement(fixedDepositVO);
				} else if (loanVO.getLoan_type() == LoanType.InsuranceContract.ordinal()) {
					InsuranceContractVO insuranceContractVO;
					insuranceContractVO = insuranceContractMapper.findById_LoanManagement(loanId)
						.orElseThrow(NotExistException::new);
					insuranceContractVO.setInsurance_id(Integer.parseInt(input));
					insuranceContractMapper.update_LoanManagement(insuranceContractVO);
				} else if (loanVO.getLoan_type() == LoanType.Collateral.ordinal()) {
					CollateralVO collateralVO = collateralMapper.findById_LoanManagement(loanId)
						.orElseThrow(NotExistException::new);
					collateralVO.setCollateral_type(Integer.parseInt(input));
					collateralMapper.update_LoanManagement(collateralVO);
				}
			}
			case 6 -> {
				CollateralVO collateralVO = collateralMapper.findById_LoanManagement(loanId)
					.orElseThrow(NotExistException::new);
				collateralVO.setMinimum_value(Integer.parseInt(input));
				collateralMapper.update_LoanManagement(collateralVO);
			}
			default -> {
				//
			}
		}
	}
	public void deleteLoanProduct(int id) throws NotExistException {
		LoanVO loanVO = loanMapper.findById_LoanManagement(id)
			.orElseThrow(NotExistException::new);
		switch (LoanType.indexOf(loanVO.getLoan_type())) {
			case Collateral -> collateralMapper.delete_LoanManagement(id);
			case FixedDeposit -> fixedDepositMapper.delete_LoanManagement(id);
			case InsuranceContract -> insuranceContractMapper.delete_LoanManagement(id);
		}
		loanMapper.delete_LoanManagement(id);
		productMapper.delete_LoanManagement(id);
	}
	public List<Product> getAll() {
		List<Product> result = new ArrayList<>();
		List<ProductVO> productVOList = productMapper.getAll_LoanManagement();
		for (ProductVO productVO : productVOList) {
			Optional<LoanVO> optionalLoanVO = loanMapper.findById_LoanManagement(productVO.getId());
			if (optionalLoanVO.isEmpty())
				continue;

			LoanVO loanVO = optionalLoanVO.get();
			LoanType loanType = LoanType.indexOf(loanVO.getLoan_type());
			switch (loanType) {
				case InsuranceContract -> {
					Optional<InsuranceContractVO> optionalInsuranceContractVO = insuranceContractMapper.findById_LoanManagement(productVO.getId());
					if (optionalInsuranceContractVO.isEmpty())
						continue;

					InsuranceContractVO insuranceContractVO = optionalInsuranceContractVO.get();
					result.add(new InsuranceContract(loanType, productVO.getName(),
						loanVO.getInterest_rate(), productVO.getMaximum_money(), loanVO.getMinimum_asset(), insuranceContractVO.getInsurance_id()));
				}
				case Collateral -> {
					Optional<CollateralVO> optionalCollateralVO = collateralMapper.findById_LoanManagement(productVO.getId());
					if (optionalCollateralVO.isEmpty())
						continue;

					CollateralVO collateralVO = optionalCollateralVO.get();
					result.add(new Collateral(loanType, productVO.getName(), loanVO.getInterest_rate(), productVO.getMaximum_money(),
						loanVO.getMinimum_asset(), CollateralType.indexOf(collateralVO.getCollateral_type()), collateralVO.getMinimum_value()));
				}
				case FixedDeposit -> {
					Optional<FixedDepositVO> optionalFixedDepositVO = fixedDepositMapper.findById_LoanManagement(productVO.getId());
					if (optionalFixedDepositVO.isEmpty())
						continue;

					FixedDepositVO fixedDepositVO = optionalFixedDepositVO.get();
					result.add(new FixedDeposit(loanType, productVO.getName(), loanVO.getInterest_rate(),
						productVO.getMaximum_money(), loanVO.getMinimum_asset(), fixedDepositVO.getMinimum_amount()));
				}
			}
		}
		return result;
	}
}
