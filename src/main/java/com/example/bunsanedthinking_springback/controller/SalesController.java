package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.dae.InduceDTO;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.sales.SalesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/sales")
public class SalesController {
	@Autowired
	private SalesModel salesModel;

	// public void evaluateSalesPerformance(int evaluate, Sales sales, EmployeeList employeeList) throws NotExistException{
	// 	salesModel.evaluateSalesPerformance(evaluate, sales, employeeList);
	// }
	//
	// public void handleInsuranceConsultation(Counsel counsel, CounselList counselList) throws NotExistException, AlreadyProcessedException {
	// 	salesModel.handleInsuranceConsultation(counsel, counselList);
	// }
	//
	// public Customer induceInsuranceProduct(String name, String address, String bankAccount, String bankName, String phoneNumber,
	// 									   String job, long property, String residentRegistrationNumber, int age, Gender gender,
	// 									   ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
	// 									   ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
	// 	return salesModel.induceInsuranceProduct(name, address, bankAccount, bankName, phoneNumber, job, property, residentRegistrationNumber,
	// 			age, gender, diseaseHistoryList, surgeryHistoryList, accidentHistoryList, product, customerList, contractList);
	// }
	//
	// public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
	// 	return salesModel.getInsuranceProduct(productList, id);
	// }
	//
	// public Customer induceLoanProduct(String name, String address, String bankAccount, String bankName, String phoneNumber,
	// 		String job, long property, String residentRegistrationNumber, int age, Gender gender,
	// 		ArrayList<DiseaseHistory> diseaseHistoryList, ArrayList<SurgeryHistory> surgeryHistoryList,
	// 		ArrayList<AccidentHistory> accidentHistoryList, Product product, CustomerList customerList, ContractList contractList) {
	// 	return salesModel.induceLoanProduct(name, address, bankAccount, bankName, phoneNumber, job, property,
	// 			residentRegistrationNumber, age, gender, diseaseHistoryList, surgeryHistoryList,
	// 			accidentHistoryList, product, customerList, contractList);
	// }
	// public Loan getLoanProduct(ProductList productList, int id) throws NotExistException {
	// 	return salesModel.getLoanProduct(productList, id);
	// }
	// public ArrayList<Employee> getAll(EmployeeList employeeList){
	// 	return salesModel.getAll(employeeList);
	// }
	// public Employee get(EmployeeList employeeList, int id) throws NotExistException {
	// 	return salesModel.get(employeeList, id);
	// }
	// public Sales getSales(EmployeeList employeeList, int id) throws NotExistException {
	// 	return salesModel.getSales(employeeList, id);
	// }
	// public ArrayList<Counsel> getAll(CounselList counselList){
	// 	return salesModel.getAll(counselList);
	// }
	// public Counsel get(CounselList counselList, int id) throws NotExistException {
	// 	return salesModel.get(counselList, id);
	// }
	// public ArrayList<Product> getAll(ProductList productList){
	// 	return salesModel.getAll(productList);
	// }
	// public void add(DiseaseHistoryList diseaseHistoryList, DiseaseHistory diseaseHistory){
	// 	salesModel.add(diseaseHistoryList, diseaseHistory);
	// }
	// public void update(EmployeeList employeeList, Sales sales) throws NotExistException {
	// 	salesModel.update(employeeList, sales);
	// }
	// public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList){
	// 	return salesModel.getAllDiseaseInsurance(productList);
	// }
	// public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList){
	// 	return salesModel.getAllInjuryInsurance(productList);
	// }
	// public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList){
	// 	return salesModel.getAllAutomobileInsurance(productList);
	// }
	// public ArrayList<Loan> getAllCollateralLoan(ProductList productList){
	// 	return salesModel.getAllCollateralLoan(productList);
	// }
	// public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList){
	// 	return salesModel.getAllFixedDepositLoan(productList);
	// }
	// public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList){
	// 	return salesModel.getAllInsuranceContractLoan(productList);
	// }
	// public Sales getSalesContractCount(EmployeeList employeeList, int id) throws NotExistException {
	// 	return salesModel.getSalesContractCount(employeeList, id);
	// }

	@PatchMapping("/evaluateSalesPerformance")
	public void evaluateSalesPerformance(int evaluate, int id){
		salesModel.evaluateSalesPerformance(evaluate, id);
	}

	@PatchMapping("/handleInsuranceConsultation")
	public void handleInsuranceConsultation(int id) throws AlreadyProcessedException {
		salesModel.handleInsuranceConsultation(id);
	}


	@PostMapping("/induceInsuranceProduct")
	public Customer induceInsuranceProduct(InduceDTO induceDTO) {
		return salesModel.induceInsuranceProduct(induceDTO);
	}

	@PostMapping("/induceLoanProduct")
	public Customer induceLoanProduct(InduceDTO induceDTO) {
		return salesModel.induceLoanProduct(induceDTO);
	}

	@GetMapping("/getInsuranceProduct")
	public Insurance getInsuranceProduct(int id) {
		return salesModel.getInsuranceProduct(id);
	}

	@GetMapping("/getLoanProduct")
	public Loan getLoanProduct(int id){
		return salesModel.getLoanProduct(id);
	}

	@GetMapping("/getAllEmployee")
	public ArrayList<Employee> getAllEmployee(){
		return salesModel.getAllEmployee();
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(int id){
		return salesModel.getEmployee(id);
	}

	@GetMapping("/getSales")
	public Sales getSales(int id){
		return salesModel.getSales(id);
	}

	@GetMapping("/getAllCounsel")
	public ArrayList<Counsel> getAllCounsel(){
		return salesModel.getAllCounsel();
	}

	@GetMapping("/getCounsel")
	public Counsel getCounsel(int id){
		return salesModel.getCounsel(id);
	}

	@GetMapping("/getAllProduct")
	public ArrayList<Product> getAllProduct(){
		return salesModel.getAllProduct();
	}

	@PostMapping("/addDiseaseHistory")
	public int addDiseaseHistory(@RequestBody DiseaseHistory diseaseHistory){
		return salesModel.addDiseaseHistory(diseaseHistory);
	}

	@PatchMapping("/updateContractCount")
	public void updateContractCount(int id, int contractCount){
		salesModel.updateContractCount(id, contractCount);
	}

	@GetMapping("/getAllDiseaseInsurance")
	public ArrayList<Insurance> getAllDiseaseInsurance(){
		return salesModel.getAllDiseaseInsurance();
	}
	@GetMapping("/getAllInjuryInsurance")
	public ArrayList<Insurance> getAllInjuryInsurance(){
		return salesModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public ArrayList<Insurance> getAllAutomobileInsurance(){
		return salesModel.getAllAutomobileInsurance();
	}

	@GetMapping("getAllCollateralLoan")
	public ArrayList<Loan> getAllCollateralLoan(){
		return salesModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public ArrayList<Loan> getAllFixedDepositLoan(){
		return salesModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public ArrayList<Loan> getAllInsuranceContractLoan(){
		return salesModel.getAllInsuranceContractLoan();
	}

	@GetMapping("/getSalesContractCount")
	public Sales getSalesContractCount(int id){
		return salesModel.getSalesContractCount(id);
	}

	public void setContractCount(int contractCount, Sales sales) {
		salesModel.setContractCount(contractCount, sales);
	}
}
