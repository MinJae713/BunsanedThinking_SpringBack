package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementList;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractList;
import com.example.bunsanedthinking_springback.entity.revival.RevivalList;
import com.example.bunsanedthinking_springback.entity.termination.TerminationList;
import com.example.bunsanedthinking_springback.exception.*;
import com.example.bunsanedthinking_springback.model.customer.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerModel customerModel;

	@GetMapping("/tst")
	public List<DiseaseHistory> test() {
		return customerModel.test();
	}
	public void applyEndorsement(ContractList contractList, EndorsementList endorsementList, int index,
								 Contract contract) throws NotExistContractException {
		customerModel.applyEndorsement(contractList, endorsementList, index, contract);
	}
	public void applyInsuranceRevival(ContractList contractList, RevivalList revivalList, Contract contract,
									  Customer customer) throws NotExistContractException, NotExistTerminatedContract {
		customerModel.applyInsuranceRevival(contractList, revivalList, contract, customer);
	}
	public void applyInsuranceTermination(ContractList contractList, TerminationList terminationList, Contract contract,
										  Customer customer) throws NotExistContractException, NotExistMaintainedContract {
		customerModel.applyInsuranceTermination(contractList, terminationList, contract, customer);
	}
	public void applyRecontract(ContractList contractList, RecontractList recontractList, Contract contract,
								Customer customer) throws NotExistContractException, NotExistExpiredContract {
		customerModel.applyRecontract(contractList, recontractList, contract, customer);
	}
	public void payInsurancefee(Customer customer, Contract contract, int money, DepositPath path,
			DepositDetailList depositDetailList) {
		customerModel.payInsurancefee(customer, contract, money, path, depositDetailList);
	}
	public Insurance viewInsuranceProductList(ProductList productList, int id) throws NotExistException {
		return customerModel.viewInsuranceProductList(productList, id);
	}
	public Loan viewLoanProductList(ProductList productList, int id) throws NotExistException {
		return customerModel.viewLoanProductList(productList, id);
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return customerModel.get(customerList, id);
	}
	public ArrayList<Insurance> getAllInsurance(ProductList productList) {
		return customerModel.getAllInsurance(productList);
	}
	public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList) {
		return customerModel.getAllDiseaseInsurance(productList);
	}
	public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList) {
		return customerModel.getAllInjuryInsurance(productList);
	}
	public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList) {
		return customerModel.getAllAutomobileInsurance(productList);
	}
	public Insurance get(int id, ProductList productList) throws NotExistException {
		return customerModel.get(id, productList);
	}
	public ArrayList<Loan> getAllLoan(ProductList productList) {
		return customerModel.getAllLoan(productList);
	}
	public ArrayList<Loan> getAllCollateralLoan(ProductList productList) {
		return customerModel.getAllCollateralLoan(productList);
	}
	public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList) {
		return customerModel.getAllFixedDepositLoan(productList);
	}
	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList) {
		return customerModel.getAllInsuranceContractLoan(productList);
	}
	public Loan getLoan(ProductList productList, int id) throws NotExistException {
		return customerModel.getLoan(productList, id);
	}
	public ArrayList<Contract> getAllApprovedByCustomer(ContractList contractList, int id) throws NotExistContractException {
		return customerModel.getAllApprovedByCustomer(contractList, id);
	}
	public Insurance getInsurance(ProductList productList, int id) throws NotExistException {
		return customerModel.getInsurance(productList, id);
	}
	public ArrayList<Contract> getAllByCustomer(ContractList contractList, int id) throws NotExistContractException {
		return customerModel.getAllByCustomer(contractList, id);
	}
	public ArrayList<Contract> getAllAutomobileInsuranceContract(ContractList contractList) throws NotExistContractException {
		return customerModel.getAllAutomobileInsuranceContract(contractList);
	}
	public ArrayList<Contract> getAllInjuryInsuranceContract(ContractList contractList) throws NotExistContractException {
		return customerModel.getAllInjuryInsuranceContract(contractList);
	}
	public ArrayList<Contract> getAllDiseaseInsuranceContract(ContractList contractList) throws NotExistContractException {
		return customerModel.getAllDiseaseInsuranceContract(contractList);
	}
	public ArrayList<Contract> getContractByProductId(ContractList contractList, int id) {
		return customerModel.getContractByProductId(contractList, id);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return customerModel.get(contractList, id);
	}
	public Contract getAutomobileByMember(ContractList contractList, int id) throws NotExistContractException {
		return customerModel.getAutomobileByMember(contractList, id);
	}
	public ArrayList<Accident> getAllByCustomer(AccidentList accidentList, int id) {
		return customerModel.getAllByCustomer(accidentList, id);
	}
	public Accident get(AccidentList accidentList, int id) throws NotExistException {
		return customerModel.get(accidentList, id);
	}
	public ArrayList<Complaint> getAllByCustomerId(ComplaintList complaintList, int id) {
		return customerModel.getAllByCustomerId(complaintList, id);
	}
	public Complaint get(ComplaintList complaintList, int id) throws NotExistException {
		return customerModel.get(complaintList, id);
	}
}
