package com.example.bunsanedthinking_springback.model.loanManagement;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetailList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.CompensationDetailMapper;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceContractMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

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

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
							   CollateralType collateralType, int minimumValue, int monthlyIncome) throws DuplicateLoanException {
		checkLoanName(name);
		ProductVO productVO = createProductVO(name, limit);
		LoanVO loanVO = createLoanVO(productVO.getId(), loanType.ordinal(), minimumAsset, monthlyIncome, interestRate);

		CollateralVO collateralVO = new CollateralVO(productVO.getId(), collateralType.ordinal(), minimumValue);
		productMapper.insert_LoanManagement(productVO);
		loanMapper.insert_LoanManagement(loanVO);
		collateralMapper.insert_LoanManagement(collateralVO);
	}

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			int parameter, int monthlyIncome) throws DuplicateLoanException {
		checkLoanName(name);
		ProductVO productVO = createProductVO(name, limit);
		LoanVO loanVO = createLoanVO(productVO.getId(), loanType.ordinal(), minimumAsset, monthlyIncome, interestRate);

		productMapper.insert_LoanManagement(productVO);
		loanMapper.insert_LoanManagement(loanVO);

		if (loanType == LoanType.FixedDeposit) {
			FixedDepositVO fixedDepositVO = new FixedDepositVO(productVO.getId(), parameter);
			fixedDepositMapper.insert_LoanManagement(fixedDepositVO);

		} else if (loanType == LoanType.InsuranceContract) {
			InsuranceContractVO insuranceContractVO = new InsuranceContractVO(productVO.getId(), parameter);
			insuranceContractMapper.insert_LoanManagement(insuranceContractVO);
		}
	}

	private void checkLoanName(String name) throws DuplicateLoanException {
		for (ProductVO product : productMapper.getAll_LoanManagement()) {
			if (product.getName().equals(name)) {
				throw new DuplicateLoanException();
			}
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

	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
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
					loanVO.getMinimum_asset(), insuranceContractVO.getContract_id());
			}
			default -> throw new NotExistException("대출 상품 종류가 잘못되었습니다.");
		}
	}

	public boolean collectLoanPrincipalInterest() {
		return true;
	}

	public void requestLoan(Contract contract, Customer customer, int money, PaymentType paymentType, boolean result,
							ContractList contractList, PaymentDetailList paymentDetailList, CompensationDetailList compensationDetailList) throws AlreadyProcessedException, NotExistContractException {
		if (contract.getContractStatus() != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}
		contract.review(result, contractList);
		if(!result) {
			return;
		}
		Integer id = paymentDetailMapper.getMaxId_LoanManagement();
		if (id == null) {
			id = Integer.parseInt( "" + PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + 1);
		} else {
			id++;
		}
		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(id, customer.getName(), customer.getBankName(), customer.getBankAccount(), money, paymentType.ordinal(),
			PaymentProcessStatus.Unprocessed.ordinal(), contract.getId(), null);
		paymentDetailMapper.insert_LoanManagement(paymentDetailVO);

		id = compensationDetailMapper.getMaxId_LoanManagement();
		if (id == null) {
			id = 1;
		} else {
			id++;
		}
		CompensationDetailVO compensationDetailVO = new CompensationDetailVO(id, money, LocalDate.now(), contract.getId());
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

	public void updateLoanProduct(int index, String input, Loan loan, ProductList productList)
			throws DuplicateLoanException, NotExistException {
		ProductVO productVO;
		LoanVO loanVO;
		switch (index) {
			case 1 -> {
				for (ProductVO product : productMapper.getAll_LoanManagement()) {
					if (product.getName().equals(input)) {
						throw new DuplicateLoanException();
					}
				}
				productVO = productMapper.findById_LoanManagement(loan.getId())
					.orElseThrow(NotExistException::new);
				productVO.setName(input);
				productMapper.update_LoanManagement(productVO);
			}
			case 2 -> {
				loanVO = loanMapper.findById_LoanManagement(loan.getId())
					.orElseThrow(NotExistException::new);
				loanVO.setInterest_rate(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 3 -> {
				productVO = productMapper.findById_LoanManagement(loan.getId())
					.orElseThrow(NotExistException::new);
				productVO.setMaximum_money(Integer.parseInt(input));
				productMapper.update_LoanManagement(productVO);
			}
			case 4 -> {
				loanVO = loanMapper.findById_LoanManagement(loan.getId())
					.orElseThrow(NotExistException::new);
				loanVO.setMinimum_asset(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 5 -> {
				if (loan instanceof FixedDeposit) {
					FixedDepositVO fixedDepositVO;
					fixedDepositVO = fixedDepositMapper.findById_LoanManagement(loan.getId())
						.orElseThrow(NotExistException::new);
					fixedDepositVO.setMinimum_amount(Integer.parseInt(input));
					fixedDepositMapper.update_LoanManagement(fixedDepositVO);
				} else if (loan instanceof InsuranceContract) {
					InsuranceContractVO insuranceContractVO;
					insuranceContractVO = insuranceContractMapper.findById_LoanManagement(loan.getId())
						.orElseThrow(NotExistException::new);
					insuranceContractVO.setInsurance_id(Integer.parseInt(input));
					insuranceContractMapper.update_LoanManagement(insuranceContractVO);
				} else if (loan instanceof Collateral) {
					CollateralVO collateralVO = collateralMapper.findById_LoanManagement(loan.getId())
						.orElseThrow(NotExistException::new);
					collateralVO.setCollateral_type(Integer.parseInt(input) - 1); // CollateralType의 index+1로 입력받는듯
					collateralMapper.update_LoanManagement(collateralVO);
				}
			}
			case 6 -> {
				if (loan instanceof Collateral) {
					CollateralVO collateralVO = collateralMapper.findById_LoanManagement(loan.getId())
						.orElseThrow(NotExistException::new);
					collateralVO.setMinimum_value(Integer.parseInt(input) - 1); // CollateralType의 index+1로 입력받는듯
					collateralMapper.update_LoanManagement(collateralVO);
				}
			}
			default -> {
				//
			}
		}
	}
	public void deleteLoanProduct(ProductList productList, int id) throws NotExistException {
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
	public ArrayList<Product> getAll(ProductList productList) {
		ArrayList<Product> result = new ArrayList<>();
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
