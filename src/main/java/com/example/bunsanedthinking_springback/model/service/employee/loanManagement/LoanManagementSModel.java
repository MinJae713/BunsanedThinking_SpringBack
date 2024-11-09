package com.example.bunsanedthinking_springback.model.service.employee.loanManagement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.loanManagement.CollateralDTO;
import com.example.bunsanedthinking_springback.dto.loanManagement.LoanDTO;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
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
import com.example.bunsanedthinking_springback.model.domain.insuranceContract.InsuranceContractDModel;
import com.example.bunsanedthinking_springback.model.domain.loan.LoanDModel;
import com.example.bunsanedthinking_springback.model.domain.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.product.ProductDModel;

@Service
public class LoanManagementSModel {
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
	private PaymentDetailDModel paymentDetailDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private CompensationDetailDModel compensationDetailDModel;

	public void addLoanProduct(CollateralDTO collateralDTO) throws DuplicateLoanException {
		checkLoanName(collateralDTO.getName());
		int productId = createProductId();
		Collateral collateral = new Collateral(productId, LoanType.indexOf(collateralDTO.getLoanType()),
			collateralDTO.getName(), collateralDTO.getInterestRate(), collateralDTO.getMaximumMoney(),
			collateralDTO.getMinimumAsset(), CollateralType.indexOf(collateralDTO.getCollateralType()),
			collateralDTO.getMinimumValue(), collateralDTO.getMonthlyIncome());
		collateralDModel.add(collateral);
	}

	public void addLoanProduct(LoanDTO loanDTO) throws DuplicateLoanException {
		checkLoanName(loanDTO.getName());
		int productId = createProductId();

		LoanType loanType = LoanType.indexOf(loanDTO.getLoanType());
		switch (loanType) {
			case FixedDeposit -> {
				FixedDeposit fixedDeposit = new FixedDeposit(productId, loanType, loanDTO.getName(),
					loanDTO.getInterestRate(), loanDTO.getMaximumMoney(), loanDTO.getMinimumAsset(),
					loanDTO.getParameter(), loanDTO.getMonthlyIncome());
				fixedDepositDModel.add(fixedDeposit);
			}
			case InsuranceContract -> {
				InsuranceContract insuranceContract = new InsuranceContract(productId, loanType, loanDTO.getName(),
					loanDTO.getInterestRate(), loanDTO.getMaximumMoney(), loanDTO.getMinimumAsset(),
					loanDTO.getParameter(), loanDTO.getMonthlyIncome());
				insuranceContractDModel.add(insuranceContract);
			}
			default -> {
				//
			}
		}
	}

	private void checkLoanName(String name) throws DuplicateLoanException {
		// TODO SQL 활용한 방법으로 변경 예정
		// Integer isExistName = productMapper.isExistName(name);
		// if (isExistName == 1) {
		// 	throw new DuplicateLoanException();
		// }
		for (Product product : productDModel.getAll()) {
			if (product.getName().equals(name))
				throw new DuplicateLoanException();
		}
	}

	private int createProductId() {
		Integer maxId = loanDModel.getMaxId();
		int id;
		if (maxId == null) {
			id = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int loanSerialLength = ("" + Loan.LOAN_SERIAL_NUMBER).length();
			String index = (maxId + "").substring(productSerialLength + loanSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + index;
			id = Integer.parseInt(compound);
		}
		return id;
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

		if (contract.getContractStatus() != ContractStatus.ContractRequesting)
			throw new AlreadyProcessedException();

		Product product = contract.getProduct();
		if (result) {
			LocalDate currentDate = LocalDate.now();
			if (product instanceof Insurance insurance) {
				LocalDate expirationDate = currentDate.plusYears(insurance.getContractPeriod());
				contract.setExpirationDate(Date.valueOf(expirationDate));
			}
			contract.setDate(Date.valueOf(currentDate));
			contract.setContractStatus(ContractStatus.Maintaining);
		} else {
			contract.setContractStatus(ContractStatus.Terminating);
			contractDModel.update(contract);
			return;
		}
		contractDModel.update(contract);

		Customer customer = customerDModel.getById(contract.getCustomerID());
		if (customer == null) {
			throw new NotExistContractException();
		}

		PaymentDetail paymentDetail = createPaymentDetail(customer, money, PaymentType.indexOf(paymentType),
			contractId);
		paymentDetailDModel.add(paymentDetail);

		CompensationDetail compensationDetail = createCompensationDetail(contractId, money);
		compensationDetailDModel.add(compensationDetail);
	}

	private PaymentDetail createPaymentDetail(Customer customer, int money, PaymentType paymentType, int contractId) {
		Integer paymentMaxId = paymentDetailDModel.getMaxId();
		int paymentId;
		if (paymentMaxId == null) {
			paymentId = Integer.parseInt("" + PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + 1);
		} else {
			String index = (paymentMaxId + "").substring((PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "").length());
			paymentId = Integer.parseInt(
				(PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		return new PaymentDetail(paymentId, PaymentProcessStatus.Unprocessed, customer.getName(),
			customer.getBankName(), customer.getBankAccount(), money, paymentType, contractId, null);
	}

	private CompensationDetail createCompensationDetail(int contractId, int money) {
		Integer compensationMaxId = compensationDetailDModel.getMaxId();
		int compensationId;
		if (compensationMaxId == null) {
			compensationId = 1;
		} else {
			compensationId = compensationMaxId + 1;
		}
		return new CompensationDetail(contractId, compensationId, money, Date.valueOf(LocalDate.now()));
	}

	public void updateLoanProduct(int index, String input, int loanId)
		throws DuplicateLoanException, NotExistException {
		Loan loan = loanDModel.getById(loanId);
		if (loan == null)
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
		switch (index) {
			case 1 -> {
				checkLoanName(input);
				loan.setName(input);
			}
			case 2 -> loan.setInterestRate(Integer.parseInt(input));
			case 3 -> loan.setMaximumMoney(Integer.parseInt(input));
			case 4 -> loan.setMinimumAsset(Integer.parseInt(input));
			case 5 -> {
				switch (loan.getLoanType()) {
					case FixedDeposit -> {
						FixedDeposit fixedDeposit = (FixedDeposit)loan;
						fixedDeposit.setMinimumAmount(Integer.parseInt(input));
						fixedDepositDModel.update(fixedDeposit);
						return;
					}
					case InsuranceContract -> {
						InsuranceContract insuranceContract = (InsuranceContract)loan;
						insuranceContract.setInsuranceId(Integer.parseInt(input));
						insuranceContractDModel.update(insuranceContract);
						return;
					}
					case Collateral -> {
						Collateral collateral = (Collateral)loan;
						collateral.setCollateralType(CollateralType.indexOf(Integer.parseInt(input)));
						collateralDModel.update(collateral);
						return;
					}
				}
			}
			case 6 -> {
				Collateral collateral = (Collateral)loan;
				collateral.setMinimumValue(Integer.parseInt(input));
				collateralDModel.update(collateral);
				return;
			}
			default -> throw new IllegalArgumentException("잘못된 index가 입력되었습니다.");
		}
		loanDModel.update(loan);
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
	}

	public List<Loan> getAll() {
		return loanDModel.getAll();
	}

	public double getOutcome(int contractId) throws NotExistContractException, NotExistException {
		Contract contract = contractDModel.getById(contractId);
		if (contract == null)
			throw new NotExistContractException();
		if (contract.getProduct() instanceof Loan loan) {
			return getLoanOutcome(contractId, loan);
		} else if (contract.getProduct() instanceof Insurance insurance) {
			return insurance.getMonthlyPremium();
		}
		return 0;
	}

	private double getLoanOutcome(int contractId, Loan loan) {
		List<CompensationDetail> compensationDetailList = compensationDetailDModel.getAll();
		compensationDetailList = compensationDetailList.stream()
			.filter(e -> e.getContractId() == contractId)
			.collect(Collectors.toList());

		// compensationDetailVOList.size() -> 대출은 1번만 지급해주기 때문에 대부분의 상황에선 1임
		int totalMoney = 0;
		for (CompensationDetail compensationDetail : compensationDetailList) {
			totalMoney += compensationDetail.getMoney();
		}
		return (double)(totalMoney * loan.getInterestRate()) / 100;
	}
}
