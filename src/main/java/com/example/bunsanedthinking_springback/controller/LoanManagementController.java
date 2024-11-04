package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetailList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.loan.LoanType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetailList;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.loanManagement.LoanManagementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/loanManagement")
public class LoanManagementController {
	@Autowired
	private LoanManagementModel loanManagementModel;

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
							   CollateralType collateralType, int minimumValue, ProductList productList) throws DuplicateLoanException {
//		loanManagementModel.addLoanProduct(loanType, name, interestRate, limit, minimumAsset, collateralType, minimumValue, productList);
	}

	public void addLoanProduct(LoanType loanType, String name, int interestRate, int limit, int minimumAsset,
			int parameter, ProductList productList) throws DuplicateLoanException {
//		loanManagementModel.addLoanProduct(loanType, name, interestRate, limit, minimumAsset, parameter, productList);
	}

	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
		return loanManagementModel.getLoanProduct(productList, id);
	}

	public boolean collectLoanPrincipalInterest() {
		return loanManagementModel.collectLoanPrincipalInterest();
	}

	public void requestLoan(Contract contract, Customer customer, int money, PaymentType paymentType, boolean result,
							ContractList contractList, PaymentDetailList paymentDetailList, CompensationDetailList compensationDetailList) throws AlreadyProcessedException, NotExistContractException {
		loanManagementModel.requestLoan(contract, customer, money, paymentType, 
				result, contractList, paymentDetailList, compensationDetailList);
	}

	public void updateLoanProduct(int index, String input, Collateral collateralLoan, ProductList productList)
			throws DuplicateLoanException, NotExistException {
		loanManagementModel.updateLoanProduct(index, input, collateralLoan, productList);
	}

	public void updateLoanProduct(int index, String input, Loan loan, ProductList productList)
			throws DuplicateLoanException, NotExistException {
		loanManagementModel.updateLoanProduct(index, input, loan, productList);
	}
	public void deleteLoanProduct(ProductList productList, int id) throws NotExistException {
		loanManagementModel.deleteLoanProduct(productList, id);
	}
	public ArrayList<Product> getAll(ProductList productList) {
		return loanManagementModel.getAll(productList);
	}
}
