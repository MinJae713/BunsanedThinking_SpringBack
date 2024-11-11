package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.sales.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.InduceDTO;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.service.employee.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/sales")
public class SalesController {
	@Autowired
	private SalesService salesSModel;

	@PatchMapping("/evaluateSalesPerformance")
	public void evaluateSalesPerformance(int evaluate, int id){
		salesSModel.evaluateSalesPerformance(evaluate, id);
	}

	@PatchMapping("/handleInsuranceConsultation")
	public void handleInsuranceConsultation(int id) throws AlreadyProcessedException {
		salesSModel.handleInsuranceConsultation(id);
	}

	@PostMapping("/induceInsuranceProduct")
	public Customer induceInsuranceProduct(@RequestBody InduceDTO induceDTO) {
		return salesSModel.induceInsuranceProduct(induceDTO);
	}

	@PostMapping("/induceLoanProduct")
	public Customer induceLoanProduct(@RequestBody InduceDTO induceDTO) {
		return salesSModel.induceLoanProduct(induceDTO);
	}

	@GetMapping("/getInsuranceProduct")
	public Insurance getInsuranceProduct(int id) {
		return salesSModel.getInsuranceProduct(id);
	}

	@GetMapping("/getLoanProduct")
	public Loan getLoanProduct(int id) {
		return salesSModel.getLoanProduct(id);
	}

	@GetMapping("/getAllEmployee")
	public ArrayList<Employee> getAllEmployee() {
		return salesSModel.getAllEmployee();
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(int id) {
		return salesSModel.getEmployee(id);
	}

	@GetMapping("/getSales")
	public Sales getSales(int id) {
		return salesSModel.getSales(id);
	}

	@GetMapping("/getAllCounsel")
	public ArrayList<Counsel> getAllCounsel() {
		return salesSModel.getAllCounsel();
	}

	@GetMapping("/getCounsel")
	public Counsel getCounsel(int id) {
		return salesSModel.getCounsel(id);
	}

	@GetMapping("/getAllProduct")
	public ArrayList<Product> getAllProduct() {
		return salesSModel.getAllProduct();
	}

	@PostMapping("/addDiseaseHistory")
	public DiseaseHistory addDiseaseHistory(@RequestBody DiseaseHistoryDTO diseaseHistoryDTO) {
		return salesSModel.addDiseaseHistory(diseaseHistoryDTO);
	}

	@PatchMapping("/updateContractCount")
	public void updateContractCount(int id, int contractCount) {
		salesSModel.updateContractCount(id, contractCount);
	}

	@GetMapping("/getAllDiseaseInsurance")
	public ArrayList<Insurance> getAllDiseaseInsurance() {
		return salesSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public ArrayList<Insurance> getAllInjuryInsurance() {
		return salesSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public ArrayList<Insurance> getAllAutomobileInsurance() {
		return salesSModel.getAllAutomobileInsurance();
	}

	@GetMapping("getAllCollateralLoan")
	public ArrayList<Loan> getAllCollateralLoan() {
		return salesSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public ArrayList<Loan> getAllFixedDepositLoan() {
		return salesSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public ArrayList<Loan> getAllInsuranceContractLoan() {
		return salesSModel.getAllInsuranceContractLoan();
	}

	@GetMapping("/getSalesContractCount")
	public Sales getSalesContractCount(int id) {
		return salesSModel.getSalesContractCount(id);
	}

	@PatchMapping("/setContractCount")
	public void setContractCount(int contractCount, int id) {
		salesSModel.setContractCount(contractCount, id);
	}
}
