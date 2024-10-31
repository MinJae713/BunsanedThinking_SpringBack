package com.example.bunsanedthinking_springback.model.sales;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SalesModel {
	public void evaluateSalesPerformance(int evaluate, Sales sales, EmployeeList employeeList) throws NotExistException {
		sales.setEvaluate(evaluate);
		employeeList.update(sales);
	}

	public void handleInsuranceConsultation(Counsel counsel, CounselList counselList) throws NotExistException, AlreadyProcessedException {
		if (counsel.getProcessStatus() == CounselProcessStatus.Completed) {
			throw new AlreadyProcessedException();
		}
		counsel.handle();
		counselList.update(counsel);
	}

	public Customer induceInsuranceProduct(String name, String address, String bankAccount, String bankName, String phoneNumber,
										   String job, long property, String residentRegistrationNumber, int age, Gender gender,
										   ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
										   ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
		
		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address, property, bankName, bankAccount);
		customerList.add(customer);
		if(accidentHistoryList != null) {
			for(AccidentHistory e :accidentHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setAccidentHistoryList(accidentHistoryList);
				accidentHistoryList.add(e);
			}
		}
		if(surgeryHistoryList != null) {
			for(SurgeryHistory e :surgeryHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setSurgeryHistoryList(surgeryHistoryList);
				surgeryHistoryList.add(e);
			}
		}
		if(diseaseHistoryList != null) {
			for(DiseaseHistory e :diseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				customer.setDiseaseHistoryList(diseaseHistoryList);
				diseaseHistoryList.add(e);
			}
		}

		Contract contract = new Contract(customer.getId(), product);
		contractList.add(contract);

		return customer;
	}

	public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
		return (Insurance) productList.get(id);

	}

	public Customer induceLoanProduct(String name, String address, String bankAccount, String bankName, String phoneNumber, 
			String job, long property, String residentRegistrationNumber, int age, Gender gender,
			ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
			ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
		
		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address, property, bankName, bankAccount);
		customerList.add(customer);
		if(accidentHistoryList != null) {
			for(AccidentHistory e :accidentHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setAccidentHistoryList(accidentHistoryList);
				accidentHistoryList.add(e);
			}
		}
		if(surgeryHistoryList != null) {
			for(SurgeryHistory e :surgeryHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setSurgeryHistoryList(surgeryHistoryList);
				surgeryHistoryList.add(e);
			}
		}
		if(diseaseHistoryList != null) {
			for(DiseaseHistory e :diseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				customer.setDiseaseHistoryList(diseaseHistoryList);
				diseaseHistoryList.add(e);
			}
		}

		Contract contract = new Contract(customer.getId(), product);
		contractList.add(contract);

		return customer;

	}
	public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
		return (Loan) productList.get(id);
	}
	public ArrayList<Employee> getAll(EmployeeList employeeList){
		return employeeList.getAll();
	}
	public Employee get(EmployeeList employeeList, int id) throws NotExistException {
		return employeeList.get(id);
	}
	public Sales getSales(EmployeeList employeeList, int id) throws NotExistException {
		return employeeList.getSales(id);
	}
	
	public ArrayList<Counsel> getAll(CounselList counselList){
		return counselList.getAll();
	}
	public Counsel get(CounselList counselList, int id) throws NotExistException {
		return counselList.get(id);
	}
	
	public ArrayList<Product> getAll(ProductList productList){
		return productList.getAll();
	}
	
	public void add(DiseaseHistoryList diseaseHistoryList, DiseaseHistory diseaseHistory){
		diseaseHistoryList.add(diseaseHistory);
	}
	
	public void update(EmployeeList employeeList, Sales sales) throws NotExistException {
		employeeList.update(sales);
	}
	
	public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList){
		return productList.getAllDiseaseInsurance();
	}
	public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList){
		return productList.getAllInjuryInsurance();
	}
	public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList){
		return productList.getAllAutomobileInsurance();
	}
	
	public ArrayList<Loan> getAllCollateralLoan(ProductList productList){
		return productList.getAllCollateralLoan();
	}
	public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList){
		return productList.getAllFixedDepositLoan();
	}
	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList){
		return productList.getAllInsuranceContractLoan();
	}
	public Sales getSalesContractCount(EmployeeList employeeList, int id) throws NotExistException {
		return (Sales) employeeList.get(id);
	}
}
