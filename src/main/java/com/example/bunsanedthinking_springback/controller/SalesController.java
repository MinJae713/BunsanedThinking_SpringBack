package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.sales.SalesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/sales")
public class SalesController {
	@Autowired
	private SalesModel salesModel;

	public void evaluateSalesPerformance(int evaluate, Sales sales, EmployeeList employeeList) throws NotExistException{
		salesModel.evaluateSalesPerformance(evaluate, sales, employeeList);
	}

	public void handleInsuranceConsultation(Counsel counsel, CounselList counselList) throws NotExistException, AlreadyProcessedException {
		salesModel.handleInsuranceConsultation(counsel, counselList);
	}

	public Customer induceInsuranceProduct(String name, String address, String bankAccount, String bankName, String phoneNumber,
										   String job, long property, String residentRegistrationNumber, int age, Gender gender,
										   ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
										   ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
		return salesModel.induceInsuranceProduct(name, address, bankAccount, bankName, phoneNumber, job, property, residentRegistrationNumber, 
				age, gender, diseaseHistoryList, surgeryHistoryList, accidentHistoryList, product, customerList, contractList);
	}

	public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
		return salesModel.getInsuranceProduct(productList, id);
	}

	public Customer induceLoanProduct(String name, String address, String bankAccount, String bankName, String phoneNumber, 
			String job, long property, String residentRegistrationNumber, int age, Gender gender,
			ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
			ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
		return salesModel.induceLoanProduct(name, address, bankAccount, bankName, phoneNumber, job, property, 
				residentRegistrationNumber, age, gender, diseaseHistoryList, surgeryHistoryList, 
				accidentHistoryList, product, customerList, contractList);
	}
	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
		return salesModel.getLoanProduct(productList, id);
	}
	public ArrayList<Employee> getAll(EmployeeList employeeList){
		return salesModel.getAll(employeeList);
	}
	public Employee get(EmployeeList employeeList, int id) throws NotExistException {
		return salesModel.get(employeeList, id);
	}
	public Sales getSales(EmployeeList employeeList, int id) throws NotExistException {
		return salesModel.getSales(employeeList, id);
	}
	public ArrayList<Counsel> getAll(CounselList counselList){
		return salesModel.getAll(counselList);
	}
	public Counsel get(CounselList counselList, int id) throws NotExistException {
		return salesModel.get(counselList, id);
	}
	public ArrayList<Product> getAll(ProductList productList){
		return salesModel.getAll(productList);
	}
	public void add(DiseaseHistoryList diseaseHistoryList, DiseaseHistory diseaseHistory){
		salesModel.add(diseaseHistoryList, diseaseHistory);
	}
	public void update(EmployeeList employeeList, Sales sales) throws NotExistException {
		salesModel.update(employeeList, sales);
	}
	public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList){
		return salesModel.getAllDiseaseInsurance(productList);
	}
	public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList){
		return salesModel.getAllInjuryInsurance(productList);
	}
	public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList){
		return salesModel.getAllAutomobileInsurance(productList);
	}
	public ArrayList<Loan> getAllCollateralLoan(ProductList productList){
		return salesModel.getAllCollateralLoan(productList);
	}
	public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList){
		return salesModel.getAllFixedDepositLoan(productList);
	}
	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList){
		return salesModel.getAllInsuranceContractLoan(productList);
	}
	public Sales getSalesContractCount(EmployeeList employeeList, int id) throws NotExistException {
		return salesModel.getSalesContractCount(employeeList, id);
	}
}
