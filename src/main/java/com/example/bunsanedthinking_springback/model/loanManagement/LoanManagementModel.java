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
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoanManagementModel {

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
							   CollateralType collateralType, int minimumValue, ProductList productList) throws DuplicateLoanException {
		for (Product product : productList.getAll()) {
			if (product.getName().equals(name)) {
				throw new DuplicateLoanException();
			}
		}
		Collateral collateralLoan = new Collateral(loanType, name, interestRate, limit, minimumAsset, collateralType,
				minimumValue);
		productList.add(collateralLoan);
	}

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			int parameter, ProductList productList) throws DuplicateLoanException {
		if (loanType == LoanType.FixedDeposit) {
			for (Product product : productList.getAll()) {
				if (product.getName().equals(name)) {
					throw new DuplicateLoanException();
				}
			}
			FixedDeposit fixedDepositLoan = new FixedDeposit(loanType, name, interestRate, limit, minimumAsset,
					parameter);
			productList.add(fixedDepositLoan);
		} else if (loanType == LoanType.InsuranceContract) {
			for (Product product : productList.getAll()) {
				if (product.getName().equals(name)) {
					throw new DuplicateLoanException();
				}
			}
			InsuranceContract insuranceContractLoan = new InsuranceContract(loanType, name, interestRate, limit,
					minimumAsset, parameter);
			productList.add(insuranceContractLoan);
		}
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
			collateralLoan.setLimit(Integer.parseInt(input));
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
				fixedDepositLoan.setLimit(Integer.parseInt(input));
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
				lnsuranceContractLoan.setLimit(Integer.parseInt(input));
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