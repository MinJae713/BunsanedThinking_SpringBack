package com.example.bunsanedthinking_springback.model.service.employee.loanManagement;

import com.example.bunsanedthinking_springback.dto.loanManagement.CollateralDTO;
import com.example.bunsanedthinking_springback.dto.loanManagement.LoanDTO;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.domain.collateral.CollateralDModel;
import com.example.bunsanedthinking_springback.model.domain.compensationDetail.CompensationDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.domain.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.domain.fixedDeposit.FixedDepositDModel;
import com.example.bunsanedthinking_springback.model.domain.insurance.InsuranceDModel;
import com.example.bunsanedthinking_springback.model.domain.insuranceContract.InsuranceContractDModel;
import com.example.bunsanedthinking_springback.model.domain.loan.LoanDModel;
import com.example.bunsanedthinking_springback.model.domain.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.product.ProductDModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanManagementSModel {
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
	private CompensationDetailMapper compensationDetailMapper;
	@Autowired
	private ProductDModel productDModel;
	@Autowired
	private LoanDModel loanDModel;
	@Autowired
	private CollateralDModel collateralDModel;
	@Autowired
	private FixedDepositDModel fixedDepositDModel;
	@Autowired
	private InsuranceContractDModel insuranceContractDModel;
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private InsuranceDModel insuranceDModel;
	@Autowired
	private PaymentDetailDModel paymentDetailDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private CompensationDetailDModel compensationDetailDModel;

	public void addLoanProduct(CollateralDTO collateralDTO) throws DuplicateLoanException {
		checkLoanName(collateralDTO.getName());
		ProductVO productVO = createProductVO(collateralDTO.getName(), collateralDTO.getMaximumMoney());
		LoanVO loanVO = createLoanVO(productVO.getId(), LoanType.indexOf(collateralDTO.getLoanType()).ordinal(),
			collateralDTO.getMinimumAsset(), collateralDTO.getMonthlyPremium(), collateralDTO.getInterestRate());

		CollateralVO collateralVO = new CollateralVO(productVO.getId(),
			CollateralType.indexOf(collateralDTO.getCollateralType()).ordinal(), collateralDTO.getMinimumValue());
//		productDModel.add(productVO);
//		loanDModel.add(loanVO);
//		collateralDModel.add(collateralVO);
	}

	public void addLoanProduct(LoanDTO loanDTO) throws DuplicateLoanException {
		checkLoanName(loanDTO.getName());
		ProductVO productVO = createProductVO(loanDTO.getName(), loanDTO.getMaximumMoney());
		LoanVO loanVO = createLoanVO(productVO.getId(), LoanType.indexOf(loanDTO.getLoanType()).ordinal(),
			loanDTO.getMinimumAsset(), loanDTO.getMonthlyPremium(), loanDTO.getInterestRate());

//		productDModel.add(productVO);
//		loanDModel.add(loanVO);

		if (loanDTO.getLoanType() == LoanType.FixedDeposit.ordinal()) {
			FixedDepositVO fixedDepositVO = new FixedDepositVO(productVO.getId(), loanDTO.getParameter());
//			fixedDepositDModel.add(fixedDepositVO);

		} else if (loanDTO.getLoanType() == LoanType.InsuranceContract.ordinal()) {
			InsuranceContractVO insuranceContractVO = new InsuranceContractVO(productVO.getId(),
				loanDTO.getParameter());
//			insuranceContractDModel.add(insuranceContractVO);
		}
	}

	private void checkLoanName(String name) throws DuplicateLoanException {
		// Integer isExistName = productMapper.isExistName(name);
		// if (isExistName == 1) {
		// 	throw new DuplicateLoanException();
		// }
		for (Product product : productDModel.getAll()) {
			if (product.getName().equals(name))
				throw new DuplicateLoanException();
		}
	}

	private ProductVO createProductVO(String name, int limit) {
		Integer productId = loanDModel.getMaxId();
		if (productId == null) {
			productId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int loanSerialLength = ("" + Loan.LOAN_SERIAL_NUMBER).length();
			String index = (productId + "").substring(productSerialLength + loanSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + index;
			productId = Integer.parseInt(compound);
		}
		return new ProductVO(productId, name, limit);
	}

	private LoanVO createLoanVO(int id, int ordinal, int minimumAsset, int monthlyIncome, int interestRate) {
		return new LoanVO(id, ordinal, minimumAsset, monthlyIncome, interestRate);
	}

	public Loan getLoanProduct(int id) throws NotExistException {
		Loan loan = loanDModel.getById(id);
		if (loan == null)
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
		return loan;
	}

	public boolean collectLoanPrincipalInterest() {
		return true;
	}

	public void requestLoan(int contractId, int money, int paymentType,
		boolean result) throws AlreadyProcessedException, NotExistContractException {
		Contract contract = contractDModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		ContractVO contractVO = ContractVO.from(contract);

		if (contract.getContractStatus() != ContractStatus.ContractRequesting)
			throw new AlreadyProcessedException();

		int productSerialLength = (Product.PRODUCT_SERIAL_NUMBER + "").length();
		int insuranceSerialLength = (Insurance.INSURANCE_SERIAL_NUMBER + "").length();
		String productType = (contract.getProduct().getId() + "").substring(productSerialLength,
			productSerialLength + insuranceSerialLength);

		if (result) {
			LocalDate currentDate = LocalDate.now();
			if (productType.equals(Insurance.INSURANCE_SERIAL_NUMBER + "")) {
				Insurance insurance = insuranceDModel.getById(contractVO.getProduct_id());
				if (insurance == null)
					throw new NotExistContractException();
				LocalDate expirationDate = currentDate.plusYears(insurance.getContractPeriod());
				contractVO.setExpiration_date(expirationDate);
			}
			contractVO.setDate(currentDate);
			contractVO.setContract_status(ContractStatus.Maintaining.ordinal());
		} else {
			contractVO.setContract_status(ContractStatus.Terminating.ordinal());
//			contractDModel.update(contractVO);
			return;
		}
//		contractDModel.update(contractVO);

		Integer id = paymentDetailDModel.getMaxId();
		if (id == null) {
			id = Integer.parseInt("" + PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + 1);
		} else {
			String index = (id + "").substring((PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		Customer customer = customerDModel.getById(contractVO.getCustomer_id());
		if (customer == null) {
			throw new NotExistContractException();
		}

		PaymentDetailVO paymentDetailVO = new PaymentDetailVO(id, customer.getName(),
			customer.getBankName(), customer.getBankAccount(), money, paymentType,
			PaymentProcessStatus.Unprocessed.ordinal(), contractId, null);
//		paymentDetailDModel.add(paymentDetailVO);

		id = compensationDetailDModel.getMaxId();
		if (id == null) {
			id = 1;
		} else {
			id++;
		}
		CompensationDetailVO compensationDetailVO = new CompensationDetailVO(id, money, LocalDate.now(), contractId);
//		compensationDetailDModel.add(compensationDetailVO);
	}

	public void updateLoanProduct(int index, String input, int loanId)
		throws DuplicateLoanException, NotExistException {
		Product product;
		Loan loan;
		ProductVO productVO;
		LoanVO loanVO;
		switch (index) {
			case 1 -> {
				checkLoanName(input);
				product = productDModel.getById(loanId);
				if (product == null)
					throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
				productVO = ProductVO.from(product);
				if (loanDModel.getById(loanId) == null)
					throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
				productVO.setName(input);
				productMapper.update_LoanManagement(productVO);
			}
			case 2 -> {
				loan = loanDModel.getById(loanId);
				if (loan == null)
					throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
				loanVO = LoanVO.from(loan);
				loanVO.setInterest_rate(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 3 -> {
				product = productDModel.getById(loanId);
				if (product == null)
					throw new NotExistException("해당하는 상품 정보가 존재하지 않습니다.");
				productVO = ProductVO.from(product);
				if (loanDModel.getById(loanId) == null)
					throw new NotExistException();
				productVO.setMaximum_money(Integer.parseInt(input));
				productMapper.update_LoanManagement(productVO);
			}
			case 4 -> {
				loan = loanDModel.getById(loanId);
				if (loan == null)
					throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
				loanVO = LoanVO.from(loan);
				loanVO.setMinimum_asset(Integer.parseInt(input));
				loanMapper.update_LoanManagement(loanVO);
			}
			case 5 -> {
				loan = loanDModel.getById(loanId);
				if (loan == null)
					throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
				switch (loan.getLoanType()) {
					case FixedDeposit -> {
						FixedDeposit fixedDeposit = fixedDepositDModel.getById(loanId);
						if (fixedDeposit == null)
							throw new NotExistException("해당하는 담보 대출 상품 정보가 존재하지 않습니다.");
						FixedDepositVO fixedDepositVO = FixedDepositVO.from(fixedDeposit);
						fixedDepositVO.setMinimum_amount(Integer.parseInt(input));
						fixedDepositMapper.update_LoanManagement(fixedDepositVO);
					}
					case InsuranceContract -> {
						InsuranceContract insuranceContract = insuranceContractDModel.getById(loanId);
						if (insuranceContract == null)
							throw new NotExistException("해당하는 보험 계약 대출 상품 정보가 존재하지 않습니다.");
						InsuranceContractVO insuranceContractVO = InsuranceContractVO.from(insuranceContract);
						insuranceContractVO.setInsurance_id(Integer.parseInt(input));
						insuranceContractMapper.update_LoanManagement(insuranceContractVO);
					}
					case Collateral -> {
						Collateral collateral = collateralDModel.getById(loanId);
						if (collateral == null)
							throw new NotExistException("해당하는 담보 대출 상품 정보가 존재하지 않습니다.");
						CollateralVO collateralVO = CollateralVO.from(collateral);
						collateralVO.setCollateral_type(Integer.parseInt(input));
						collateralMapper.update_LoanManagement(collateralVO);
					}
				}
			}
			case 6 -> {
				Collateral collateral = collateralDModel.getById(loanId);
				if (collateral == null)
					throw new NotExistException("해당하는 담보 대출 상품 정보가 존재하지 않습니다.");
				CollateralVO collateralVO = CollateralVO.from(collateral);
				collateralVO.setMinimum_value(Integer.parseInt(input));
				collateralMapper.update_LoanManagement(collateralVO);
			}
			default -> {
				throw new IllegalArgumentException("잘못된 index가 입력되었습니다.");
			}
		}
	}

	public void deleteLoanProduct(int id) throws NotExistException {
		Loan loan = loanDModel.getById(id);
		if (loan == null)
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
		switch (loan.getLoanType()) {
			case Collateral -> collateralDModel.delete(id);
			case FixedDeposit -> fixedDepositDModel.delete(id);
			case InsuranceContract -> insuranceContractDModel.delete(id);
		}
		loanDModel.delete(id);
		productDModel.delete(id);
	}

	public List<Product> getAll() {
		String loanTypeSerial = "" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER;
		return productDModel.getAll()
			.stream()
			.filter(product -> ("" + product.getId()).startsWith(loanTypeSerial))
			.collect(Collectors.toList());
	}

	public double getOutcome(int contractId) throws NotExistContractException, NotExistException {
		Contract contract = contractDModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		// int productSerialLength = (Product.PRODUCT_SERIAL_NUMBER + "").length();
		// int insuranceSerialLength = (Insurance.INSURANCE_SERIAL_NUMBER + "").length();
		// String productType = (contractVO.getProduct_id() + "").substring(
		// 	productSerialLength, productSerialLength + insuranceSerialLength);
		// if (productType.equals(Insurance.INSURANCE_SERIAL_NUMBER + "")) {
		// 	InsuranceVO insuranceVO = insuranceMapper.findById_FinancialAccountant(contractVO.getProduct_id())
		// 		.orElseThrow(() -> new NotExistException("해당하는 상품 정보가 존재하지 않습니다."));
		// 	return insuranceVO.getMonthly_premium();
		// } else if (productType.equals(Loan.LOAN_SERIAL_NUMBER + "")) {
		// 	return getLoanOutcome(contractVO);
		// }
		if (contract.getProduct() instanceof Loan loan) {
			return getLoanOutcome(contractId, loan);
		} else if (contract.getProduct() instanceof Insurance insurance) {
			return insurance.getMonthlyPremium();
		}
		return 0;
	}

	private double getLoanOutcome(int contractId, Loan loan) throws NotExistException {
		List<CompensationDetailVO> compensationDetailVOList =
			compensationDetailMapper.getAllCompensationByContractId_Customer(contractId);
		if (compensationDetailVOList.size() == 1) {
			CompensationDetailVO compensationDetailVO = compensationDetailVOList.get(0);
			return (double)(compensationDetailVO.getMoney() * loan.getInterestRate()) / 100;
		}
		// compensationDetailVOList.size() > 1인 경우 -> 대출은 1번만 지급해주기 때문에 대부분의 상황에선 X
		int totalMoney = 0;
		for (CompensationDetailVO compensationDetailVO : compensationDetailVOList) {
			totalMoney += compensationDetailVO.getMoney();
		}
		return (double)(totalMoney * loan.getInterestRate()) / 100;
	}
}
