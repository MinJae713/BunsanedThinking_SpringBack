package com.example.bunsanedthinking_springback.model.loanManagement;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetailList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.loan.*;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceContractMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoanManagementModel {
	private static int index = 0;

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
		for (Product product : productMapper.getAll_LoanManagement()) {
			if (product.getName().equals(name)) {
				throw new DuplicateLoanException();
			}
		}
	}

	private ProductVO createProductVO(String name, int limit) {
		index++;
		String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Loan.LOAN_SERIAL_NUMBER + index;
		int productId = Integer.parseInt(compound);
		return new ProductVO(productId, name, limit);
	}

	private LoanVO createLoanVO(int id, int ordinal, int minimumAsset, int monthlyIncome, int interestRate) {
		return new LoanVO(id, ordinal, minimumAsset, monthlyIncome, interestRate);
	}

	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
		try {
			return (Loan) productList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 대출 상품 정보가 존재하지 않습니다.");
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
		PaymentDetail paymentDetail = new PaymentDetail(customer.getName(), customer.getBankName(),
				customer.getBankAccount(), money, paymentType, contract.getId());
		paymentDetailList.add(paymentDetail);
		CompensationDetail compensationDetail = new CompensationDetail(contract.getId(), money);
		compensationDetailList.add(compensationDetail);
		contract.addCompensationDetail(compensationDetail);
	}

	public void updateLoanProduct(int index, String input, Collateral collateralLoan, ProductList productList)
			throws DuplicateLoanException, NotExistException {
		switch (index) {
		case 1:
			for (Product product : productList.getAll()) {
				if (product.getName().equals(input)) {
					throw new DuplicateLoanException();
				}
			}
			collateralLoan.setName(input);
			productList.update(collateralLoan);
			break;
		case 2:
			collateralLoan.setInterestRate(Integer.parseInt(input));
			productList.update(collateralLoan);
			break;
		case 3:
			collateralLoan.setMaximumMoney(Integer.parseInt(input));
			productList.update(collateralLoan);
			break;
		case 4:
			collateralLoan.setMinimumAsset(Integer.parseInt(input));
			productList.update(collateralLoan);
			break;
		case 5:
			if (CollateralType.RealEstate.ordinal() == (Integer.parseInt(input) - 1)) {
				collateralLoan.setCollateralType(CollateralType.RealEstate);
			} else if (CollateralType.Car.ordinal() == (Integer.parseInt(input) - 1)) {
				collateralLoan.setCollateralType(CollateralType.Car);
			}
			productList.update(collateralLoan);
			break;
		case 6:
			collateralLoan.setMinimumValue(Integer.parseInt(input));
			productList.update(collateralLoan);
			break;
		default:
			break;
		}
	}

	public void updateLoanProduct(int index, String input, Loan loan, ProductList productList)
			throws DuplicateLoanException, NotExistException {
		if (loan instanceof FixedDeposit) {
			FixedDeposit fixedDepositLoan = (FixedDeposit) loan;
			switch (index) {
			case 1:
				for (Product product : productList.getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateLoanException();
					}
				}
				fixedDepositLoan.setName(input);
				productList.update(fixedDepositLoan);
				break;
			case 2:
				fixedDepositLoan.setInterestRate(Integer.parseInt(input));
				productList.update(fixedDepositLoan);
				break;
			case 3:
				fixedDepositLoan.setMaximumMoney(Integer.parseInt(input));
				productList.update(fixedDepositLoan);
				break;
			case 4:
				fixedDepositLoan.setMinimumAsset(Integer.parseInt(input));
				productList.update(fixedDepositLoan);
				break;
			case 5:
				fixedDepositLoan.setMinimumAmount(Integer.parseInt(input));
				productList.update(fixedDepositLoan);
				break;
			default:
				break;
			}

		} else if (loan instanceof InsuranceContract) {
			InsuranceContract lnsuranceContractLoan = (InsuranceContract) loan;
			switch (index) {
			case 1:
				for (Product product : productList.getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateLoanException();
					}
				}
				lnsuranceContractLoan.setName(input);
				productList.update(lnsuranceContractLoan);
				break;
			case 2:
				lnsuranceContractLoan.setInterestRate(Integer.parseInt(input));
				productList.update(lnsuranceContractLoan);
				break;
			case 3:
				lnsuranceContractLoan.setMaximumMoney(Integer.parseInt(input));
				productList.update(lnsuranceContractLoan);
				break;
			case 4:
				lnsuranceContractLoan.setMinimumAsset(Integer.parseInt(input));
				productList.update(lnsuranceContractLoan);
				break;
			case 5:
				lnsuranceContractLoan.setProductID(Integer.parseInt(input));
				productList.update(lnsuranceContractLoan);
				break;
			default:
				break;
			}
		}

	}
	public void deleteLoanProduct(ProductList productList, int id) throws NotExistException {
		productList.delete(id);
	}
	public ArrayList<Product> getAll(ProductList productList) {
		return productList.getAll();
	}
}
