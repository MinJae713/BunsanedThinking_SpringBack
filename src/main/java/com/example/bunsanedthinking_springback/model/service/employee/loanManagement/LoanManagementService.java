package com.example.bunsanedthinking_springback.model.service.employee.loanManagement;

import com.example.bunsanedthinking_springback.dto.employee.loanManagement.CollateralDTO;
import com.example.bunsanedthinking_springback.dto.employee.loanManagement.LoanDTO;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentProcessStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.collateral.CollateralEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.compensationDetail.CompensationDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit.FixedDepositEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceContract.InsuranceContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanManagementService {
	@Autowired
	private ProductEntityModel productEntityModel;
	@Autowired
	private LoanEntityModel loanEntityModel;
	@Autowired
	private CollateralEntityModel collateralEntityModel;
	@Autowired
	private FixedDepositEntityModel fixedDepositEntityModel;
	@Autowired
	private InsuranceContractEntityModel insuranceContractEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private CompensationDetailEntityModel compensationDetailEntityModel;

	public void addLoanProduct(CollateralDTO collateralDTO) throws DuplicateLoanException {
		checkLoanName(collateralDTO.getName());
		int productId = createProductId();
		Collateral collateral = new Collateral(productId, LoanType.indexOf(collateralDTO.getLoanType()),
			collateralDTO.getName(), collateralDTO.getInterestRate(), collateralDTO.getMaximumMoney(),
			collateralDTO.getMinimumAsset(), CollateralType.indexOf(collateralDTO.getCollateralType()),
			collateralDTO.getMinimumValue(), collateralDTO.getMonthlyIncome());
		collateralEntityModel.add(collateral);
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
				fixedDepositEntityModel.add(fixedDeposit);
			}
			case InsuranceContract -> {
				InsuranceContract insuranceContract = new InsuranceContract(productId, loanType, loanDTO.getName(),
					loanDTO.getInterestRate(), loanDTO.getMaximumMoney(), loanDTO.getMinimumAsset(),
					loanDTO.getParameter(), loanDTO.getMonthlyIncome());
				insuranceContractEntityModel.add(insuranceContract);
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
		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(name))
				throw new DuplicateLoanException();
		}
	}

	private int createProductId() {
		Integer maxId = loanEntityModel.getMaxId();
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
		Loan loan = loanEntityModel.getById(id);
		if (loan == null)
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
		return loan;
	}

	public boolean collectLoanPrincipalInterest() {
		return true;
	}

	public void requestLoan(int contractId, int money, int paymentType,
		boolean result) throws AlreadyProcessedException, NotExistContractException {
		Contract contract = contractEntityModel.getById(contractId);
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
			contractEntityModel.update(contract);
			return;
		}
		contractEntityModel.update(contract);

		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		if (customer == null) {
			throw new NotExistContractException();
		}

		PaymentDetail paymentDetail = createPaymentDetail(customer, money, PaymentType.indexOf(paymentType),
			contractId);
		paymentDetailEntityModel.add(paymentDetail);

		CompensationDetail compensationDetail = createCompensationDetail(contractId, money);
		compensationDetailEntityModel.add(compensationDetail);
	}

	private PaymentDetail createPaymentDetail(Customer customer, int money, PaymentType paymentType, int contractId) {
		Integer paymentMaxId = paymentDetailEntityModel.getMaxId();
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
		Integer compensationMaxId = compensationDetailEntityModel.getMaxId();
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
		Loan loan = loanEntityModel.getById(loanId);
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
						fixedDepositEntityModel.update(fixedDeposit);
						return;
					}
					case InsuranceContract -> {
						InsuranceContract insuranceContract = (InsuranceContract)loan;
						insuranceContract.setInsuranceId(Integer.parseInt(input));
						insuranceContractEntityModel.update(insuranceContract);
						return;
					}
					case Collateral -> {
						Collateral collateral = (Collateral)loan;
						collateral.setCollateralType(CollateralType.indexOf(Integer.parseInt(input)));
						collateralEntityModel.update(collateral);
						return;
					}
				}
			}
			case 6 -> {
				Collateral collateral = (Collateral)loan;
				collateral.setMinimumValue(Integer.parseInt(input));
				collateralEntityModel.update(collateral);
				return;
			}
			default -> throw new IllegalArgumentException("잘못된 index가 입력되었습니다.");
		}
		loanEntityModel.update(loan);
	}

	public void deleteLoanProduct(int id) throws NotExistException {
		Loan loan = loanEntityModel.getById(id);
		if (loan == null)
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
		switch (loan.getLoanType()) {
			case Collateral -> collateralEntityModel.delete(id);
			case FixedDeposit -> fixedDepositEntityModel.delete(id);
			case InsuranceContract -> insuranceContractEntityModel.delete(id);
		}
	}

	public List<Loan> getAll() {
		return loanEntityModel.getAll();
	}

	public double getOutcome(int contractId) throws NotExistContractException, NotExistException {
		Contract contract = contractEntityModel.getById(contractId);
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
		List<CompensationDetail> compensationDetailList = compensationDetailEntityModel.getAll();
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
